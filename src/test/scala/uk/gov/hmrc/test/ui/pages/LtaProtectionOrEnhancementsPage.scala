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
import uk.gov.hmrc.test.ui.constants.PageInformation.{LTA_PROTECTION_OR_ENHANCEMENTS_PAGE_HEADER, LTA_PROTECTION_OR_ENHANCEMENTS_PAGE_TITLE}

object LtaProtectionOrEnhancementsPage extends BasePage {
  def onLtaProtectionOrEnhancementsPage() = {
    verifyPageUrl("lifetime-allowance/protection-enhancements")
    onPage(LTA_PROTECTION_OR_ENHANCEMENTS_PAGE_TITLE)
    isHeader(LTA_PROTECTION_OR_ENHANCEMENTS_PAGE_HEADER)
  }

  def selectProtectionRadioButtonAndContinue(): Unit = {
    val text = "Protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectEnhancementsRadioButtonAndContinue(): Unit = {
    val text = "Enhancements"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectBothRadioButtonAndContinue(): Unit = {
    val text = "Both"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }
}
