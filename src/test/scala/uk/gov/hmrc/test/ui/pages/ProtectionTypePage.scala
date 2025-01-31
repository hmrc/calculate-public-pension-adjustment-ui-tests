/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ProtectionTypePage extends BasePage {

  def selectEnhancedProtectionRadioButtonAndContinue(): Unit = {
    val text = "Enhanced protection"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

  def selectFixedProtection2016RadioButtonAndContinue(): Unit = {
    val text = "Fixed protection 2016"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }

}
