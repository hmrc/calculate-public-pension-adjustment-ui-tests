/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object WhenFlexiblyAccessPensionPage extends BasePage {

  def enterAnInvalidDateAndClickContinue() = {
    clearDate()
    enterDay("32")
    enterMonth("04")
    enterYear("2016")
    submitPage()
  }

  def enterAnInvalidMonthAndClickContinue() = {
    clearDate()
    enterDay("25")
    enterMonth("14")
    enterYear("2016")
    submitPage()
  }

  def enterAnInvalidYearAndClickContinue() = {
    clearDate()
    enterDay("25")
    enterMonth("14")
    enterYear("Y2016")
    submitPage()
  }

  def enterAFutureDateAndClickContinue() = {
    val (month, date, year) = getFutureDateTime()
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear(year.toString)
    submitPage()
  }

  def enterDateAndClickContinue(dateinFull: String) = {
    val (year, month, date) = parseDate(dateinFull)
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear(year.toString)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterAPreRemedyDateAndClickContinue() = {
    clearDate()
    enterDay("05")
    enterMonth("04")
    enterYear("2015")
    submitPage()
  }

  def enterValidPastDateAndClickContinue() = {
    clearDate()
    enterDay("06")
    enterMonth("04")
    enterYear("2015")
    submitPage()
  }

  def enterValidFutureDateAndClickContinue() = {
    val (month, date, year) = getCurrentDateTime()
    clearDate()
    enterDay(date.toString)
    enterMonth(month.toString)
    enterYear(year.toString)
    submitPage()
  }

  def enterFlexiAcessDateAndClickContinue(date: String, month: String, year: String) = {
    clearDate()
    enterDay(date)
    enterMonth(month)
    enterYear(year)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def verifyAllowedDatesAndContinue(): Unit = {
    enterValidPastDateAndClickContinue()
    clickBackButton()
    enterValidFutureDateAndClickContinue()
  }

  def verifyInvalidFlexiblyEnteredDate(errorMessage: String): Unit = {
    val actualErrorMessage = getInvalidFlexiblyAccessDateErrorMessage()
    assert(actualErrorMessage.contains(errorMessage))
  }
}
