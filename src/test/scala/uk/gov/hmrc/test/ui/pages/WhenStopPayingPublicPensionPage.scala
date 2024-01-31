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

object WhenStopPayingPublicPensionPage extends BasePage {

  def enterAnInvalidDateAndClickContinue() = {
    clearDate()
    enterDay("32")
    enterMonth("04")
    enterYear("2016")
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
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValidPre2015DateAndClickContinue() = {
    clearDate()
    enterDay("08")
    enterMonth("07")
    enterYear("2015")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValid2017_2018_DateAndContinue() = {
    clearDate()
    enterDay("06")
    enterMonth("04")
    enterYear("2017")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValid2016_2017_DateAndContinue() = {
    clearDate()
    enterDay("05")
    enterMonth("04")
    enterYear("2017")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValidFutureDateAndClickContinue() = {
    clearDate()
    enterDay("05")
    enterMonth("04")
    enterYear("2022")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValidDateAndClickContinue(day: String, month: String, year: String) = {
    clearDate()
    enterDay(day)
    enterMonth(month)
    enterYear(year)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterAFutureDateAndClickContinue() = {
    clearDate()
    enterDay("06")
    enterMonth("04")
    enterYear("2022")
    submitPage()
  }
}
