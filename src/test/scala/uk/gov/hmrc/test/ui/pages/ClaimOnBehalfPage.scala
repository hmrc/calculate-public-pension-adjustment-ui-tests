/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object ClaimOnBehalfPage extends BasePage {

  def verifyPageSelectYesAndContinue() = {
    Thread.sleep(7000)
    selectYesAndContinueCalculationsPage()
  }
  def verifyPageSelectNoAndContinue() = {
    Thread.sleep(7000)
    selectNoAndContinueCalculationsPage()
  }
}
