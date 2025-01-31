/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ValueNewLtaChargePage extends BasePage {

  def enterNewLTAChargeMoreThanPreviousCharge() = {
    val text = "950000000"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }

  def enterNewLTAChargeLessThanPreviousCharge() = {
    val text = "100000000"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }

  def enterNewLTACharge() = {
    val text = "450000000"
    driver.findElement(By.id("value")).clear()
    driver.findElement(By.id("value")).sendKeys(text)
    checkYourAnswersLASMap(getHeader(), "£" + text)
  }

  def verifyPageEnterLTAChargeMoreThanPreviousChargeAndContinue() = {
    enterNewLTAChargeMoreThanPreviousCharge()
    submitPage()
  }

  def verifyPageEnterLTAChargeLessThanPreviousChargeAndContinue() = {
    enterNewLTAChargeLessThanPreviousCharge()
    submitPage()
  }

  def verifyPageEnterLTAChargeAndContinue() = {
    enterNewLTACharge()
    submitPage()
  }
}
