/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object WhenDidYouAskPensionSchemeToPay extends BasePage {

  def enterSchemePaymentDate() = {
    clearDate()
    enterDay("1")
    enterMonth("1")
    enterYear("2022")
  }

  def verifyPageEnterDateAndContinue(year: Int) = {
    enterSchemePaymentDate()
    submitPage()
  }

}
