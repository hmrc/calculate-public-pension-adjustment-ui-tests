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
import org.scalatest.Assertion
import uk.gov.hmrc.test.ui.constants.PageInformation.{SAVINGS_STATEMENT_PAGE_HEADER, SAVINGS_STATEMENT_PAGE_TITLE}

import java.util.ResourceBundle

object SavingsStatementPage extends BasePage {
  def validatePageLanguage(language: String): Assertion = {
    val bundle = ResourceBundle.getBundle(language)

    language match {
      case "en" | "cy" =>
        assert(
          driver
            .findElement(
              By.xpath(
                "//div[@class='govuk-header__content']/a[@class='hmrc-header__service-name hmrc-header__service-name--linked']"
              )
            )
            .getText
            == bundle.getString("service.name")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//li[@class='hmrc-language-select__list-item']/span"
              )
            )
            .getText
            == bundle.getString("service.languagechnagename")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//a[@class='govuk-back-link  js-visible']"
              )
            )
            .getText
            == bundle.getString("site.back")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//h1[@class='govuk-fieldset__heading']"
              )
            )
            .getText
            == bundle.getString("isRSSReceived.title")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//input[@id='value']/../label[@class='govuk-label govuk-radios__label']']"
              )
            )
            .getText
            == bundle.getString("isRSSReceived.yes")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//input[@id='value-no']/../label[@class='govuk-label govuk-radios__label']"
              )
            )
            .getText
            == bundle.getString("isRSSReceived.no")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//button[@class='govuk-button']"
              )
            )
            .getText
            == bundle.getString("isRSSReceived.continueButton")
        )
        assert(
          driver
            .findElement(
              By.xpath(
                "//a[@class='govuk-link hmrc-report-technical-issue '"
              )
            )
            .getText
            == bundle.getString("isRSSReceived.reporttechnicalissuelink")
        )
      case _           => fail(s"Unsupported language: $language")
    }
  }

}
