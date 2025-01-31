/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object LifetimeAllowancePercentageChangePage extends BasePage {
  def verifyLTAPercentageChangeSelectYesAndContinue(): Unit = {
    selectYesOption()
    submitPage()
  }

  def verifyLTAPercentageChangeSelectNoAndContinue(): Unit = {
    selectNoOption()
    submitPage()
  }
}
