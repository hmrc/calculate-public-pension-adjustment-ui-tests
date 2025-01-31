/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SchemePaidExtraLtaChargePage extends BasePage {

  val pension_scheme_name =
    "pensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepe nsions"

  def enterPensionScheme(schemeName: String) = {
    driver.findElement(By.id("name")).clear()
    driver.findElement(By.id("name")).sendKeys(schemeName)
  }

  def enterPensionSchemeTaxReference(taxRef: String) = {
    driver.findElement(By.id("taxRef")).clear()
    driver.findElement(By.id("taxRef")).sendKeys(taxRef)
  }

  def getPensionSchemeName() = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value")

  def getTaxReference() = driver.findElement(By.id("taxRef")).getAttribute("value")

  def enterPensionSchemeInformationAndContinue(schemeName: String, taxRef: String) = {
    enterPensionScheme(schemeName)
    enterPensionSchemeTaxReference(taxRef)
    checkYourAnswersLASMap(getHeader(), getTaxReference())
    submitPage()
  }
}
