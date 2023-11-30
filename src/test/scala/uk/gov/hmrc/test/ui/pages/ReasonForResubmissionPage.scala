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

import org.openqa.selenium.By
import org.scalatest.Assertion
import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.constants.Errors.TEXT_WITH_500_CHARACTERS
import uk.gov.hmrc.test.ui.constants.PageInformation.{REASON_FOR_RESUBMISSION_PAGE_HEADER, REASON_FOR_RESUBMISSION_PAGE_LABEL, REASON_FOR_RESUBMISSION_PAGE_TITLE}

object ReasonForResubmissionPage extends BasePage {

  def enterResubmissionReason(reason: String): Unit = {
    driver.findElement(By.xpath("//textarea[@id='value']")).clear
    driver.findElement(By.xpath("//textarea[@id='value']")).sendKeys(reason)
  }

  def getResubmissionReason(): String =
    driver.findElement(By.xpath("//textarea[@id='value']")).getAttribute("value")

  def verifiedMaxCharacterLength(): Boolean =
    getResubmissionReason() == TEXT_WITH_500_CHARACTERS

  def clearTextarea(): Unit =
    driver.findElement(By.xpath("//textarea[@id='value']")).clear()

  def enterReasonAndContinue() = {
    val reason = "resubmission reason"
    enterResubmissionReason(reason)
    checkYourAnswersGSMap(REASON_FOR_RESUBMISSION_PAGE_LABEL, reason)
    submitPage()
  }

  def enterReasonAndContinue(reason: String) = {
    enterResubmissionReason(reason)
    checkYourAnswersGSMap(REASON_FOR_RESUBMISSION_PAGE_LABEL, reason)
    submitPage()
  }

  def enterLengthierReasonAndContinue(reason: String) = {
    enterResubmissionReason(reason)
    checkYourAnswersGSMap(REASON_FOR_RESUBMISSION_PAGE_LABEL, reason)
    submitPage()
  }
}
