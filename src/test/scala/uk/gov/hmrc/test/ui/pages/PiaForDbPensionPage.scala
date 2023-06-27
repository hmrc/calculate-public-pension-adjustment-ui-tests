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

object PiaForDbPensionPage extends BasePage {
  val PIA_FOR_DB_PENSION_PAGE_TITLE  =
    "What was your pension input amount for defined contribution pension schemes for (day) (month) (year) to (day) (month) (year)? - Calculate Public Pension Adjustment service - GOV.UK"
  val PIA_FOR_DB_PENSION_PAGE_HEADER =
    "What was your pension input amount for defined contribution pension schemes for (day) (month) (year) to (day) (month) (year)?"

  def onPiaForDbPensionPage(year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("pia-for-db-pension/" + year + "/" + pensionSchemeNumber)
    onPage(PIA_FOR_DB_PENSION_PAGE_TITLE)
    isHeader(PIA_FOR_DB_PENSION_PAGE_HEADER)
  }

  def enterPensionInputAmountForDB(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterPensionInputAmountForDBAndContinue(
    year: String,
    pensionSchemeNumber: String,
    adjustedIncome: String
  ) = {
    onPiaForDbPensionPage(year, pensionSchemeNumber)
    enterPensionInputAmountForDB(adjustedIncome)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
