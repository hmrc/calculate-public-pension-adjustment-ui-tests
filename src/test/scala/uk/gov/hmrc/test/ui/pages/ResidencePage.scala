/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object ResidencePage extends BasePage {

  def verifyPageSelectYesAndContinue() =
    selectYesAndContinueCalculationsPage()
  def verifyPageSelectNoAndContinue()  =
    selectNoAndContinueCalculationsPage()
}
