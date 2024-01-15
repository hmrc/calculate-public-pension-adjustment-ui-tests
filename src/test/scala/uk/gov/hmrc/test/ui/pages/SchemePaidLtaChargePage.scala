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

object SchemePaidLtaChargePage extends BasePage {

  def enterPensionScheme(pensionName: String) = {
    driver.findElement(By.xpath("//input[@id='name']")).clear()
    driver.findElement(By.xpath("//input[@id='name']")).sendKeys(pensionName)
  }

  def enterPensionSchemeTaxReference(taxRef: String) = {
    driver.findElement(By.xpath("//input[@id='taxRef']")).clear()
    driver.findElement(By.xpath("//input[@id='taxRef']")).sendKeys(taxRef)
  }

  def getPensionSchemeName() = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value")
  def getTaxReference()      = driver.findElement(By.xpath("//input[@id='taxRef']")).getAttribute("value")

  def enterPensionSchemeInformationAndContinue(taxRef: String, pensionName: String) = {
    enterPensionScheme(taxRef)
    enterPensionSchemeTaxReference(pensionName)
    checkYourAnswersLASMap(getHeader(), getPensionSchemeName() + " / " + getTaxReference())
    submitPage()
  }
}
