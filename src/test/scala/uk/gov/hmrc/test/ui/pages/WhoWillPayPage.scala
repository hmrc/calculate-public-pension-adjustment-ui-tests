/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhoWillPayPage extends BasePage {

  def selectPensionScheme() = driver.findElement(By.id("value_1")).click()
  def selectYou()           = driver.findElement(By.id("value_0")).click()
  def verifyPageSelectYouAndContinue(year: Int) = {
    selectYou()
    submitPage()
  }

  def verifyPageSelectPensionSchemeAndContinue(year: Int) = {
    selectPensionScheme()
    submitPage()
  }
}
