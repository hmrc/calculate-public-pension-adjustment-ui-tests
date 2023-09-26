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
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHO_WILL_PAY_PAGE_HEADER, WHO_WILL_PAY_PAGE_TITLE}

object WhoWillPayPage extends BasePage {
  def verifyWhoWillPayPage(year: Int) = {
    verifyPageUrl("submission-service/" + year + "/who-will-pay-new-tax-charge")
    onPage(WHO_WILL_PAY_PAGE_TITLE)
    isHeader(WHO_WILL_PAY_PAGE_HEADER)
  }
  def selectPensionScheme() = driver.findElement(By.id("value_1")).click()
  def selectYou()           = driver.findElement(By.id("value_0")).click()
  def verifyPageSelectYouAndContinue(year: Int) = {
    verifyWhoWillPayPage(year)
    selectYou()
    submitPage()
  }

  def verifyPageSelectPensionSchemeAndContinue(year: Int) = {
    verifyWhoWillPayPage(year)
    selectPensionScheme()
    submitPage()
  }
}
