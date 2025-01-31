/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhoPaidAnnualAllowanceChargePage extends BasePage {

  def selectYou()           = driver.findElement(By.id("value_0")).click()
  def selectPensionScheme() = driver.findElement(By.id("value_1")).click()
  def selectBoth()          = driver.findElement(By.id("value_2")).click()

  def verifyPageSelectYouAndContinue() = {
    selectYou()
    checkYourAnswersAAPeriodMap(getHeader(), "You")
    submitPage()
  }
  def verifyPageSelectPensionSchemeAndContinue() = {
    selectPensionScheme()
    checkYourAnswersAAPeriodMap(getHeader(), "Pension Scheme")
    submitPage()
  }
  def verifyPageSelectBothAndContinue() = {
    selectBoth()
    checkYourAnswersAAPeriodMap(getHeader(), "Both")
    submitPage()
  }

}
