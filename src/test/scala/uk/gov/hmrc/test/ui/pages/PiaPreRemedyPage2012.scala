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

import uk.gov.hmrc.test.ui.constants.PageInformation.{PIA_PRE_REMEDY_2012_PAGE_HEADER, PIA_PRE_REMEDY_2012_PAGE_TITLE}

object PiaPreRemedyPage2012 extends BasePage {
  def onPiaPreRemedyPage2012Page() = {
    verifyPageUrl("pia-pre-remedy/2013")
    onPage(PIA_PRE_REMEDY_2012_PAGE_TITLE)
    isHeader(PIA_PRE_REMEDY_2012_PAGE_HEADER)
  }

  def enterAmountAndClickContinue() = {
    enterAmount("100000000")
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }
}
