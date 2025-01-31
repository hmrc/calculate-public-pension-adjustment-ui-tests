/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SubmitPensionSchemeDetailsPage extends BasePage {

  def enterPensionSchemeName()         = driver.findElement(By.id("pensionSchemeName")).sendKeys("Pension Scheme 1")
  def enterPensionSchemeTaxReference() = driver.findElement(By.id("pensionSchemeTaxReference")).sendKeys("00348916LR")

  def verifyPageEnterPensionSchemeInformationAndContinue() = {
    enterPensionSchemeName()
    enterPensionSchemeTaxReference()
    submitPage()
  }
}
