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

import uk.gov.hmrc.test.ui.constants.PageInformation.{DATE_OF_BENEFIT_CRYSTALLISATION_EVENT_PAGE_HEADER, DATE_OF_BENEFIT_CRYSTALLISATION_EVENT_PAGE_TITLE}

object DateOfBenefitCrystallisationEventPage extends BasePage {
  def onDateOfBenefitCrystallisationEventPage() = {
    verifyPageUrl("date-of-benefit-crystallisation-event")
    onPage(DATE_OF_BENEFIT_CRYSTALLISATION_EVENT_PAGE_TITLE)
    isHeader(DATE_OF_BENEFIT_CRYSTALLISATION_EVENT_PAGE_HEADER)
  }
  def enterBenefitCrystallisationDateAndContinue() = {
    clearDate()
    enterDay("25")
    enterMonth("06")
    enterYear("2017")
    checkYourAnswersLASMap(getHeader(), getDate())
    submitPage()
  }
}
