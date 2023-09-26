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
import uk.gov.hmrc.test.ui.constants.PageInformation.{REFORM_PENSION_SCHEME_REFERENCE_PAGE_HEADER, REFORM_PENSION_SCHEME_REFERENCE_PAGE_TITLE}

object ReformPensionSchemeReferencePage extends BasePage {
  val pensionRef = "TAX00000629RTED"
  def verifyReformPensionSchemeReferencePage(taxRef: String) = {
    verifyPageUrl("submission-service/" + taxRef + "/reform-individual-pension-scheme-reference")
    onPage(REFORM_PENSION_SCHEME_REFERENCE_PAGE_TITLE)
    isHeader(REFORM_PENSION_SCHEME_REFERENCE_PAGE_HEADER)
  }

  def verifyPageEnterReferenceAndContinue(taxRef: String) = {
    verifyReformPensionSchemeReferencePage(taxRef)
    driver.findElement(By.id("value")).sendKeys(pensionRef)
    checkYourAnswersCalculationsMap(getHeader(), pensionRef)
    submitPage()
  }
}
