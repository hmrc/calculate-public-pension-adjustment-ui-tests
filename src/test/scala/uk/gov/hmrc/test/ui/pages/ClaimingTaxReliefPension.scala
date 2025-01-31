/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object ClaimingTaxReliefPension extends BasePage {

  def verifyClaimingTaxReliefPensionSelectYesAndContinue() = {
    selectYesOption()
    submitPage()
  }

  def verifyClaimingTaxReliefPensionSelectNoAndContinue() = {
    selectNoOption()
    submitPage()
  }

}
