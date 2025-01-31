/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object OtherSchemeLTANotification extends BasePage {
  def verifyPageSelectYesAndContinue(): Unit = {
    selectYesOption()
    submitPage()
  }

  def verifyPageSelectNoAndContinue(): Unit = {
    selectNoOption()
    submitPage()
  }
}
