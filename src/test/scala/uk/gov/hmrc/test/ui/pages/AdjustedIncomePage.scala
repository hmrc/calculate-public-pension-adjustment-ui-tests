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

object AdjustedIncomePage extends BasePage {
  val ADJUSTED_INCOME_PAGE_TITLE  =
    "What was your adjusted income from 6 April fromYear to 5 April toYear? - Calculate Public Pension Adjustment service - GOV.UK"
  val ADJUSTED_INCOME_TITLE       =
    "What was your adjusted income from 6 April fromYear to 5 April toYear?"
  val ADJUSTED_INCOME_PAGE_HEADER = "Adjusted income"
  def onAdjustedIncomePage(fromYear: String, toYear: String, year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("adjusted-income/" + year)
    onPage(ADJUSTED_INCOME_PAGE_TITLE.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
    isHeader(ADJUSTED_INCOME_PAGE_HEADER)
  }

  def enterAdjustedIncome(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterAdjustedIncomeAndContinue(
    fromYear: String,
    toYear: String,
    year: String,
    adjustedIncome: String
  ) = {
    val newTitle = ADJUSTED_INCOME_TITLE.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear)
    onAdjustedIncomePage(fromYear, toYear, year, adjustedIncome)
    enterAdjustedIncome(adjustedIncome)
    checkYourAnswersAAPeriodMap(newTitle, "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
