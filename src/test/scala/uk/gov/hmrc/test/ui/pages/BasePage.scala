/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.driver.BrowserDriver

import java.time.{LocalDateTime, ZoneId}
import java.util.ResourceBundle

trait BasePage extends BrowserDriver with Matchers {

  def submitPage(): Unit =
    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click()

  def clickContinueButton(): Unit =
    driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click()

  def onPage(pageTitle: String): Boolean =
    if (driver.getTitle != pageTitle)
      throw PageNotFoundException(
        s"Expected '$pageTitle' page, but found '${driver.getTitle}' page."
      )
    else true

  def isHeader(header: String): Boolean = {
    var headerText = driver.findElement(By.xpath("//h1")).getText
    if (driver.findElements(By.xpath("//h1/span")).size() != 0) {
      headerText = headerText.replaceAll(driver.findElement(By.xpath("(//h1/span)")).getText, "").replaceAll("\n", "")
      if (headerText != header)
        throw PageNotFoundException(
          s"Expected '$header', but found '$headerText'"
        )
      else true
    }
    if ((driver.findElements(By.xpath("//h1/label/span")).size() != 0)) {
      headerText =
        headerText.replaceAll(driver.findElement(By.xpath("(//h1/label/span)")).getText, "").replaceAll("\n", "")
      if (headerText != header)
        throw PageNotFoundException(
          s"Expected '$header', but found '$headerText'"
        )
      else true
    } else {
      if (headerText != header)
        throw PageNotFoundException(
          s"Expected '$header', but found '$headerText'"
        )
      else true
    }
  }

  def selectYesOption(): Unit =
    driver.findElement(By.id("value")).click()

  def selectNoOption(): Unit =
    driver.findElement(By.id("value-no")).click()

  def clickBackButton(): Unit =
    driver.findElement(By.xpath("//a[contains(text(),'Back')]")).click()

  def clickPageNotWorkingLink(): Unit =
    driver.findElement(By.xpath("//a[contains(@class,'govuk-link hmrc-report-technical-issue')]")).click()

  def clickWelshLanguageTranslationLink(): Unit =
    driver.findElement(By.xpath("//span[contains(text(),'Cymraeg')]")).click()

  def clickEnglishLanguageTranslationLink(): Unit =
    driver.findElement(By.xpath("//span[contains(text(),'English')]")).click()

  def verifyLoginButtonLanguage(language: String): Assertion = {
    val bundle = ResourceBundle.getBundle(language)

    language match {
      case "en" | "cy" =>
        assert(
          driver
            .findElement(
              By.xpath(
                "//div[@class='govuk-header__content']/a[@govuk-link hmrc-sign-out-nav__link']"
              )
            )
            .getText
            == bundle.getString("site.signIn")
        )
      case _           => fail(s"Unsupported language: $language")
    }
  }

  def verifyLogOutButtonLanguage(language: String): Assertion = {
    val bundle = ResourceBundle.getBundle(language)

    language match {
      case "en" | "cy" =>
        assert(
          driver
            .findElement(
              By.xpath(
                "//div[@class='govuk-header__content']/a[@govuk-link hmrc-sign-out-nav__link']"
              )
            )
            .getText
            == bundle.getString("site.signOut")
        )
      case _           => fail(s"Unsupported language: $language")
    }
  }

  def verifyPageUrl(name: String): Assertion = {
    val currentUrl: String = driver.getCurrentUrl
    val lastPart           = currentUrl.replaceAll(TestConfiguration.url("ui-frontend") + "/", "")
    lastPart shouldEqual name
  }

  def validateRadioButtonError(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(Errors.RADIO_BUTTON_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.RADIO_BUTTON_ERROR_SUMMARY)
    )

  def selectYesAndContinue() = {
    selectYesOption()
    submitPage()
  }

  def selectNoAndContinue() = {
    selectNoOption()
    submitPage()
  }

  def enterDay(day: String) =
    driver.findElement(By.id("value.day")).sendKeys(day)

  def enterMonth(month: String) =
    driver.findElement(By.id("value.month")).sendKeys(month)

  def enterYear(year: String) =
    driver.findElement(By.id("value.year")).sendKeys(year)

  def clearDate() = {
    driver.findElement(By.id("value.day")).clear()
    driver.findElement(By.id("value.month")).clear()
    driver.findElement(By.id("value.year")).clear()
  }

  def getCurrentDateTime(): (Int, Int, Int) = {
    val currentDateTime = LocalDateTime.now(ZoneId.of("GMT"))
    val currentDate     = currentDateTime.getDayOfMonth
    val currentMonth    = currentDateTime.getMonthValue
    val currentYear     = currentDateTime.getYear
    (currentMonth, currentDate, currentYear)
  }

  def enterAmount(amount: String) =
    driver.findElement(By.id("value")).sendKeys(amount)

}
case class PageNotFoundException(s: String) extends Exception(s)
