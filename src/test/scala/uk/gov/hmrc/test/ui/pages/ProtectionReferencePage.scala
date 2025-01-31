/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ProtectionReferencePage extends BasePage {

  def enterProtectionReferenceAndContinue(): Unit = {
    val text = "R41AB5678TR2335"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def enterNewProtectionReferenceAndContinue(reference: String): Unit = {
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(reference)
    checkYourAnswersLASMap(getHeader(), reference)
    submitPage()
  }

}
