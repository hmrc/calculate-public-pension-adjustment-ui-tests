/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object DoYouKnowPersonalAllowancePage extends BasePage {
  def verifyPageSelectNoAndContinue() =
    selectNoAndContinueForAAPeriodPage()

  def verifyPageSelectYesAndContinue() =
    selectYesAndContinueForAAPeriodPage()
}
