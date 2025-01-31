/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object BankDetailsPage extends BasePage {
  val accountName   = "Teddy Dickson"
  val sortCode      = "207102"
  val accountNumber = "44311655"

  def enterBankDetails() = {
    driver.findElement(By.id("accountName")).sendKeys(accountName)
    driver.findElement(By.id("sortCode")).sendKeys(sortCode)
    driver.findElement(By.id("accountNumber")).sendKeys(accountNumber)
  }

  def verifyPageEnterBankDetailsClickContinue() = {
    enterBankDetails()
    submitPage()
  }

}
