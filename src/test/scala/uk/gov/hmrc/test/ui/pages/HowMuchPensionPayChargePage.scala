/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object HowMuchPensionPayChargePage extends BasePage {

  def enterPensionPay(pensionPay: String) = driver.findElement(By.id("value")).sendKeys(pensionPay)

  def verifyPageEnterPensionPayAndContinue(pensionPay: String) = {
    enterPensionPay(pensionPay)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }

}
