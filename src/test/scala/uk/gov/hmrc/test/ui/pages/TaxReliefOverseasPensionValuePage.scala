/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TaxReliefOverseasPensionValuePage extends BasePage {
  def enterTaxReliefOverseasPensionValue(taxReliefPensionValue: String) =
    driver.findElement(By.id("value")).sendKeys(taxReliefPensionValue)

  def verifyPageTaxReliefOverseasPensionValueAndContinue(taxReliefPensionValue: String) = {
    enterTaxReliefOverseasPensionValue(taxReliefPensionValue)
    submitPage()
  }

}
