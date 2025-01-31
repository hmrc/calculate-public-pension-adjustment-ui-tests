/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PersonalAllowancePage extends BasePage {
  def verifyPageEnterPersonalAllowanceAndContinue(personalAllowance: String) = {
    enterPersonalAllowance(personalAllowance)
    submitPage()
  }
  def enterPersonalAllowance(personalAllowance: String) = driver.findElement(By.id("value")).sendKeys(personalAllowance)

}
