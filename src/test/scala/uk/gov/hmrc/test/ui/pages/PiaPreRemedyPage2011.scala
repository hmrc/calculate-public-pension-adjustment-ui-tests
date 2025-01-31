/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object PiaPreRemedyPage2011 extends BasePage {
  def onPiaPreRemedyPage2011PageEnterValueAndContinue() =
    enterAmountAndClickContinue()

  def enterAmountAndClickContinue() = {
    enterAmount("110000000")
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }

  def enterAmountAndClickContinue(amount: String) = {
    enterAmount(amount)
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }
}
