/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TaxReliefPage extends BasePage {
  def verifyPageEnterTaxReliefPageAndContinue(taxRelief: String) = {
    enterTaxReliefPage(taxRelief)
    submitPage()
  }

  def enterTaxReliefPage(taxRelief: String) = driver.findElement(By.id("value")).sendKeys(taxRelief)

}
