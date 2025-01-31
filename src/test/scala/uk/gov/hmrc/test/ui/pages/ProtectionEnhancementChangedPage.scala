/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ProtectionEnhancementChangedPage extends BasePage {

  def selectProtectionAndClickOnContinue() =
    selectProtectionRadioButtonAndContinue()

  def selectEnhancementAndClickOnContinue() =
    selectEnhancementsRadioButtonAndContinue()

  def selectBothAndClickOnContinue() =
    selectBothRadioButtonAndContinue()

  def selectNoAndClickOnContinue() =
    selectNoRadioButtonAndContinue()

  def selectProtectionRadioButtonAndContinue(): Unit = {
    val text = "Protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectEnhancementsRadioButtonAndContinue(): Unit = {
    val text = "Enhancement"
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
