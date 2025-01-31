/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object ClaimingAdditionalTaxRateReliefPage extends BasePage {

  def verifyPageClickYesAndContinue() =
    selectYesAndContinueCalculationsPage()

  def verifyPageClickNoAndContinue() =
    selectNoAndContinueCalculationsPage()
}
