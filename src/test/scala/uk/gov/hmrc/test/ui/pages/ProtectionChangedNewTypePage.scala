/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ProtectionChangedNewTypePage extends BasePage {

  def selectEnhancedProtection() = {
    val text = "Enhanced protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectFixedProtection2016() = {
    val text = "Fixed protection 2016"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
  }
  def selectEnhancedProtectionAndContinue() = {
    selectEnhancedProtection()
    submitPage()
  }

  def selectFixedProtection2016AndContinue() = {
    selectFixedProtection2016()
    submitPage()
  }

}
