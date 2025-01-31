/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object HowExcessWasPaidPage extends BasePage {

  def selectAnnualPayment() = {
    driver.findElement(By.xpath("//input[@id='value_0']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_0']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectLumpSumPayment() = {
    driver.findElement(By.xpath("//input[@id='value_1']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_1']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectBothPayment() = {
    driver.findElement(By.xpath("//input[@id='value_2']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_2']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectRadioButtonAnnualPaymentAndContinue() = {
    selectAnnualPayment()
    submitPage()
  }

  def selectRadioButtonLumpSumAndContinue() = {
    selectLumpSumPayment()
    submitPage()
  }

  def selectRadioButtonBothAndContinue() = {
    selectBothPayment()
    submitPage()
  }
}
