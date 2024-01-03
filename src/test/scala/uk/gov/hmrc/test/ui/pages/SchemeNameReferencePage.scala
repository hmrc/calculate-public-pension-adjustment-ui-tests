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
import uk.gov.hmrc.test.ui.constants.PageInformation.{SCHEME_NAME_REFERENCE_PAGE_HEADER, SCHEME_NAME_REFERENCE_PAGE_TITLE, VALUE_OF_LUMP_SUM_PAGE_HEADER, VALUE_OF_LUMP_SUM_PAGE_TITLE}

object SchemeNameReferencePage extends BasePage {

  def enterSchemeName() =
    driver.findElement(By.id("name")).sendKeys("TTTTT")

  def enterSchemeReference() =
    driver.findElement(By.id("taxRef")).sendKeys("00348916BA")

  def enterSchemeNameReferenceAndContinue() = {
    enterSchemeName()
    enterSchemeReference()
    submitPage()
  }

  def enterSchemeNameReferenceAndContinue(schemeName: String, taxRef: String) = {
    enterSchemeName(schemeName)
    enterSchemeReference(taxRef)
    submitPage()
  }

  def enterSchemeName(schemeName: String) =
    driver.findElement(By.id("name")).sendKeys(schemeName)

  def enterSchemeReference(taxRef: String) =
    driver.findElement(By.id("taxRef")).sendKeys(taxRef)
}
