/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object TheirDOBPage extends BasePage {

  def enterBirthday() = {
    clearDate()
    enterDay("28")
    enterMonth("11")
    enterYear("1964")
  }
  def verifyPageEnterBirthdayAndContinue() = {
    enterBirthday()
    checkYourAnswersCalculationsMap(getHeader(), getDate())
    submitPage()
  }
}
