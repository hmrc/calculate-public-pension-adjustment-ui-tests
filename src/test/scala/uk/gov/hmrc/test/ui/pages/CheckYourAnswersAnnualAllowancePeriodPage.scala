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

import uk.gov.hmrc.test.ui.constants.PageInformation.{CHECK_YOUR_ANSWERS_PAGE_FOR_AA_HEADER, CHECK_YOUR_ANSWERS_PAGE_FOR_AA_TITLE, CHECK_YOUR_PERIOD_ANSWERS_PAGE_FOR_AA_HEADER2}
import util.DataCollectorMap.checkAnswersAAPeriod

object CheckYourAnswersAnnualAllowancePeriodPage extends BasePage {

  def onCheckYourAnswersAnnualAllowancePeriodPage(period: String) = {
    verifyPageUrl(s"annual-allowance/$period/check-answers")
    onPage(CHECK_YOUR_ANSWERS_PAGE_FOR_AA_TITLE)
    isHeader(CHECK_YOUR_ANSWERS_PAGE_FOR_AA_HEADER)
    isHeader2(CHECK_YOUR_PERIOD_ANSWERS_PAGE_FOR_AA_HEADER2 + " " + period)
  }

  def verifyCheckYourAnswersPageAndContinue(period: String) = {
    onCheckYourAnswersAnnualAllowancePeriodPage(period)
    val sortedCheckAnswersAAPeriod = checkAnswersAAPeriod.sortBy(_._1)
    // TODO mapping of check your answers labels
//    require(
//      sortedCheckAnswersAAPeriod == returnCheckYourAnswersPageInformationAsAList(),
//      "Data not matching"
//    )
    clickContinueButton()
  }

}
