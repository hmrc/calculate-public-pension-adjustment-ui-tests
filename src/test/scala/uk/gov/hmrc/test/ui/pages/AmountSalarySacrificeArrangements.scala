/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object AmountSalarySacrificeArrangements extends BasePage {

  def enterSalarySacrificeAmount(salarySacrifice: String) =
    driver.findElement(By.id("value")).sendKeys(salarySacrifice)

  def enterSalarySacrificeAmountAndContinue(salarySacrifice: String) = {
    enterSalarySacrificeAmount(salarySacrifice: String)
    submitPage()
  }
}
