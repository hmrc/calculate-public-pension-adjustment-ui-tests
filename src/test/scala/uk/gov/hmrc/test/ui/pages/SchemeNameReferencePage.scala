/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SchemeNameReferencePage extends BasePage {

  def enterSchemeName() =
    driver.findElement(By.id("name")).sendKeys("TTTTT")

  def enterSchemeReference() =
    driver.findElement(By.id("taxRef")).sendKeys("00348916BA")

  def enterSchemeNameReferenceAndContinue() = {
    enterSchemeName()
    enterSchemeReference()
    submitPage()
  }

  def enterSchemeNameReferenceAndContinue(schemeName: String, taxRef: String) = {
    enterSchemeName(schemeName)
    enterSchemeReference(taxRef)
    submitPage()
  }

  def enterSchemeName(schemeName: String) =
    driver.findElement(By.id("name")).sendKeys(schemeName)

  def enterSchemeReference(taxRef: String) =
    driver.findElement(By.id("taxRef")).sendKeys(taxRef)
}
