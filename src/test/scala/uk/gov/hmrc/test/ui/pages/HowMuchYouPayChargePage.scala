/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object HowMuchYouPayChargePage extends BasePage {

  def enterYouPay(pensionPay: String) = driver.findElement(By.id("value")).sendKeys(pensionPay)

  def verifyPageEnterYouPayAndContinue(pensionPay: String) = {
    enterYouPay(pensionPay)
    submitPage()
  }

}
