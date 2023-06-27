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

object ThresholdIncomePage extends BasePage {
  val THRESHOLD_INCOME_PAGE_TITLE  =
    "Was your threshold income above [£110,000/£200,000] from 6 April (year) to 5 April (year)? - Calculate Public Pension Adjustment service - GOV.UK"
  val THRESHOLD_INCOME_PAGE_HEADER =
    "Was your threshold income above [£110,000/£200,000] from 6 April (year) to 5 April (year)?"
  def onThresholdIncomePage(year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("threshold-income/" + year + "/" + pensionSchemeNumber)
    onPage(THRESHOLD_INCOME_PAGE_TITLE)
    isHeader(THRESHOLD_INCOME_PAGE_HEADER)
  }

  def verifyPageSelectYesAndContinue(year: String, pensionSchemeNumber: String) = {
    onThresholdIncomePage(year, pensionSchemeNumber)
    selectYesAndContinueForAAPeriodPage()
  }

  def verifyPageSelectNoAndContinue(year: String, pensionSchemeNumber: String) = {
    onThresholdIncomePage(year, pensionSchemeNumber)
    selectNoAndContinueForAAPeriodPage()
  }

}
