/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object CheckYourAnswersCalculationJourneyPage extends BasePage {

  def verifyCheckYourAnswersPageAndContinue() =
    clickContinueButton()

  def clickChangeOnWhatAreYouReportingQuestion() = clickOnChangeLink(
    "What are you reporting"
  )

}
