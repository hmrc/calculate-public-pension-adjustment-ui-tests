/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ContactNumberPage extends BasePage {
  val contactNumber = "+44 808 157 0192"

  def verifyPageEnterContactNumberAndContinue() = {
    driver.findElement(By.id("value")).sendKeys(contactNumber)
    checkYourAnswersCalculationsMap(getHeader(), contactNumber)
    submitPage()
  }

  def verifyPageAndContinueWithoutContactNumber() = {
    checkYourAnswersCalculationsMap(getHeader(), "")
    submitPage()
  }
}
