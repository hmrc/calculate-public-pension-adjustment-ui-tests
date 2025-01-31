/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ValueOfLumpSumPage extends BasePage {

  def enterLumpSum(value: String) =
    driver.findElement(By.id("value")).sendKeys(value)

  def enterLumpSumAndContinue(value: String) = {
    enterLumpSum(value)
    submitPage()
  }
}
