/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

object Registered extends BasePage {

  val REGISTERED_PAGE_TITLE  =
    "Were you a member of a registered pension scheme between 6 April fromYear and 5 April toYear? - Calculate your public service pension adjustment - GOV.UK"
  val REGISTERED_PAGE_HEADER =
    "Were you a member of a registered pension scheme between 6 April fromYear and 5 April toYear?"
  def onRegisteredPageSelectNoAndContinue(fromYear: String, toYear: String) = {
    verifyPageUrl("annual-allowance/registered/" + toYear)
    selectNoOption()
    checkYourAnswersAASMap(
      "Member of a registered pension scheme between 6 April fromYear and 5 April toYear"
        .replaceAll("fromYear", fromYear)
        .replaceAll("toYear", toYear),
      selectedOption()
    )
    submitPage()
  }

  def onRegisteredPageSelectYesAndContinue(fromYear: String, toYear: String) = {
    verifyPageUrl("annual-allowance/registered/" + toYear)
    selectYesOption()
    checkYourAnswersAASMap(
      "Member of a registered pension scheme between 6 April fromYear and 5 April toYear"
        .replaceAll("fromYear", fromYear)
        .replaceAll("toYear", toYear),
      selectedOption()
    )
    submitPage()
  }
}
