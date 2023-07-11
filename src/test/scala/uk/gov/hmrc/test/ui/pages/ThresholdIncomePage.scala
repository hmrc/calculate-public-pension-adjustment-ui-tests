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
  val THRESHOLD_INCOME_PAGE_2017_TO_2020_TITLE  =
    "Was your threshold income above £110,000 from 6 April fromYear to 5 April toYear? - Calculate Public Pension Adjustment service - GOV.UK"
  val THRESHOLD_INCOME_PAGE_2017_TO_2020_HEADER =
    "Was your threshold income above £110,000 from 6 April fromYear to 5 April toYear?"

  val THRESHOLD_INCOME_PAGE_2021_TO_2023_TITLE  =
    "Was your threshold income above £200,000 from 6 April fromYear to 5 April toYear? - Calculate Public Pension Adjustment service - GOV.UK"
  val THRESHOLD_INCOME_PAGE_2021_TO_2023_HEADER =
    "Was your threshold income above £200,000 from 6 April fromYear to 5 April toYear?"
  def onThresholdIncome2017TO2020Page(fromYear: String, toYear: String, year: String) = {
    verifyPageUrl("threshold-income/" + year)
    onPage(THRESHOLD_INCOME_PAGE_2017_TO_2020_TITLE.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
    isHeader(THRESHOLD_INCOME_PAGE_2017_TO_2020_HEADER.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
  }

  def onThresholdIncome2021TO2023Page(fromYear: String, toYear: String, year: String) = {
    verifyPageUrl("threshold-income/" + year)
    onPage(THRESHOLD_INCOME_PAGE_2021_TO_2023_TITLE.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
    isHeader(THRESHOLD_INCOME_PAGE_2021_TO_2023_HEADER.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
  }

  def verify2017TO2020PageSelectYesAndContinue(
    fromYear: String,
    toYear: String,
    year: String
  ) = {
    onThresholdIncome2017TO2020Page(fromYear, toYear, year)
    selectYesAndContinueForAAPeriodPage()
  }

  def verify2017TO2020PageSelectNoAndContinue(
    fromYear: String,
    toYear: String,
    year: String
  ) = {
    onThresholdIncome2017TO2020Page(fromYear, toYear, year)
    selectNoAndContinueForAAPeriodPage()
  }

  def verify2021TO2023PageSelectYesAndContinue(
    fromYear: String,
    toYear: String,
    year: String
  ) = {
    onThresholdIncome2021TO2023Page(fromYear, toYear, year)
    selectYesAndContinueForAAPeriodPage()
  }

  def verify2021TO2023PageSelectNoAndContinue(
    fromYear: String,
    toYear: String,
    year: String
  ) = {
    onThresholdIncome2021TO2023Page(fromYear, toYear, year)
    selectNoAndContinueForAAPeriodPage()
  }

}
