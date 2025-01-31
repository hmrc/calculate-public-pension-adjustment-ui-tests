/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object LifetimeAllowancePercentageIncreasePage extends BasePage {
  def verifyLTAPercentageIncreaseSelectYesAndContinue(): Unit = {
    selectYesOption()
    submitPage()
  }

  def verifyLTAPercentageIncreaseSelectNoAndContinue(): Unit = {
    selectNoOption()
    submitPage()
  }
}
