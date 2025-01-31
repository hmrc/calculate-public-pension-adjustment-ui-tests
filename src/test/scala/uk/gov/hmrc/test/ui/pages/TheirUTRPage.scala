/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
object TheirUTRPage extends BasePage {
  val utr = "1234567891"

  def enterUTR(): Unit =
    driver.findElement(By.id("value")).sendKeys(utr)

  def verifyPageEnterUTRAndContinue(): Unit = {
    enterUTR()
    checkYourAnswersCalculationsMap(getHeader(), utr)
    submitPage()
  }
  def verifyPageContinueWithoutTaxReference(): Unit = {
    checkYourAnswersCalculationsMap(getHeader(), "")
    submitPage()
  }
}
