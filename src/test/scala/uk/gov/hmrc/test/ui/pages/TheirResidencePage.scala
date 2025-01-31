/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object TheirResidencePage extends BasePage {

  def verifyPageSelectYesAnContinue() =
    selectYesAndContinueCalculationsPage()
  def verifyPageSelectNoAnContinue()  =
    selectNoAndContinueCalculationsPage()
}
