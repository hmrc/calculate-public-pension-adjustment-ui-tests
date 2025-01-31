/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object BlindPersonAllowance extends BasePage {

  def verifyClaimingBlindPersonAllowanceSelectYesAndContinue() = {
    selectYesOption()
    submitPage()
  }

  def verifyClaimingBlindPersonAllowanceSelectNoAndContinue() = {
    selectNoOption()
    submitPage()
  }

}
