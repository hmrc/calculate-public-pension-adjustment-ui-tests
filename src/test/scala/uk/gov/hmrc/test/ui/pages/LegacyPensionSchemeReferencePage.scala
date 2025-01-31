/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object LegacyPensionSchemeReferencePage extends BasePage {

  def verifyPageEnterReferenceAndContinue(schemeName: String, taxRef: String) = {
    driver.findElement(By.id("value")).sendKeys(taxRef)
    checkYourAnswersCalculationsMap("Individual reference details for scheme " + schemeName + " / " + taxRef, "")
    checkYourAnswersCalculationsMap(getHeader(), taxRef)
    submitPage()
  }
}
