/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.scalatest.Assertion

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
