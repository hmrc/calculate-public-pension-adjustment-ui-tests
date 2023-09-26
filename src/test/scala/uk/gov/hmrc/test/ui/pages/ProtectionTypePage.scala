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
import uk.gov.hmrc.test.ui.constants.PageInformation.{PROTECTION_TYPE_PAGE_HEADER, PROTECTION_TYPE_PAGE_TITLE}

object ProtectionTypePage extends BasePage {
  def onProtectionTypePage() = {
    verifyPageUrl("lifetime-allowance/protection-type")
    onPage(PROTECTION_TYPE_PAGE_TITLE)
    isHeader(PROTECTION_TYPE_PAGE_HEADER)
  }

  def selectEnhancedProtectionRadioButtonAndContinue(): Unit = {
    val text = "Enhanced protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

}
