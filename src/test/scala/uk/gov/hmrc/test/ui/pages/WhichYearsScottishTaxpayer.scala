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
import org.scalatest.Assertion
import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.constants.PageInformation.{SCOTTISH_TAX_PAYER_FROM_2016_PAGE_HEADER, SCOTTISH_TAX_PAYER_FROM_2016_PAGE_TITLE, WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_HEADER, WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_TITLE}
import uk.gov.hmrc.test.ui.pages.ReportingChangePage.{assert, driver}
import uk.gov.hmrc.test.ui.pages.ScottishTaxpayerFrom2016Page.{isHeader, onPage, verifyPageUrl}

object WhichYearsScottishTaxpayer extends BasePage {
  def onWhichYearsScottishTaxpayerPage() = {
    verifyPageUrl("which-years-scottish-taxpayer")
    onPage(WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_TITLE)
    isHeader(WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_HEADER)
  }

  def validateWhichYearsScottishTaxpayerErrorsWhenNoCheckBoxSelected(): Assertion =
    assert(
      driver
        .findElement(By.xpath("//div[@class='govuk-form-group govuk-form-group--error']//p[@id='value-error']"))
        .getText
        .contains(Errors.WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_ERROR_SUMMARY) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//h2"))
        .getText
        .contains(Errors.ERROR_SUMMARY_TITLE) && driver
        .findElement(By.xpath("//div[@class='govuk-error-summary']//li"))
        .getText
        .contains(Errors.WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_ERROR_SUMMARY)
    )
}
