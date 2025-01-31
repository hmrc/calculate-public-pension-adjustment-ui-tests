/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhichYearsScottishTaxpayer extends BasePage {
  var taxYears = ""

  def selectScottishTaxPayerYear(year: String) = {
    driver
      .findElement(
        By.xpath(
          "//div[@class='govuk-checkboxes__item']//label[contains(text(),'to 5 April " + year + "')]//preceding-sibling::input"
        )
      )
      .click()
    val fullText = driver
      .findElement(
        By.xpath("//div[@class='govuk-checkboxes__item']//label[contains(text(),'to 5 April " + year + "')]")
      )
      .getText
      .trim
    if (taxYears.nonEmpty) {
      taxYears = taxYears + "," + fullText
    } else {
      taxYears = fullText
    }
    checkYourAnswersAAPeriodMap(getHeader(), taxYears)
  }

  def selectScottishTaxpayerYearAndContinue(year: String) = {
    selectScottishTaxPayerYear(year)
    submitPage()
  }
}
