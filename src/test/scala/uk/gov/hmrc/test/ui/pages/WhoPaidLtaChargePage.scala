/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhoPaidLtaChargePage extends BasePage {

  def selectYou() = {
    driver.findElement(By.xpath("//input[@id='value_0']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_0']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }
  def selectPensionScheme() = {
    driver.findElement(By.xpath("//input[@id='value_1']")).click()
    val text = driver.findElement(By.xpath("//input[@id='value_1']/following-sibling::label")).getText.trim
    checkYourAnswersLASMap(getHeader(), text)
  }

  def selectYouAndClickOnContinue() = {
    selectYou()
    submitPage()
  }

  def selectPensionSchemeAndClickOnContinue() = {
    selectPensionScheme()
    submitPage()
  }
}
