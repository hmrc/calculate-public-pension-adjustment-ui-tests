/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.specs

import uk.gov.hmrc.test.ui.constants.Errors.TEXT_WITH_501_CHARACTERS
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class UserJourneyTests extends BaseSpec {

  Feature("Calculate public pension adjustment user journey") {

    /** Below journey covers 0,1,3(N),5 pages in the mural board* */
    Scenario("User Journey through resubmission 'NO' functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I click continue button")
      SavingsStatementPage.submitPage()

      Then("I Should see the error messages")
      SavingsStatementPage.validateRadioButtonError()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I click continue button")
      ResubmittingAdjustmentPage.submitPage()

      Then("I Should see the error messages")
      ResubmittingAdjustmentPage.validateRadioButtonError()

      When("I select option No and continue")
      ResubmittingAdjustmentPage.selectNoAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I click continue button")
      ReportingChangePage.submitPage()

      Then("I Should see the error messages")
      ReportingChangePage.validateReportingChangePageErrorsWhenNoCheckBoxSelected()
    }

    /** Below journey covers 0,1,3(Y),4,5 pages in the mural board* */
    Scenario("User journey through resubmission (Yes) functionality with maximum character length") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue()

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage()

      When("I click continue button")
      ReasonForResubmissionPage.submitPage()

      Then("I Should see the error messages")
      ReasonForResubmissionPage.validateReasonForResubmissionPageErrorsWhenNoTextAsReason()

      When("I enter resubmission reason with  503 characters")
      ReasonForResubmissionPage.enterResubmissionReason(TEXT_WITH_501_CHARACTERS)

      Then("I Should see only 500 characters in the resubmission reason text box")
      assert(ReasonForResubmissionPage.verifiedMaxCharacterLength())

      Then("I click continue")
      ReasonForResubmissionPage.submitPage()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

    }

    /** Below journey covers 0,1,3(Y),4,5(AA),6(Y),7 pages in the mural board* */
    Scenario("User journey through resubmission (Yes) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue()

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I click continue button")
      ScottishTaxpayerFrom2016Page.submitPage()

      Then("I Should see the error message")
      ScottishTaxpayerFrom2016Page.validateScottishTaxPayerRadioButtonError()

      When("I select yes and continue to next page")
      ScottishTaxpayerFrom2016Page.selectYesAndContinue()

      Then("I Should see the which-years-scottish-taxpayer page")
      WhichYearsScottishTaxpayer.onWhichYearsScottishTaxpayerPage()

      When("I click continue button")
      WhichYearsScottishTaxpayer.submitPage()

      Then("I Should see the error message")
      WhichYearsScottishTaxpayer.validateWhichYearsScottishTaxpayerErrorsWhenNoCheckBoxSelected()

    }

    /** Below journey covers 0,1,3(Y),4,5(AA),6(N),8(N),9,10(N),13 pages in the mural board* */
    Scenario("User journey through Scottish taxpayer from 6 April 2016 (No) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue()

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinue()

      Then("I Should see the paying-into-public-pension-scheme page")
      PayingIntoPublicPensionSchemePage.onPayingIntoPublicPensionSchemePage()

      When("I click continue button")
      PayingIntoPublicPensionSchemePage.submitPage()

      Then("I Should see the error message")
      PayingIntoPublicPensionSchemePage.validatePayingPSPSchemeRadioButtonError()

      When("I select no and continue to next page")
      PayingIntoPublicPensionSchemePage.selectNoAndContinue()

      Then("I Should see the when-stop-paying-public-pension page")
      WhenStopPayingPublicPensionPage.onWhenStopPayingPublicPensionPage()

      When("I click continue button")
      WhenStopPayingPublicPensionPage.submitPage()

      Then("I Should see the error message")
      WhenStopPayingPublicPensionPage.validateNoInputDateError()

      When("I enter an invalid date")
      WhenStopPayingPublicPensionPage.enterAnInvalidDateAndClickContinue()

      Then("I Should see the error message")
      WhenStopPayingPublicPensionPage.validateInvalidDateError()

      When("I enter a future date")
      WhenStopPayingPublicPensionPage.enterAFutureDateAndClickContinue()

      Then("I Should see the error message")
      WhenStopPayingPublicPensionPage.validateFutureDateError()

      When("I enter a pre-remedy date")
      WhenStopPayingPublicPensionPage.enterAPreRemedyDateAndClickContinue()

      Then("I Should see the error message")
      WhenStopPayingPublicPensionPage.validatePreRemedyDateError()

      When("I enter a valid last past date")
      WhenStopPayingPublicPensionPage.enterValidPastDateAndClickContinue()

      When("I click back button")
      CheckYourAnswersPage.clickBackButton()

      Then("I Should see the when-stop-paying-public-pension page")
      WhenStopPayingPublicPensionPage.onWhenStopPayingPublicPensionPage()

      When("I enter a valid future date")
      WhenStopPayingPublicPensionPage.enterValidFutureDateAndClickContinue()

      Then("I Should see the have-defined-contribution-pension page")
      HaveDefinedContributionPensionPage.onHaveDefinedContributionPensionPage()

      When("I select no and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinue()

      Then("I Should see the pay-tax-charge-from2015-2016 page")
      PayTaxChargeFrom20152016Page.onPayTaxChargeFrom20152016Page()
    }

    /** Below journey covers 0,1,3(Y),4,5(AA),6(N),8(Y),10(Y),11(Y),12,13 pages in the mural board* */
    Scenario("User journey through paying into a PSPS (yes) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue()

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinue()

      Then("I Should see the paying-into-public-pension-scheme page")
      PayingIntoPublicPensionSchemePage.onPayingIntoPublicPensionSchemePage()

      When("I select yes and continue to next page")
      PayingIntoPublicPensionSchemePage.selectYesAndContinue()

      Then("I Should see the have-defined-contribution-pension page")
      HaveDefinedContributionPensionPage.onHaveDefinedContributionPensionPage()

      When("I select yes and continue to next page")
      HaveDefinedContributionPensionPage.selectYesAndContinue()

      Then("I Should see the have-defined-contribution-pension page")
      HaveFlexiblyAccessedPensionPage.onHaveFlexiblyAccessedPensionPage()

      When("I select yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesAndContinue()

      Then("I Should see the when-flexibly-access-pension page")
      WhenFlexiblyAccessPensionPage.onWhenFlexiblyAccessPensionPage()

      Then("I validate all date errors")
      WhenFlexiblyAccessPensionPage.validateAllDateErrors()

      Then("I verify valid dates and click continue")
      WhenFlexiblyAccessPensionPage.verifyAllowedDatesAndContinue()

      Then("I Should see the pay-tax-charge-from2015-2016 page")
      PayTaxChargeFrom20152016Page.onPayTaxChargeFrom20152016Page()

    }

    /** Below journey covers 0,1,3(Y),4,5(AA),6(N),8(Y),10(Y),11(N),13,16,17,18 pages in the mural board* */
    Scenario("User journey through paying into a Flexi Access (yes) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue()

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinue()

      Then("I Should see the paying-into-public-pension-scheme page")
      PayingIntoPublicPensionSchemePage.onPayingIntoPublicPensionSchemePage()

      When("I select yes and continue to next page")
      PayingIntoPublicPensionSchemePage.selectYesAndContinue()

      Then("I Should see the have-defined-contribution-pension page")
      HaveDefinedContributionPensionPage.onHaveDefinedContributionPensionPage()

      When("I select yes and continue to next page")
      HaveDefinedContributionPensionPage.selectYesAndContinue()

      Then("I Should see the have-flexible-accessed-pension page")
      HaveFlexiblyAccessedPensionPage.onHaveFlexiblyAccessedPensionPage()

      When("I select No and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectNoAndContinue()

      Then("I Should see the pay-tax-charge-from2015-2016 page")
      PayTaxChargeFrom20152016Page.onPayTaxChargeFrom20152016Page()

      When("I select No and continue to next page")
      PayTaxChargeFrom20152016Page.selectNoAndContinue()

      Then("I Should see the pia-pre-remedy/2012 page")
      PiaPreRemedyPage2012.onPiaPreRemedyPage2012Page()

      When("I enter amount for 2012-2013 and click continue")
      PiaPreRemedyPage2012.enterAmountAndClickContinue()

      Then("I Should see the pia-pre-remedy/2013 page")
      PiaPreRemedyPage2013.onPiaPreRemedyPage2013Page()

      When("I enter amount for 2013-2014 and click continue")
      PiaPreRemedyPage2013.enterAmountAndClickContinue()

      Then("I Should see the pia-pre-remedy/2014 page")
      PiaPreRemedyPage2014.onPiaPreRemedyPage2014Page()

      When("I enter amount for 2014-2015 and click continue")
      PiaPreRemedyPage2014.enterAmountAndClickContinue()

    }

    /** Below journey covers 0,1,3(Y),4,5 pages in the mural board* */
    Scenario("LTA user journey through LTA> had a benefit crystallisation(Yes) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the what-you-will-need-lta page")
      WhatYouWillNeedLtaPage.onWhatYouWillNeedLtaPage()

      When("I click continue")
      WhatYouWillNeedLtaPage.clickContinueButton()

      Then("I Should see the had-benefit-crystallisation-event page")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinue()

      Then("I Should see the date-of-benefit-crystallisation-event page")
      DateOfBenefitCrystallisationEventPage.onDateOfBenefitCrystallisationEventPage()
    }

    /** Below journey covers 0,1,3(Y),4,5 pages in the mural board* */
    Scenario("LTA user journey through LTA> had a benefit crystallisation(No) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue()

      Then("I Should see the what-you-will-need-lta page")
      WhatYouWillNeedLtaPage.onWhatYouWillNeedLtaPage()

      When("I click continue")
      WhatYouWillNeedLtaPage.clickContinueButton()

      Then("I Should see the had-benefit-crystallisation-event page")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I select No and continue to next page")
      HadBenefitCrystallisationEventPage.selectNoAndContinue()

      Then("I Should see the not-able-to-use-this-service-lta page")
      NotAbleToUseThisServiceLtaPage.onNotAbleToUseThisServiceLtaPage()
    }

    /** Below journey covers 0,1,2 pages in the mural board* */
    Scenario("User Journey through savings statement 'NOT' received functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.loadPage

      When("I click start button")
      HomePage.clickStartButton()

      When("I select option No and continue")
      SavingsStatementPage.selectNoAndContinue()

      Then("I Should see the EligibilityPage page")
      EligibilityPage.onEligibilityPage()
    }
  }
}
