/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ProtectionChangedNewReferencePage extends BasePage {

  def enterReference() = {
    val text = "R41AB5678TR2335"
    driver.findElement(By.xpath("//input[@id='value']")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), text)
  }

  def enterNewReference(reference: String) = {
    driver.findElement(By.xpath("//input[@id='value']")).sendKeys(reference)
    checkYourAnswersLASMap(getHeader(), reference)
  }

  def enterReferenceAndContinue(): Unit = {
    enterReference()
    submitPage()
  }

  def enterNewReferenceAndContinue(reference: String): Unit = {
    enterNewReference(reference)
    submitPage()
  }
}
