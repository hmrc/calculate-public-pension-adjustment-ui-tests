/*
 * Copyright 2024 HM Revenue & Customs
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

object TaxReliefAmountPage extends BasePage {

  def enterTaxReliefAmount(taxReliefAmount: String) = driver.findElement(By.id("value")).sendKeys(taxReliefAmount)

  def verifyPageEnterTaxReliefAndContinue(taxReliefAmount: String) = {
    enterTaxReliefAmount(taxReliefAmount)
    checkYourAnswersCalculationsMap(getHeader(), taxReliefAmount)
    submitPage()
  }

  def verifyPageEnterTaxReliefSaveAndContinue(taxReliefAmount: String) = {
    enterTaxReliefAmount(taxReliefAmount)
    checkYourAnswersCalculationsMap(getHeader(), taxReliefAmount)
    saveAndsubmitPage()
  }
}
