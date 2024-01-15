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

object ProtectionChangedNewTypePage extends BasePage {

  def selectEnhancedProtection() = {
    val text = "Enhanced protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectFixedProtection2016() = {
    val text = "Fixed protection 2016"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
  }
  def selectEnhancedProtectionAndContinue() = {
    selectEnhancedProtection()
    submitPage()
  }

  def selectFixedProtection2016AndContinue() = {
    selectFixedProtection2016()
    submitPage()
  }

}
