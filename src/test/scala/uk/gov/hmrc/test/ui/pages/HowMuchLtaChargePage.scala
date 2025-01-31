/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object HowMuchLtaChargePage extends BasePage {

  def enterCharge() = {
    val text = "200000000"
    driver.findElement(By.xpath("//input[@id='value']")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }
  def verifyPageEnterChargeAmountAndContinue() = {
    enterCharge()
    submitPage()
  }

}
