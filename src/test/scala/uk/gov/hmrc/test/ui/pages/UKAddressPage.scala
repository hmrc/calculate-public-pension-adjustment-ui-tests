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
import uk.gov.hmrc.test.ui.constants.PageInformation.{UK_ADDRESS_PAGE_HEADER, UK_ADDRESS_PAGE_TITLE}

object UKAddressPage extends BasePage {
  def verifyTheirUKAddressPage() = {
    verifyPageUrl("submission-service/your-address")
    onPage(UK_ADDRESS_PAGE_TITLE)
    isHeader(UK_ADDRESS_PAGE_HEADER)
  }

  def enterAddressInformation() = {
    driver.findElement(By.id("addressLine1")).sendKeys("No 137")
    driver.findElement(By.id("addressLine2")).sendKeys("Prince bcd road")
    driver.findElement(By.id("townOrCity")).sendKeys("London")
    driver.findElement(By.id("county")).sendKeys("London")
    driver.findElement(By.id("postCode")).sendKeys("AB3 7ED")
  }

  def verifyPageEnterAddressAndContinue() = {
    verifyTheirUKAddressPage()
    enterAddressInformation()
    submitPage()
  }
}
