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

import uk.gov.hmrc.test.ui.constants.PageInformation.{CONTRIBUTED_OTHER_DB_DC_SCHEME_PAGE_HEADER, CONTRIBUTED_OTHER_DB_DC_SCHEME_PAGE_TITLE}

object ContributedOtherDbDcSchemePage extends BasePage {
  def onContributedOtherDbDcSchemePage(year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("contributed-other-db-dc-scheme/" + year + "/" + pensionSchemeNumber)
    onPage(CONTRIBUTED_OTHER_DB_DC_SCHEME_PAGE_TITLE)
    isHeader(CONTRIBUTED_OTHER_DB_DC_SCHEME_PAGE_HEADER)
  }

  def verifyPageSelectYesAndContinue(year: String, pensionSchemeNumber: String) = {
    onContributedOtherDbDcSchemePage(year, pensionSchemeNumber)
    selectYesAndContinueForAAPeriodPage()
  }

  def verifyPageSelectNoAndContinue(year: String, pensionSchemeNumber: String) = {
    onContributedOtherDbDcSchemePage(year, pensionSchemeNumber)
    selectNoAndContinueForAAPeriodPage()
  }

}
