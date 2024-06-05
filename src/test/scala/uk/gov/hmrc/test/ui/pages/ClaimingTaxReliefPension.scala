package uk.gov.hmrc.test.ui.pages

import uk.gov.hmrc.test.ui.pages.MemberMoreThanOnePensionPage.{selectYesAndContinueForAAPeriodPage, verifyPageSelectNoAndContinue, verifyPageSelectYesAndContinue}

object ClaimingTaxReliefPension extends BasePage {

  def verifyClaimingTaxReliefPensionSelectYesAndContinue()={
    selectYesOption()
    submitPage()
  }

  def verifyClaimingTaxReliefPensionSelectNoAndContinue()={
    selectNoOption()
    submitPage()
  }

}
