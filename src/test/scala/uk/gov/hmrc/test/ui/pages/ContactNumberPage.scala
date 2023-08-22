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
import uk.gov.hmrc.test.ui.constants.PageInformation.{CONTACT_NUMBER_PAGE_HEADER, CONTACT_NUMBER_PAGE_TITLE}

object ContactNumberPage extends BasePage {
  val contactNumber = "+44 808 157 0192"
  def verifyContactNumberPage() = {
    verifyPageUrl("contact-number")
    onPage(CONTACT_NUMBER_PAGE_TITLE)
    isHeader(CONTACT_NUMBER_PAGE_HEADER)
  }
  def verifyPageEnterContactNumberAndContinue() = {
    verifyContactNumberPage()
    driver.findElement(By.id("value")).sendKeys(contactNumber)
    checkYourAnswersCalculationsMap(getHeader(), contactNumber)
    submitPage()
  }

  def verifyPageAndContinueWithoutContactNumber() = {
    verifyContactNumberPage()
    checkYourAnswersCalculationsMap(getHeader(), "")
    submitPage()
  }
}
