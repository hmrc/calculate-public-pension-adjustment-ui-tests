/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object InternationalEnhancementReferencePage extends BasePage {

  def enterInternationalEnhancementReferenceAndContinue(): Unit = {
    val reference = "1234567890ASDFG"
    driver.findElement(By.id("value")).sendKeys(reference)
    checkYourAnswersLASMap(getHeader(), reference)
    submitPage()
  }

  def enterNewInternationalEnhancementReferenceAndContinue(reference: String): Unit = {
    driver.findElement(By.id("value")).sendKeys(reference)
    checkYourAnswersLASMap(getHeader(), reference)
    submitPage()
  }
}
