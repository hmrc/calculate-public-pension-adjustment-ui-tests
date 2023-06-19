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
import uk.gov.hmrc.test.ui.constants.PageInformation.{SCHEME_PAID_LTA_CHARGE_PAGE_HEADER, SCHEME_PAID_LTA_CHARGE_PAGE_TITLE, WHO_PAYING_EXTRA_LTA_CHARGE_PAGE_HEADER, WHO_PAYING_EXTRA_LTA_CHARGE_PAGE_TITLE}

object SchemePaidLtaChargePage extends BasePage {

  val pension_scheme_name =
    "pensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepe nsions"
  def onSchemePaidLtaChargePage() = {
    verifyPageUrl("scheme-paid-lta-charge")
    onPage(SCHEME_PAID_LTA_CHARGE_PAGE_TITLE)
    isHeader(SCHEME_PAID_LTA_CHARGE_PAGE_HEADER)
  }

  def enterPensionScheme() = {
    driver.findElement(By.xpath("//input[@id='name']")).clear()
    driver.findElement(By.xpath("//input[@id='name']")).sendKeys(pension_scheme_name)
  }

  def enterPensionSchemeTaxReference() = {
    driver.findElement(By.xpath("//input[@id='taxRef']")).clear()
    driver.findElement(By.xpath("//input[@id='taxRef']")).sendKeys("00348916RT")
  }

  def enterPensionSchemeInformationAndContinue() = {
    onSchemePaidLtaChargePage()
    enterPensionScheme()
    enterPensionSchemeTaxReference()
  }
}
