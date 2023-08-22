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
import uk.gov.hmrc.test.ui.constants.PageInformation.{BANK_DETAILS_PAGE_HEADER, BANK_DETAILS_PAGE_TITLE}

object BankDetailsPage extends BasePage {
  val accountName   = "Teddy Dickson"
  val sortCode      = "207102"
  val accountNumber = "44311655"
  def verifyBankDetailsPage() = {
    verifyPageUrl("bank-details")
    onPage(BANK_DETAILS_PAGE_TITLE)
    isHeader(BANK_DETAILS_PAGE_HEADER)
  }

  def enterBankDetails() = {
    driver.findElement(By.id("accountName")).sendKeys(accountName)
    driver.findElement(By.id("sortCode")).sendKeys(sortCode)
    driver.findElement(By.id("accountNumber")).sendKeys(accountNumber)
  }

  def verifyPageEnterBankDetailsClickContinue() = {
    verifyBankDetailsPage()
    enterBankDetails()
    checkYourAnswersCalculationsMap(getHeader(), accountName + " " + sortCode + " " + accountNumber)
    submitPage()
  }

}
