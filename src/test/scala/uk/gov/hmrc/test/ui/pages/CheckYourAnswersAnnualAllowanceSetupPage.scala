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

import uk.gov.hmrc.test.ui.constants.PageInformation.{CHECK_YOUR_ANSWERS_PAGE_FOR_AA_HEADER, CHECK_YOUR_ANSWERS_PAGE_FOR_AA_HEADER2, CHECK_YOUR_ANSWERS_PAGE_FOR_AA_TITLE}
import util.DataCollectorMap

object CheckYourAnswersAnnualAllowanceSetupPage extends BasePage {

  def onCheckYourAnswersAnnualAllowanceSetupPage() = {
    verifyPageUrl("check-your-answers-annual-allowance-setup")
    onPage(CHECK_YOUR_ANSWERS_PAGE_FOR_AA_TITLE)
    isHeader(CHECK_YOUR_ANSWERS_PAGE_FOR_AA_HEADER)
    isHeader2(CHECK_YOUR_ANSWERS_PAGE_FOR_AA_HEADER2)
  }

  def verifyCheckYourAnswersPageAndContinue() = {
    onCheckYourAnswersAnnualAllowanceSetupPage()
    require(DataCollectorMap.checkAnswersAAS == returnCheckYourAnswersPageInformation(), "Data not matching")
    clickContinueButton()
  }

  def clickChangeOnWhenDidYouStopPaying() = clickOnChangeLink(
    "When did you stop paying into a public service pension scheme"
  )

}
