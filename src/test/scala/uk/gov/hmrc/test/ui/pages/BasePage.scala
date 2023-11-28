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

import org.jsoup.Jsoup
import org.openqa.selenium.{By, WebElement}
import org.scalatest.Assertion
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import util.DataCollectorMap

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime, ZoneId}
import java.util.{Calendar, ResourceBundle}
import scala.collection.mutable
import scala.jdk.CollectionConverters.CollectionHasAsScala

trait GSDataCollector {
  def checkYourAnswersGSMap(key: String, value: Any): Unit
}

trait AASDataCollector {
  def checkYourAnswersAASMap(key: String, value: Any): Unit
}

trait LADataCollector {
  def checkYourAnswersLASMap(key: String, value: Any): Unit
}

trait CalculationDataCollector {
  def checkYourAnswersLASMap(key: String, value: Any): Unit
}

trait AAPeriodDataCollector {
  def checkYourAnswersCalculationsMap(key: String, value: Any): Unit
}

trait BasePage extends BrowserDriver with GSDataCollector with AASDataCollector with Matchers {

  def checkYourAnswersCalculationsMap(key: String, value: Any): Unit =
    DataCollectorMap.addToCalculationsPeriodMap(key, value)

  def checkYourAnswersGSMap(key: String, value: Any): Unit = {

    val displayLabel = value match {
      case s: String     =>
        s.replace("Annual allowance", "Annual allowance tax charges")
          .replace("Lifetime allowance", "Lifetime allowance tax charges")
      case selectedValue => selectedValue
    }

    DataCollectorMap.addToGSMap(key, displayLabel)
  }

  def checkYourAnswersAASMap(key: String, value: Any): Unit =
    DataCollectorMap.addToAASMap(key, value)

  def checkYourAnswersLASMap(key: String, value: Any): Unit =
    DataCollectorMap.addToLASMap(key, value)

  def checkYourAnswersAAPeriodMap(key: String, value: Any): Unit =
    DataCollectorMap.addToAAPeriodMap(key, value)

  def submitPage(): Unit =
    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click()

  def clickContinueButton(): Unit =
    driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click()

  def clickSubmissionContinueButton(): Unit =
    driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click()

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

  def isHeader2(header: String): Boolean = {
    val headerText = driver.findElement(By.xpath("//div[@class='govuk-grid-column-two-thirds']/h2")).getText
    if (headerText != header)
      throw PageNotFoundException(
        s"Expected '$header', but found '$headerText'"
      )
    else true
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
    var domain             = ""
    if (currentUrl.contains("submit-public")) {
      domain = TestConfiguration.url("submit-ui-frontend") + "/"
    } else {
      domain = TestConfiguration.url("ui-frontend") + "/"
    }
    val lastPart           = currentUrl.replaceAll(domain, "")
    lastPart shouldEqual name
  }

