/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object LumpSumDeathBenefitsValuePage extends BasePage {
  def enterLumpSumAndContinue(lumpSumValue: String): Unit = {
    driver.findElement(By.id("value")).sendKeys(lumpSumValue)
    submitPage()
  }
}
