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

object PensionSchemeInputAmountsPage extends BasePage {
  val PENSION_SCHEME_INPUT_AMOUNTS_PAGE_TITLE  =
    "Pension input amounts for the pension scheme - Calculate your public service pension adjustment - GOV.UK"
  val PENSION_SCHEME_INPUT_AMOUNTS_PAGE_HEADER =
    "Pension input amounts for thePensionSchemeName"

  def onPensionSchemeInputAmountsPage(period: String, pensionSchemeNumber: String, schemeName: String) = {
    verifyPageUrl(s"annual-allowance/$period/pension-scheme-$pensionSchemeNumber/pension-input-amount")
    onPage(PENSION_SCHEME_INPUT_AMOUNTS_PAGE_TITLE.replaceAll("thePensionSchemeName", schemeName))
    isHeader(PENSION_SCHEME_INPUT_AMOUNTS_PAGE_HEADER.replaceAll("thePensionSchemeName", schemeName))
  }

  def enterPensionInputAmount(pensionInputAmount: String) = {
    driver.findElement(By.id("originalPIA")).clear()
    driver.findElement(By.id("originalPIA")).sendKeys(pensionInputAmount)
  }

  def enterRevisedPensionInputAmount(revisedPensionInputAmount: String) = {
    driver.findElement(By.id("revisedPIA")).clear()
    driver.findElement(By.id("revisedPIA")).sendKeys(revisedPensionInputAmount)
  }

  def verifypageEnterPensionAmountsAndContinue(
    period: String,
    pensionSchemeNumber: String,
    pensionInputAmount: String,
    revisedPensionInputAmount: String,
    schemeName: String
  ) = {
    onPensionSchemeInputAmountsPage(period, pensionSchemeNumber, schemeName)
    enterPensionInputAmount(pensionInputAmount)
    enterRevisedPensionInputAmount(revisedPensionInputAmount)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + pensionInputAmount + " / " + "£" + revisedPensionInputAmount)
    submitPage()
  }
}
