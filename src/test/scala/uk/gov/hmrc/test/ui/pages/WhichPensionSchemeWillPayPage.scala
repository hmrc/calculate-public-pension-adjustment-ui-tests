/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhichPensionSchemeWillPayPage extends BasePage {

  def selectPrivatePensionScheme() = driver.findElement(By.xpath("//input[@value='Private pension scheme']")).click()

  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//input[contains(@value,'" + pensionScheme + "')]")).click()

  def verifyPageSelectPrivatePSAndContinue(year: Int) = {
    selectPrivatePensionScheme()
    submitPage()
  }

  def verifyPageSelectPSAndContinue(pensionScheme: String, year: Int) = {
    selectPensionScheme(pensionScheme)
    submitPage()
  }
}
