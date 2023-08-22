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
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHICH_SCHEME_DETAILS_PAGE_HEADER, WHICH_SCHEME_DETAILS_PAGE_TITLE}

object WhichSchemeDetailsPage extends BasePage {
  def onWhichSchemeDetailsPage(period: String, pensionSchemeNumber: String) = {
    verifyPageUrl("which-scheme-details/" + period + "/" + pensionSchemeNumber)
    onPage(WHICH_SCHEME_DETAILS_PAGE_TITLE)
    isHeader(WHICH_SCHEME_DETAILS_PAGE_HEADER)
  }
  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//label[contains(text(),'" + pensionScheme + "')]/preceding::input[1]")).click()
  def selectNewScheme()                          = driver.findElement(By.xpath("//label[contains(text(),'New')]/preceding::input[1]")).click()
  def verifyPageSelectSchemeAndContinue(
    period: String,
    pensionSchemeNumber: String,
    pensionSchemeName: String,
    pensionScheme: String
  ) = {
    onWhichSchemeDetailsPage(period, pensionSchemeNumber)
    selectPensionScheme(pensionScheme)
    checkYourAnswersAAPeriodMap(
      "What is the name and tax reference of the pension scheme?",
      pensionSchemeName + " / " + pensionScheme
    )
    submitPage()
  }
  def verifyPageSelectNewSchemeAndContinue(period: String, pensionSchemeNumber: String) = {
    onWhichSchemeDetailsPage(period, pensionSchemeNumber)
    selectNewScheme()
    checkYourAnswersAAPeriodMap("What is the name and tax reference of the pension scheme?", "New")
    submitPage()
  }

  def isSchemeAvailable(schemeName: String): Boolean =
    driver.findElements(By.xpath("//label[contains(text(),'" + schemeName + "')]")).size > 0

}
