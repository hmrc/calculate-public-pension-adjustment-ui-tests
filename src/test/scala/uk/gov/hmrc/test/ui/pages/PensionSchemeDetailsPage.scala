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
import uk.gov.hmrc.test.ui.constants.PageInformation.{PENSION_SCHEME_DETAILS_PAGE_HEADER, PENSION_SCHEME_DETAILS_PAGE_TITLE}

object PensionSchemeDetailsPage extends BasePage {
  def onPensionSchemeDetailsPage(period: String, pensionSchemeNumber: String) = {
    verifyPageUrl("pension-scheme-details/" + period + "/" + pensionSchemeNumber)
    onPage(PENSION_SCHEME_DETAILS_PAGE_TITLE)
    isHeader(PENSION_SCHEME_DETAILS_PAGE_HEADER)
  }
  def enterPensionSchemeName(schemeName: String) = {
    driver.findElement(By.id("schemeName")).clear()
    driver.findElement(By.id("schemeName")).sendKeys(schemeName)
  }
  def enterSchemeTaxReference(taxReference: String) = {
    driver.findElement(By.id("schemeTaxRef")).clear()
    driver.findElement(By.id("schemeTaxRef")).sendKeys(taxReference)
  }
  def enterTaxInformationAndContinue(
    period: String,
    pensionSchemeNumber: String,
    schemeName: String,
    taxReference: String
  ) = {
    onPensionSchemeDetailsPage(period, pensionSchemeNumber)
    enterPensionSchemeName(schemeName)
    enterSchemeTaxReference(taxReference)
    checkYourAnswersAAPeriodMap(getHeader(), schemeName + " / " + taxReference)
    submitPage()
  }

}
