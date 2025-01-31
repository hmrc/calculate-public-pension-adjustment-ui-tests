/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PIAAmountDecreaseOrNoChangePage extends BasePage {
  def selectYesAndContinue() = {
    driver.findElement(By.id("value_0")).click()
    submitPage()
  }

  def selectNoAndContinue() = {
    driver.findElement(By.id("value_1")).click()
    submitPage()
  }

  def selectIDontKnowAndContinue() = {
    driver.findElement(By.id("value_2")).click()
    submitPage()
  }
}
