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
import uk.gov.hmrc.test.ui.pages.HowMuchYouPayChargePage.{checkYourAnswersAAPeriodMap, driver, getHeader}

object AdjustedIncomePage extends BasePage {
  val ADJUSTED_INCOME_PAGE_TITLE  =
    "What was your adjusted income from 6 April (year) to 5 April (year)? - Calculate Public Pension Adjustment service - GOV.UK"
  val ADJUSTED_INCOME_PAGE_HEADER = "What was your adjusted income from 6 April (year) to 5 April (year)?"
  def onAdjustedIncomePage(year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("adjusted-income/" + year + "/" + pensionSchemeNumber)
    onPage(ADJUSTED_INCOME_PAGE_TITLE)
    isHeader(ADJUSTED_INCOME_PAGE_HEADER)
  }

  def enterAdjustedIncome(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterAdjustedIncomeAndContinue(year: String, pensionSchemeNumber: String, adjustedIncome: String) = {
    onAdjustedIncomePage(year, pensionSchemeNumber)
    enterAdjustedIncome(adjustedIncome)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
