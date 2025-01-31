/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ThresholdIncomePage extends BasePage {

  def verify2017TO2020PageSelectYesAndContinue() = {
    driver.findElement(By.id("value_0")).click()
    submitPage()
  }

  def verify2017TO2020PageSelectNoAndContinue() = {
    driver.findElement(By.id("value_1")).click()
    submitPage()
  }

  def verify2021TO2023PageSelectYesAndContinue() = {
    driver.findElement(By.id("value_0")).click()
    submitPage()
  }

  def verify2021TO2023PageSelectNoAndContinue() = {
    driver.findElement(By.id("value_1")).click()
    submitPage()
  }

  def verifyPageSelectIdoNotKnowAndContinue() = {
    driver.findElement(By.id("value_2")).click()
    submitPage()
  }

}
