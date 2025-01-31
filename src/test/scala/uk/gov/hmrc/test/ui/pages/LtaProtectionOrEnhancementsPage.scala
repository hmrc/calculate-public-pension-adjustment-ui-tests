/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object LtaProtectionOrEnhancementsPage extends BasePage {

  def selectProtectionRadioButtonAndContinue(): Unit = {
    val text = "Protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectEnhancementsRadioButtonAndContinue(): Unit = {
    val text = "Enhancements"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectBothRadioButtonAndContinue(): Unit = {
    val text = "Both"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectNoRadioButtonAndContinue(): Unit = {
    val text = "No"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }
}
