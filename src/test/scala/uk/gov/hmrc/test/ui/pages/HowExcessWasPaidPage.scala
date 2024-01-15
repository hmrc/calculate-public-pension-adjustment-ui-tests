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

object HowExcessWasPaidPage extends BasePage {

  def selectAnnualPayment() = {
    driver.findElement(By.xpath("//input[@id='value_0']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_0']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectLumpSumPayment() = {
    driver.findElement(By.xpath("//input[@id='value_1']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_1']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectBothPayment() = {
    driver.findElement(By.xpath("//input[@id='value_2']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_2']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectRadioButtonAnnualPaymentAndContinue() = {
    selectAnnualPayment()
    submitPage()
  }

  def selectRadioButtonLumpSumAndContinue() = {
    selectLumpSumPayment()
    submitPage()
  }

  def selectRadioButtonBothAndContinue() = {
    selectBothPayment()
    submitPage()
  }
}
