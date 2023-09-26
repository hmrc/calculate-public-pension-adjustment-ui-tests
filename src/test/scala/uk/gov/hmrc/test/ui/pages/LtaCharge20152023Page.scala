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

import uk.gov.hmrc.test.ui.constants.PageInformation.{LTA_CHARGE_2015_2023_PAGE_HEADER, LTA_CHARGE_2015_2023_PAGE_TITLE}

object LtaCharge20152023Page extends BasePage {
  def onLtaCharge20152023Page() = {
    verifyPageUrl("lifetime-allowance/charge-2015-2023")
    onPage(LTA_CHARGE_2015_2023_PAGE_TITLE)
    isHeader(LTA_CHARGE_2015_2023_PAGE_HEADER)
  }

  def selectYesAndClickOnContinue() = {
    onLtaCharge20152023Page()
    selectYesAndContinueForLTAPage()
  }

  def selectNoAndClickOnContinue() = {
    onLtaCharge20152023Page()
    selectNoAndContinueForLTAPage()
  }
}
