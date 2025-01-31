/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object NewExcessPaidPage extends BasePage {

  def selectAnnualPaymentRadioButtonAndContinue(): Unit = {
    val text = "Annual payment"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectLumpSumRadioButtonAndContinue(): Unit = {
    val text = "Lump sum"
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

}
