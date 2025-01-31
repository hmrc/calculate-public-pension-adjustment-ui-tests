/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ReformPensionSchemeReferencePage extends BasePage {
  val pensionRef = "TAX00000629RTED"

  def verifyPageEnterReferenceAndContinue(taxRef: String) = {
    driver.findElement(By.id("value")).sendKeys(pensionRef)
    checkYourAnswersCalculationsMap(getHeader(), pensionRef)
    submitPage()
  }
}
