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

object TotalIncomePage extends BasePage {
  val TOTAL_INCOME_PAGE_TITLE  =
    "What was your total income from 6 April fromYear to 5 April toYear? - Calculate Public Pension Adjustment service - GOV.UK"
  val TOTAL_INCOME_PAGE_HEADER = "What was your total income from 6 April fromYear to 5 April toYear?"

  def onTotalIncomePage(fromYear: String, toYear: String, year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("total-income/" + year + "/" + pensionSchemeNumber)
    onPage(TOTAL_INCOME_PAGE_TITLE.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
    isHeader(TOTAL_INCOME_PAGE_HEADER.replaceAll("fromYear", fromYear).replaceAll("toYear", toYear))
  }

  def enterTotalIncome(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterTotalIncomeAndContinue(
    fromYear: String,
    toYear: String,
    year: String,
    pensionSchemeNumber: String,
    adjustedIncome: String
  ) = {
    onTotalIncomePage(fromYear, toYear, year, pensionSchemeNumber)
    enterTotalIncome(adjustedIncome)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
