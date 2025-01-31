/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object HowMuchTaxReliefPensionPage extends BasePage {
  def enterTaxReliefPensionAmount(pensionPay: String) = driver.findElement(By.id("value")).sendKeys(pensionPay)

  def verifyPageEnterPensionPayAndContinue(pensionPay: String) = {
    enterTaxReliefPensionAmount(pensionPay)
    submitPage()
  }
}
