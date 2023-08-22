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
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import util.NINOGenerator

object AuthorityWizardPage extends BasePage {

  val authUrl: String = TestConfiguration.url("auth-frontend")

  def authorizedLoginUser(): Unit = {
    driver.get(driver.getCurrentUrl)
    driver.findElement(By.id("nino")).sendKeys(NINOGenerator.nino)
    selectConfidenceLevel("250")
    driver.findElement(By.id("submit-top")).submit()
  }

  def selectConfidenceLevel(confidenceLevel: String) = {
    val selectElement = driver.findElement(By.id("confidenceLevel"))
    val dropdown      = new Select(selectElement)
    dropdown.selectByValue(confidenceLevel)
  }
}
