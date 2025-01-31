/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PensionCreditReferencePage extends BasePage {

  def enterPensionCreditReferenceAndContinue(): Unit = {
    val reference = "1234567890ZXCVB"
    driver.findElement(By.id("value")).sendKeys(reference)
    checkYourAnswersLASMap(getHeader(), reference)
    submitPage()
  }

  def enterNewPensionCreditReferenceAndContinue(reference: String): Unit = {
    driver.findElement(By.id("value")).sendKeys(reference)
    checkYourAnswersLASMap(getHeader(), reference)
    submitPage()
  }

}
