/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object NewEnhancementTypePage extends BasePage {

  def selectInternationalEnhancementRadioButtonAndContinue(): Unit = {
    val text = "International enhancement"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectPensionCreditRadioButtonAndContinue(): Unit = {
    val text = "Pension credit"
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
