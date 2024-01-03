/*
 * Copyright 2024 HM Revenue & Customs
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

object WhenFlexiblyAccessPensionPage extends BasePage {

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

  def verifyAllowedDatesAndContinue(): Unit = {
    enterValidPastDateAndClickContinue()
    clickBackButton()
    enterValidFutureDateAndClickContinue()
  }
}
