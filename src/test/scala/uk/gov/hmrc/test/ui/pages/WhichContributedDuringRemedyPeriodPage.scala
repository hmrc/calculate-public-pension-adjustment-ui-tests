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
import uk.gov.hmrc.test.ui.constants.PageInformation.{WHICH_CONTRIBUTED_DURING_REMEDY_PERIOD_PAGE_HEADER, WHICH_CONTRIBUTED_DURING_REMEDY_PERIOD_PAGE_TITLE}
import uk.gov.hmrc.test.ui.pages.PensionSchemeInputAmountsPage.{checkYourAnswersAAPeriodMap, getHeader}

object WhichContributedDuringRemedyPeriodPage extends BasePage {
  def onThresholdIncomePage(year: String, pensionSchemeNumber: String) = {
    verifyPageUrl("which-contributed-during-remedy-period/" + year + "/" + pensionSchemeNumber)
    onPage(WHICH_CONTRIBUTED_DURING_REMEDY_PERIOD_PAGE_TITLE)
    isHeader(WHICH_CONTRIBUTED_DURING_REMEDY_PERIOD_PAGE_HEADER)
  }
  def selectDefinedContribution() = driver.findElement(By.id("value_0")).click()
  def selectDefinedBenefit()      = driver.findElement(By.id("value_1")).click()

  def defSelectBothDCAndDB() = {
    selectDefinedContribution()
    selectDefinedBenefit()
    checkYourAnswersAAPeriodMap(getHeader(), "Defined contribution")
    checkYourAnswersAAPeriodMap(getHeader(), "Defined benefit")
  }

  def verifyPageSelectDCAndContinue(year: String, pensionSchemeNumber: String): Unit = {
    onThresholdIncomePage(year, pensionSchemeNumber)
    selectDefinedContribution()
    checkYourAnswersAAPeriodMap(getHeader(), "Defined contribution")
    submitPage()
  }

  def verifyPageSelectDBAndContinue(year: String, pensionSchemeNumber: String): Unit = {
    onThresholdIncomePage(year, pensionSchemeNumber)
    selectDefinedBenefit()
    checkYourAnswersAAPeriodMap(getHeader(), "Defined benefit")
    submitPage()
  }

  def verifyPageSelectDBAndDCANDContinue(year: String, pensionSchemeNumber: String): Unit = {
    onThresholdIncomePage(year, pensionSchemeNumber)
    defSelectBothDCAndDB()
    submitPage()
  }
}
