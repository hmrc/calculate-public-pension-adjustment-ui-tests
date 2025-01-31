/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object DateOfBenefitCrystallisationEventPage extends BasePage {

  def enterBenefitCrystallisationDateAndContinue() = {
    clearDate()
    enterDay("25")
    enterMonth("06")
    enterYear("2017")
    checkYourAnswersLASMap(getHeader(), getDate())
    submitPage()
  }
}
