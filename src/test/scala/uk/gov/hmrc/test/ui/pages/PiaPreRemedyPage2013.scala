/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object PiaPreRemedyPage2013 extends BasePage {
  def onPiaPreRemedyPage2013PageEnterValueAndContinue() =
    enterAmountAndClickContinue()

  def enterAmountAndClickContinue() = {
    enterAmount("130000000")
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }

  def enterAmountAndClickContinue(amount: String) = {
    enterAmount(amount)
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }
}
