/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object ContributedOtherDbDcSchemePage extends BasePage {
  def verifyPageSelectYesAndContinue() =
    selectYesAndContinueForAAPeriodPage()

  def verifyPageSelectNoAndContinue() =
    selectNoAndContinueForAAPeriodPage()

}
