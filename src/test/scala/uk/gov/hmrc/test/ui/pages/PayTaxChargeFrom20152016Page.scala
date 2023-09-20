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

import uk.gov.hmrc.test.ui.constants.PageInformation.{PAY_TAX_CHARGE_FROM2015_2016_PAGE_HEADER, PAY_TAX_CHARGE_FROM2015_2016_PAGE_TITLE}

object PayTaxChargeFrom20152016Page extends BasePage {
  def onPayTaxChargeFrom20152016Page() = {
    verifyPageUrl("annual-allowance/tax-charge-between-2015-2016")
    onPage(PAY_TAX_CHARGE_FROM2015_2016_PAGE_TITLE)
    isHeader(PAY_TAX_CHARGE_FROM2015_2016_PAGE_HEADER)
  }
}
