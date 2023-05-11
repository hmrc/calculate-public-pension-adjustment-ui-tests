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
import uk.gov.hmrc.test.ui.constants.PageInformation.{REASON_FOR_RESUBMISSION_PAGE_HEADER, REASON_FOR_RESUBMISSION_PAGE_TITLE}

object ReasonForResubmissionPage extends BasePage {

  def enterResubmissionReason(reason: String): Unit = {
    driver.findElement(By.xpath("//textarea[@id='value']")).clear
    driver.findElement(By.xpath("//textarea[@id='value']")).sendKeys(reason)
  }

  def getResubmissionReason(): String =
    driver.findElement(By.xpath("//textarea[@id='value']")).getAttribute("value")

  def verifiedMaxCharacterLength(): Boolean                                  =
    getResubmissionReason == TEXT_WITH_500_CHARACTERS
  def validateReasonForResubmissionPageErrorsWhenNoTextAsReason(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//div[@class='govuk-form-group govuk-form-group--error']//p[@id='value-error']"))
        .getText
        .contains(Errors.TEXT_AREA_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.TEXT_AREA_ERROR_SUMMARY)
    )

  def validateReasonForResubmissionPageErrorsReasonExceedCharLimit(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//div[@class='govuk-form-group govuk-form-group--error']//p[@id='value-error']"))
        .getText
        .contains(Errors.REASON_FOR_RESUBMISSION_CHARACTER_LIMITATION_ERROR) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.REASON_FOR_RESUBMISSION_CHARACTER_LIMITATION_ERROR)
    )

  def clearTextarea(): Unit =
    driver.findElement(By.xpath("//textarea[@id='value']")).clear()

  def onReasonForResubmissionPage() = {
    verifyPageUrl("reason-for-resubmission")
    onPage(REASON_FOR_RESUBMISSION_PAGE_TITLE)
    isHeader(REASON_FOR_RESUBMISSION_PAGE_HEADER)
  }

  def enterReasonAndContinue() = {
    enterResubmissionReason("resubmission reason")
    submitPage()
  }

  def enterLengthierReasonAndContinue(reason: String) = {
    enterResubmissionReason(reason)
    submitPage()
  }
}
