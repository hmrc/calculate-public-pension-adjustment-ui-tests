/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object AnyLumpSumDeathBenefitsPage extends BasePage {
  def selectYesAndContinue() = {
    selectYesOption()
    submitPage()
  }
  def selectNoAndContinue() = {
    selectNoOption()
    submitPage()
  }
}
