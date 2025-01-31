/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object AddAnotherSchemePage extends BasePage {

  def verifyPageSelectYesAndContinue() =
    selectYesAndContinueForAAPeriodPage()

  def verifyPageSelectNoAndContinue() =
    selectNoAndContinueForAAPeriodPage()
}
