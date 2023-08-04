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
import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_HEADER, WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_TITLE}
import uk.gov.hmrc.test.ui.pages.WhenStopPayingPublicPensionPage.{checkYourAnswersAASMap, getDate, getHeader}

object WhenFlexiblyAccessPensionPage extends BasePage {
  def validateNoInputDateError(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_NO_INPUT_DATA_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_NO_INPUT_DATA_ERROR_SUMMARY)
    )

  def validateRealDateError(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_REAL_DATE_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_REAL_DATE_ERROR_SUMMARY)
    )

  def validateFutureDateError(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_FUTURE_DATE_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_FUTURE_DATE_ERROR_SUMMARY)
    )

  def validatePreRemedyDateError(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_PRE_REMEDY_DATE_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_PRE_REMEDY_DATE_ERROR_SUMMARY)
    )

  def validateDateFormatError(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//fieldset[@class='govuk-fieldset']//p[@id='value-error']"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_DATES_FORMAT_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_DATES_FORMAT_ERROR_SUMMARY)
    )
  def onWhenFlexiblyAccessPensionPage() = {
    verifyPageUrl("when-flexibly-access-pension")
    onPage(WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_TITLE)
    isHeader(WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_HEADER)
  }

  def enterAnInvalidDateAndClickContinue() = {
    clearDate()
    enterDay("32")
    enterMonth("04")
    enterYear("2016")
    submitPage()
  }

  def enterAnInvalidMonthAndClickContinue() = {
    clearDate()
    enterDay("25")
    enterMonth("14")
    enterYear("2016")
    submitPage()
  }

  def enterAnInvalidYearAndClickContinue() = {
    clearDate()
    enterDay("25")
    enterMonth("14")
    enterYear("Y2016")
    submitPage()
  }

  def enterAFutureDateAndClickContinue() = {
    val (month, date, year) = getFutureDateTime()
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear(year.toString)
    submitPage()
  }

  def enterDateAndClickContinue(dateinFull: String) = {
    val (year, month, date) = parseDate(dateinFull)
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear(year.toString)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterAPreRemedyDateAndClickContinue() = {
    clearDate()
    enterDay("05")
    enterMonth("04")
    enterYear("2015")
    submitPage()
  }

  def enterValidPastDateAndClickContinue() = {
    clearDate()
    enterDay("06")
    enterMonth("04")
    enterYear("2015")
    submitPage()
  }

  def enterValidFutureDateAndClickContinue() = {
    val (month, date, year) = getCurrentDateTime()
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear(year.toString)
    submitPage()
  }

  def enterFlexiAcessDateAndClickContinue(date: String, month: String, year: String) = {
    clearDate()
    enterDay(date)
    enterMonth(month)
    enterYear(year)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def validateAllDateErrors(): Unit = {
    submitPage()
    validateNoInputDateError()
    enterAnInvalidDateAndClickContinue()
    validateRealDateError()
    enterAnInvalidMonthAndClickContinue()
    validateRealDateError()
    enterAnInvalidYearAndClickContinue()
    validateRealDateError()
    enterAFutureDateAndClickContinue()
    validateFutureDateError()
    enterAPreRemedyDateAndClickContinue()
    validatePreRemedyDateError()
  }

  def verifyAllowedDatesAndContinue(): Unit = {
    enterValidPastDateAndClickContinue()
    clickBackButton()
    onWhenFlexiblyAccessPensionPage()
    enterValidFutureDateAndClickContinue()
  }
}
