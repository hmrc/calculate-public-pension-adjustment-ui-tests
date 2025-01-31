/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object BlindPersonsAllowanceAmount extends BasePage {

  def enterBlindPersonsAllowanceAmount(blindPersonsAllowanceAmount: String) = {
    driver.findElement(By.id("value")).sendKeys(blindPersonsAllowanceAmount)
    submitPage()
  }
}
