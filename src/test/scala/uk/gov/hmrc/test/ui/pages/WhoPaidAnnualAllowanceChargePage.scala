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

object WhoPaidAnnualAllowanceChargePage extends BasePage {
  val WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_TITLE  =
    "Who paid the annual allowance charge for [period selected]? - Calculate Public Pension Adjustment service - GOV.UK"
  val WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_HEADER =
    "Who paid the annual allowance charge for [period selected]?"

  def onPensionSchemeInputAmountsPage() = {
    verifyPageUrl("who-paid-annual-allowance-charge-[year]-[year]")
    onPage(WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_TITLE)
    isHeader(WHO_PAID_ANNUAL_ALLOWANCE_CHARGE_PAGE_HEADER)
  }
}
