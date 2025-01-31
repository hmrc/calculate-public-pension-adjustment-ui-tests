/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PensionSchemeDetailsPage extends BasePage {

  def enterPensionSchemeName(schemeName: String) = {
    driver.findElement(By.id("schemeName")).clear()
    driver.findElement(By.id("schemeName")).sendKeys(schemeName)
  }
  def enterSchemeTaxReference(taxReference: String) = {
    driver.findElement(By.id("schemeTaxRef")).clear()
    driver.findElement(By.id("schemeTaxRef")).sendKeys(taxReference)
  }
  def enterTaxInformationAndContinue(
    schemeName: String,
    taxReference: String
  ) = {
    enterPensionSchemeName(schemeName)
    enterSchemeTaxReference(taxReference)
    checkYourAnswersAAPeriodMap(getHeader(), schemeName + " / " + taxReference)
    submitPage()
  }

}
