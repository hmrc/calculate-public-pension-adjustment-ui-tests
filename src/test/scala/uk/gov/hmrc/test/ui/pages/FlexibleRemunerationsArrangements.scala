/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object FlexibleRemunerationsArrangements extends BasePage {

  def selectYesAndContinue() = {
    selectYesOption()
    submitPage()
  }

  def selectNoAndContinue() = {
    selectNoOption()
    submitPage()
  }

}
