/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages
object AskedPensionSchemeToPayTaxCharge extends BasePage {

  def verifyPageSelectYesAndContinue(year: Int) = {
    selectYesOption()
    submitPage()
  }

  def verifyPageSelectNoAndContinue(year: Int) = {
    selectNoOption()
    submitPage()
  }

}
