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

object WhoPaidAnnualAllowanceChargePage extends BasePage {
  val WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_TITLE  =
    "Who paid the annual allowance tax charge for pensionSchemeNumber? - Calculate Public Pension Adjustment service - GOV.UK"
  val WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_HEADER =
    "Who paid the annual allowance tax charge for schemeName?"

  // TODO requires a minor change on top of the changes in MCSC-390 for this to work
  def onWhoPaidAnnualAllowanceChargePage(
    year: String,
    pensionSchemeNumber: String,
    period: String,
    schemeName: String
  ) = {
    verifyPageUrl(s"annual-allowance/$year/pension-scheme-$pensionSchemeNumber/who-paid-charge")
    onPage(WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_TITLE.replaceAll("pensionSchemeNumber", pensionSchemeNumber))
    isHeader(WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_HEADER.replaceAll("schemeName", schemeName))
  }
  def selectYou()           = driver.findElement(By.id("value_0")).click()
  def selectPensionScheme() = driver.findElement(By.id("value_1")).click()
  def selectBoth()          = driver.findElement(By.id("value_2")).click()

  def verifyPageSelectYouAndContinue(year: String, pensionSchemeNumber: String, period: String, schemeName: String) = {
    onWhoPaidAnnualAllowanceChargePage(year, pensionSchemeNumber, period, schemeName)
    selectYou()
    checkYourAnswersAAPeriodMap(getHeader(), "You")
    submitPage()
  }
  def verifyPageSelectPensionSchemeAndContinue(
    year: String,
    pensionSchemeNumber: String,
    period: String,
    schemeName: String
  ) = {
    onWhoPaidAnnualAllowanceChargePage(year, pensionSchemeNumber, period, schemeName)
    selectPensionScheme()
    checkYourAnswersAAPeriodMap(getHeader(), "Pension Scheme")
    submitPage()
  }
  def verifyPageSelectBothAndContinue(year: String, pensionSchemeNumber: String, period: String, schemeName: String) = {
    onWhoPaidAnnualAllowanceChargePage(year, pensionSchemeNumber, period, schemeName)
    selectBoth()
    checkYourAnswersAAPeriodMap(getHeader(), "Both")
    submitPage()
  }

}
