/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object LifetimeAllowanceNewChargePage extends BasePage {
  def verifyLifetimeAllowanceNewChargePageSelectYesAndContinue(): Unit = {
    selectYesOption()
    submitPage()
  }

  def verifyLifetimeAllowanceNewChargePageSelectNoAndContinue(): Unit = {
    selectNoOption()
    submitPage()
  }
}
