/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object PiaPreRemedyPage2015 extends BasePage {
  def onPiaPreRemedyPage2015PageEnterValueAndContinue() =
    enterAmountAndClickContinue()

  def enterAmountAndClickContinue() = {
    enterAmount("300000000")
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }

  def enterAmountAndClickContinue(amount: String) = {
    enterAmount(amount)
    checkYourAnswersAASMap(getHeader(), getEnteredAmount())
    submitPage()
  }
}