  def validateRadioButtonError(expectedErrorMessage: String): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(expectedErrorMessage) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(expectedErrorMessage)
    )

  def getHeader(): String = {
    var headerText = driver.findElement(By.xpath("//h1")).getText
    if (driver.findElements(By.xpath("//h1/span")).size() != 0) {
      headerText = headerText.replaceAll(driver.findElement(By.xpath("(//h1/span)")).getText, "").replaceAll("\n", "")
    }
    if ((driver.findElements(By.xpath("//h1/label/span")).size() != 0)) {
      headerText =
        headerText.replaceAll(driver.findElement(By.xpath("(//h1/label/span)")).getText, "").replaceAll("\n", "")
    }
    headerText
  }

  def getDate(): String = {
    val inputDate     = getDay() + "/" + getMonth() + "/" + getYear()
    val dateFormatter = new SimpleDateFormat("dd/MM/yyyy")
    val parsedDate    = dateFormatter.parse(inputDate)
    val calendar      = Calendar.getInstance()
    calendar.setTime(parsedDate)
    val year          = calendar.get(Calendar.YEAR)
    val month         = calendar.get(Calendar.MONTH) + 1
    val day           = calendar.get(Calendar.DAY_OF_MONTH)
    val localDate     = LocalDate.of(year, month, day)
    val outputFormat  = DateTimeFormatter.ofPattern("dd MMMM yyyy")
    val formattedDate = localDate.format(outputFormat)
    formattedDate
  }

  def selectedOption(): String =
    if (driver.findElement(By.id("value")).isSelected)
      "Yes"
    else if (driver.findElement(By.id("value-no")).isSelected)
      "No"
    else ""

  def selectYesAndContinueForGSPage() = {
    selectYesOption()
    checkYourAnswersGSMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectOptionAndContinueForGSPage(option: String) =
    option match {
      case "true"  =>
        selectYesOption()
        checkYourAnswersGSMap(getHeader(), selectedOption())
        submitPage()
      case "false" =>
        selectNoOption()
        checkYourAnswersGSMap(getHeader(), selectedOption())
        submitPage()
      case _       => fail("No data found")
    }

  def selectNoAndContinueForGSPage() = {
    selectNoOption()
    checkYourAnswersGSMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectYesAndContinueForAASPage() = {
    selectYesOption()
    checkYourAnswersAASMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectYesAndContinueForAAPeriodPage() = {
    selectYesOption()
    checkYourAnswersAAPeriodMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectNoAndContinueForAASPage() = {
    selectNoOption()
    checkYourAnswersAASMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectNoAndContinueForAAPeriodPage() = {
    selectNoOption()
    checkYourAnswersAAPeriodMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectYesAndContinueForLTAPage() = {
    selectYesOption()
    checkYourAnswersLASMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectNoAndContinueCalculationsPage() = {
    selectNoOption()
    checkYourAnswersCalculationsMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectYesAndContinueCalculationsPage() = {
    selectYesOption()
    checkYourAnswersCalculationsMap(getHeader(), selectedOption())
    submitPage()
  }

  def selectNoAndContinueForLTAPage() = {
    selectNoOption()
    checkYourAnswersLASMap(getHeader(), selectedOption())
    submitPage()
  }

  def enterDay(day: String) =
    driver.findElement(By.id("value.day")).sendKeys(day)

  def getDay() =
    driver.findElement(By.id("value.day")).getAttribute("value")

  def enterMonth(month: String) =
    driver.findElement(By.id("value.month")).sendKeys(month)

  def getMonth() =
    driver.findElement(By.id("value.month")).getAttribute("value")

  def enterYear(year: String) =
    driver.findElement(By.id("value.year")).sendKeys(year)

  def getYear() =
    driver.findElement(By.id("value.year")).getAttribute("value")

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

  def getFutureDateTime(): (Int, Int, Int) = {
    val currentDateTime = LocalDateTime.now(ZoneId.of("GMT+1")).plusDays(1)
    val currentDate     = currentDateTime.getDayOfMonth
    val currentMonth    = currentDateTime.getMonthValue
    val currentYear     = currentDateTime.getYear
    (currentMonth, currentDate, currentYear)
  }

  def parseDate(inputDate: String): (Int, Int, Int) = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(inputDate, formatter)
    val year      = localDate.getYear
    val month     = localDate.getMonthValue
    val day       = localDate.getDayOfMonth

    (year, month, day)
  }

  def enterAmount(amount: String) =
    driver.findElement(By.id("value")).sendKeys(amount)

  def getEnteredAmount(): String =
    "£" + driver.findElement(By.id("value")).getAttribute("value")

  def returnCheckYourAnswersPageInformation(): mutable.Map[String, Any] = {
    val map        = mutable.Map[String, Any]()
    // Extract <dt> and the first non-empty <dd> text for each <div>
    val dlElements = driver.findElements(By.xpath("//dl[@class='govuk-summary-list']"))
    dlElements.forEach { dlElement =>
      // Get the HTML content of the <dl> element
      val html        = dlElement.getAttribute("innerHTML")
      // Parse the HTML using Jsoup
      val document    = Jsoup.parse(html)
      // Extract <dt> and the first non-empty <dd> text for each <div>
      val divElements = document.select("div")
      divElements.forEach { divElement =>
        val dtElement  = divElement.selectFirst("dt")
        val ddElements = divElement.select("dd").asScala
        if (dtElement != null && ddElements.nonEmpty) {
          val dtText = dtElement.text().trim()
          val ddText = ddElements.find(_.text().trim().nonEmpty).map(_.text().trim()).getOrElse("")
          map.put(dtText, ddText)
        }
      }
    }
    map
  }

  def returnCheckYourAnswersPageInformationAsAList(): List[(String, Any)] = {
    var map         = List[(String, Any)]()
    // Extract <dt> and the first non-empty <dd> text for each <div>
    val dlElement   = driver.findElement(By.xpath("//dl[@class='govuk-summary-list']"))
    // Get the HTML content of the <dl> element
    val html        = dlElement.getAttribute("innerHTML")
    // Parse the HTML using Jsoup
    val document    = Jsoup.parse(html)
    // Extract <dt> and the first non-empty <dd> text for each <div>
    val divElements = document.select("div")
    divElements.forEach { divElement =>
      val dtElement  = divElement.selectFirst("dt")
      val ddElements = divElement.select("dd").asScala
      if (dtElement != null && ddElements.nonEmpty) {
        val dtText = dtElement.text().trim()
        val ddText = ddElements.find(_.text().trim().nonEmpty).map(_.text().trim()).getOrElse("")
        map = map :+ (dtText, ddText)
      }
    }
    map.sortBy(_._1)
  }

  def clickOnChangeLink(question: String) =
    driver.findElement(By.xpath("//dt[contains(text(),'" + question + "')]/ancestor::div[1]//a")).click()

  def signOutPage(): this.type = {
    driver.manage.deleteAllCookies()
    this
  }

  def getCheckedOptions(): String = {
    var selectedText               = ""
    val elements: List[WebElement] =
      driver.findElements(By.xpath("//input[@class='govuk-radios__input']")).asScala.toList
    for (checkbox <- elements)
      if (checkbox.isSelected) {
        selectedText = checkbox.findElement(By.xpath("//following-sibling::label")).getText()
      }
    selectedText
  }
}

case class PageNotFoundException(s: String) extends Exception(s)
