/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SchemePaidLtaChargePage extends BasePage {

  def enterPensionScheme(pensionName: String) = {
    driver.findElement(By.xpath("//input[@id='name']")).clear()
    driver.findElement(By.xpath("//input[@id='name']")).sendKeys(pensionName)
  }

  def enterPensionSchemeTaxReference(taxRef: String) = {
    driver.findElement(By.xpath("//input[@id='taxRef']")).clear()
    driver.findElement(By.xpath("//input[@id='taxRef']")).sendKeys(taxRef)
  }

  def getPensionSchemeName() = driver.findElement(By.xpath("//input[@id='name']")).getAttribute("value")
  def getTaxReference()      = driver.findElement(By.xpath("//input[@id='taxRef']")).getAttribute("value")

  def enterPensionSchemeInformationAndContinue(taxRef: String, pensionName: String) = {
    enterPensionScheme(taxRef)
    enterPensionSchemeTaxReference(pensionName)
    checkYourAnswersLASMap(getHeader(), getPensionSchemeName() + " / " + getTaxReference())
    submitPage()
  }
}
