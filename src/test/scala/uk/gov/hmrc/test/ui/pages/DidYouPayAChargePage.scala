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

import uk.gov.hmrc.test.ui.constants.PageInformation.{DID_YOU_PAY_A_CHARGE_PAGE_HEADER, DID_YOU_PAY_A_CHARGE_PAGE_TITLE}

object DidYouPayAChargePage extends BasePage {
  def onPensionSchemeInputAmountsPage(period: String, pensionSchemeNumber: String, pensionSchemeName: String) = {
    verifyPageUrl(s"annual-allowance/$period/pension-scheme-$pensionSchemeNumber/annual-allowance-charge")

    onPage(DID_YOU_PAY_A_CHARGE_PAGE_TITLE.replaceAll("pensionSchemeNumber", pensionSchemeNumber))
    isHeader(DID_YOU_PAY_A_CHARGE_PAGE_HEADER + s" $pensionSchemeName?")
  }

  def verifyPageSelectNoAndContinue(period: String, pensionSchemeNumber: String, pensionSchemeName: String) = {
    onPensionSchemeInputAmountsPage(period, pensionSchemeNumber, pensionSchemeName)
    selectNoAndContinueForAAPeriodPage()
  }

  def verifyPageSelectYesAndContinue(period: String, pensionSchemeNumber: String, pensionSchemeName: String) = {
    onPensionSchemeInputAmountsPage(period, pensionSchemeNumber, pensionSchemeName)
    selectYesAndContinueForAAPeriodPage()
  }
}
