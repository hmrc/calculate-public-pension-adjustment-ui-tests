/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
object TheirNINOTRNPage extends BasePage {

  def enterNINOTRN(): Unit =
    driver.findElement(By.id("value")).sendKeys("00348916RT")

  def verifyPageEnterNINOAndContinue(): Unit = {
    enterNINOTRN()
    submitPage()
  }
}
