/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object ToldChangeInLtaPercentagePage extends BasePage {
  def onToldChangeInLtaPercentagePageAndSelectYesAndContinue() =
    selectYesAndContinueForLTAPage()

  def onToldChangeInLtaPercentagePageAndSelectNoAndContinue() =
    selectNoAndContinueForLTAPage()
}
