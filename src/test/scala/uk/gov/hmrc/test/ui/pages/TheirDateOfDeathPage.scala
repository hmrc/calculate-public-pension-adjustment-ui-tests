/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object TheirDateOfDeathPage extends BasePage {
  def enterDeathday() = {
    val (month, date, year) = getCurrentDateTime()
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear("2022")
  }
  def verifyPageEnterDateOfDeathAndContinue() = {
    enterDeathday()
    checkYourAnswersCalculationsMap(getHeader(), getDate())
    submitPage()
  }
}
