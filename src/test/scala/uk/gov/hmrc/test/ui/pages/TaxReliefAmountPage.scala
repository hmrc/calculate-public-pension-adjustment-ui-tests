/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TaxReliefAmountPage extends BasePage {

  def enterTaxReliefAmount(taxReliefAmount: String) = driver.findElement(By.id("value")).sendKeys(taxReliefAmount)

  def verifyPageEnterTaxReliefAndContinue(taxReliefAmount: String) = {
    enterTaxReliefAmount(taxReliefAmount)
    checkYourAnswersCalculationsMap(getHeader(), taxReliefAmount)
    submitPage()
  }
}
