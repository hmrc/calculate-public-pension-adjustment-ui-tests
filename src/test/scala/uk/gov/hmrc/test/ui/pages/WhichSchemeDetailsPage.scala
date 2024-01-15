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

object WhichSchemeDetailsPage extends BasePage {

  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//label[contains(text(),'" + pensionScheme + "')]/preceding::input[1]")).click()
  def selectNewScheme()                          = driver.findElement(By.xpath("//label[contains(text(),'New')]/preceding::input[1]")).click()
  def verifyPageSelectSchemeAndContinue(
    pensionSchemeName: String,
    pensionScheme: String
  ) = {
    selectPensionScheme(pensionScheme)
    checkYourAnswersAAPeriodMap(
      "What is the name and tax reference of the pension scheme?",
      pensionSchemeName + " / " + pensionScheme
    )
    submitPage()
  }
  def verifyPageSelectNewSchemeAndContinue() = {
    selectNewScheme()
    checkYourAnswersAAPeriodMap("What is the name and tax reference of the pension scheme?", "New")
    submitPage()
  }

  def isSchemeAvailable(schemeName: String): Boolean =
    driver.findElements(By.xpath("//label[contains(text(),'" + schemeName + "')]")).size > 0

}
