/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object NewValueOfAnnualPaymentPage extends BasePage {

  def enterAnnualPayment(value: String) =
    driver.findElement(By.id("value")).sendKeys(value)

  def enterAnnualPaymentAndContinue(value: String) = {
    enterAnnualPayment(value)
    submitPage()
  }
}
