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

import uk.gov.hmrc.test.ui.constants.PageInformation.{PIA_PRE_REMEDY_2011_PAGE_HEADER, PIA_PRE_REMEDY_2011_PAGE_TITLE}

object PiaPreRemedyPage2011 extends BasePage {
  def onPiaPreRemedyPage2011PageEnterValueAndContinue() = {
    verifyPageUrl("annual-allowance/pension-input-amount/2011")
    onPage(PIA_PRE_REMEDY_2011_PAGE_TITLE)
    isHeader(PIA_PRE_REMEDY_2011_PAGE_HEADER)
    enterAmountAndClickContinue()
  }

  def enterAmountAndClickContinue() = {
    enterAmount("110000000")
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }

  def enterAmountAndClickContinue(amount: String) = {
    enterAmount(amount)
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }
}