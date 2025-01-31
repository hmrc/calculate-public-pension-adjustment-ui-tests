/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object CheckYourAnswersPage extends BasePage {

  def verifyCheckYourAnswersPageAndContinue() =
    clickContinueButton()

  def clickChangeOnWhatAreYouReportingQuestion() = clickOnChangeLink(
    "Charges to be adjusted"
  )

}
