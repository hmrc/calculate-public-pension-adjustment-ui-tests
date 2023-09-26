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
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHO_PAYING_EXTRA_LTA_CHARGE_PAGE_HEADER, WHO_PAYING_EXTRA_LTA_CHARGE_PAGE_TITLE}

object WhoPayingExtraLtaChargePage extends BasePage {
  def onWhoPayingExtraLtaChargePage() = {
    verifyPageUrl("lifetime-allowance/who-paid-extra-charge")
    onPage(WHO_PAYING_EXTRA_LTA_CHARGE_PAGE_TITLE)
    isHeader(WHO_PAYING_EXTRA_LTA_CHARGE_PAGE_HEADER)
  }
  def selectPaidByYou() = {
    driver.findElement(By.id("value_0")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_0']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }
  def selectPaidByPensionScheme() = {
    driver.findElement(By.id("value_1")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_1']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }
  def verifyPageSelectYouAndContinue() = {
    onWhoPayingExtraLtaChargePage()
    selectPaidByYou()
    submitPage()
  }
  def verifyPageSelectPensionSchemeAndContinue() = {
    onWhoPayingExtraLtaChargePage()
    selectPaidByPensionScheme()
    submitPage()
  }
}
