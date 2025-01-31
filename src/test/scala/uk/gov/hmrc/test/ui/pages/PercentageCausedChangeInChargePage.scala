/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PercentageCausedChangeInChargePage extends BasePage {

  def selectNewChargeRadioButtonAndContinue(): Unit = {
    val text = "New charge"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectIncreasedChargeRadioButtonAndContinue(): Unit = {
    val text = "Increased charge"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectNoneRadioButtonAndContinue(): Unit = {
    val text = "No charge"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }
}
