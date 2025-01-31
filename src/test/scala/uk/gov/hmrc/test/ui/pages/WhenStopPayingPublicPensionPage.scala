/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object WhenStopPayingPublicPensionPage extends BasePage {

  def enterAnInvalidDateAndClickContinue() = {
    clearDate()
    enterDay("32")
    enterMonth("04")
    enterYear("2016")
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
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValidPre2015DateAndClickContinue() = {
    clearDate()
    enterDay("08")
    enterMonth("07")
    enterYear("2015")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterStopPayingDateAndClickContinue(date: String, month: String, year: String) = {
    clearDate()
    enterDay(date)
    enterMonth(month)
    enterYear(year)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValid2017_2018_DateAndContinue() = {
    clearDate()
    enterDay("06")
    enterMonth("04")
    enterYear("2017")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValid2016_2017_DateAndContinue() = {
    clearDate()
    enterDay("05")
    enterMonth("04")
    enterYear("2017")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValidFutureDateAndClickContinue() = {
    clearDate()
    enterDay("05")
    enterMonth("04")
    enterYear("2022")
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterValidDateAndClickContinue(day: String, month: String, year: String) = {
    clearDate()
    enterDay(day)
    enterMonth(month)
    enterYear(year)
    checkYourAnswersAASMap(getHeader(), getDate())
    submitPage()
  }

  def enterAFutureDateAndClickContinue() = {
    clearDate()
    enterDay("06")
    enterMonth("04")
    enterYear("2022")
    submitPage()
  }
}
