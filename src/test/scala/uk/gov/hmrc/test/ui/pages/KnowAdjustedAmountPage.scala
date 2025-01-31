/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object KnowAdjustedAmountPage extends BasePage {
  def verifyPageSelectNoAndContinue() =
    selectNoAndContinueForAAPeriodPage()

  def verifyPageSelectYesAndContinue() =
    selectYesAndContinueForAAPeriodPage()
}
