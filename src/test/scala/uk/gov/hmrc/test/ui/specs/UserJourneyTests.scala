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
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I click continue button")
      ResubmittingAdjustmentPage.submitPage()

      Then("I Should see the error messages")
      ResubmittingAdjustmentPage.validateRadioButtonError()

      When("I select option No and continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I click continue button")
      ReportingChangePage.submitPage()

      Then("I Should see the error messages")
      ReportingChangePage.validateReportingChangePageErrorsWhenNoCheckBoxSelected()

      When("I click sign out from the page")
      ReportingChangePage.signOutPage()
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
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

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

      When("I click sign out from the page")
      ReportingChangePage.signOutPage()

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
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I click continue button")
      ScottishTaxpayerFrom2016Page.submitPage()

      Then("I Should see the error message")
      ScottishTaxpayerFrom2016Page.validateScottishTaxPayerRadioButtonError()

      When("I select yes and continue to next page")
      ScottishTaxpayerFrom2016Page.selectYesAndContinueForAASPage()

      Then("I Should see the which-years-scottish-taxpayer page")
      WhichYearsScottishTaxpayer.onWhichYearsScottishTaxpayerPage()

      When("I click continue button")
      WhichYearsScottishTaxpayer.submitPage()

      Then("I Should see the error message")
      WhichYearsScottishTaxpayer.validateWhichYearsScottishTaxpayerErrorsWhenNoCheckBoxSelected()

      When("I click sign out from the page")
      WhichYearsScottishTaxpayer.signOutPage()

    }

    /** Below journey covers 0,1,3(Y),4,5(AA),6(N),8(N),9,10(N),13 pages in the mural board* */
    Scenario("User journey through Annual allowance functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

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

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      Then("I Should see the paying-into-public-pension-scheme page")
      PayingIntoPublicPensionSchemePage.onPayingIntoPublicPensionSchemePage()

      When("I click continue button")
      PayingIntoPublicPensionSchemePage.submitPage()

      Then("I Should see the error message")
      PayingIntoPublicPensionSchemePage.validatePayingPSPSchemeRadioButtonError()

      When("I select no and continue to next page")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

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
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      Then("I Should see the pay-tax-charge-from2015-2016 page")
      PayTaxChargeFrom20152016Page.onPayTaxChargeFrom20152016Page()

      When("I select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I Should see the Task list page")
      TaskListPage.onTaskListPage()

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

      When("I click change details of your AA background")
      TaskListPage.clickChangeDetailsOfYourAA()

      When("I click on when did you stop paying into PSPS")
      CheckYourAnswersAnnualAllowanceSetupPage.clickChangeOnWhenDidYouStopPaying()

      When("I update stop paying date")
      WhenStopPayingPublicPensionPage.enterValid2017_2018_DateAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2018 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2018")

      When("I click change details of your AA background")
      TaskListPage.clickChangeDetailsOfYourAA()

      When("I click on when did you stop paying into PSPS")
      CheckYourAnswersAnnualAllowanceSetupPage.clickChangeOnWhenDidYouStopPaying()

      When("I update stop paying date")
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2017 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      When("I click change details of your AA background")
      TaskListPage.clickChangeDetailsOfYourAA()

      When("I click on when did you stop paying into PSPS")
      CheckYourAnswersAnnualAllowanceSetupPage.clickChangeOnWhenDidYouStopPaying()

      When("I update stop paying date")
      WhenStopPayingPublicPensionPage.enterValidFutureDateAndClickContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

      When("I click sign out from the page")
      PayTaxChargeFrom20152016Page.signOutPage()
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
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

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

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForGSPage()

      Then("I Should see the paying-into-public-pension-scheme page")
      PayingIntoPublicPensionSchemePage.onPayingIntoPublicPensionSchemePage()

      When("I select yes and continue to next page")
      PayingIntoPublicPensionSchemePage.selectYesAndContinueForGSPage()

      Then("I Should see the have-defined-contribution-pension page")
      HaveDefinedContributionPensionPage.onHaveDefinedContributionPensionPage()

      When("I select yes and continue to next page")
      HaveDefinedContributionPensionPage.selectYesAndContinueForGSPage()

      Then("I Should see the have-defined-contribution-pension page")
      HaveFlexiblyAccessedPensionPage.onHaveFlexiblyAccessedPensionPage()

      When("I select yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesAndContinueForGSPage()

      Then("I Should see the when-flexibly-access-pension page")
      WhenFlexiblyAccessPensionPage.onWhenFlexiblyAccessPensionPage()

      Then("I validate all date errors")
      WhenFlexiblyAccessPensionPage.validateAllDateErrors()

      Then("I verify valid dates and click continue")
      WhenFlexiblyAccessPensionPage.verifyAllowedDatesAndContinue()

      Then("I Should see the pay-tax-charge-from2015-2016 page")
      PayTaxChargeFrom20152016Page.onPayTaxChargeFrom20152016Page()

      When("I click sign out from the page")
      PayTaxChargeFrom20152016Page.signOutPage()

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
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

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

      Then("I Should see the check your answers page with user selected values")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I Should see the scottish-taxpayer-from-2016 page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      Then("I Should see the paying-into-public-pension-scheme page")
      PayingIntoPublicPensionSchemePage.onPayingIntoPublicPensionSchemePage()

      When("I select yes and continue to next page")
      PayingIntoPublicPensionSchemePage.selectYesAndContinueForAASPage()

      Then("I Should see the have-defined-contribution-pension page")
      HaveDefinedContributionPensionPage.onHaveDefinedContributionPensionPage()

      When("I select yes and continue to next page")
      HaveDefinedContributionPensionPage.selectYesAndContinueForAASPage()

      Then("I Should see the have-flexible-accessed-pension page")
      HaveFlexiblyAccessedPensionPage.onHaveFlexiblyAccessedPensionPage()

      When("I select No and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectNoAndContinueForAASPage()

      Then("I Should see the pay-tax-charge-from2015-2016 page")
      PayTaxChargeFrom20152016Page.onPayTaxChargeFrom20152016Page()

      When("I select No and continue to next page")
      PayTaxChargeFrom20152016Page.selectNoAndContinueForAASPage()

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

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersAnnualAllowanceSetupPage.signOutPage()
    }

    /** Below journey covers 4.1,4.3,4.4,4.5,4.6,4.7,4.8 pages in the mural board* */
    Scenario("LTA user journey through LTA> had a benefit crystallisation(Yes) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      Then("I Should see the had-benefit-crystallisation-event page")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      Then("I Should see the date-of-benefit-crystallisation-event page")
      DateOfBenefitCrystallisationEventPage.onDateOfBenefitCrystallisationEventPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargPage.onPercentageCausedChangeInChargPage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargPage.selectNewChargeRadioButtonAndContinue()

      When("I click sign out from the page")
      PercentageCausedChangeInChargPage.signOutPage()

      /*Then("I Should see the lta-protection-or-enhancements page")
      LtaProtectionOrEnhancementsPage.onLtaProtectionOrEnhancementsPage()

      When("I select any radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectProtectionRadioButtonAndContinue()

      Then("I Should see the protection-type page")
      ProtectionTypePage.onProtectionTypePage()

      When("I select any radio button and click continue")
      ProtectionTypePage.selectEnhancedProtectionRadioButtonAndContinue()

      Then("I Should see the protection-reference page")
      ProtectionReferencePage.onProtectionReferencePage()

      When("I enter protection reference and click continue")
      ProtectionReferencePage.enterProtectionReferenceAndContinue()*/

    }

    /** Below journey covers 4.1,4.3,4.4,4.5,4.6,4.7,4.8 pages in the mural board* */
    Scenario("LTA user journey through negative tests set 1") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify only lifetime allowance section is displayed")
      TaskListPage.isLifeTimeAllowanceTitleDisplayed()
      TaskListPage.isAnnualAllowanceTitleNotDisplayed()

      Then("I verify the status of LA - details of your event is NOT STARTED")
      assert(TaskListPage.getLADetailsOfYourEventStatus() == TaskListPage.NOT_STARTED)

      When("I click on setup questions link")
      TaskListPage.clickOnChangeSetupQuestions()

      When("I click on 'What are you reporting' question")
      CheckYourAnswersPage.clickChangeOnWhatAreYouReportingQuestion()

      When("I select only annual allowance check box")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      Then("I verify check your answers page with the updated information and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify check your answers page with the updated information and click continue")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I click back button")
      ScottishTaxpayerFrom2016Page.clickBackButton()

      When("I click change on what are ypu reporting question")
      CheckYourAnswersPage.clickChangeOnWhatAreYouReportingQuestion()

      When("I select only life time allowance on reporting change page")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      Then("I verify check your answers page with the updated information and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify only lifetime allowance section is displayed")
      TaskListPage.isLifeTimeAllowanceTitleDisplayed()
      TaskListPage.isAnnualAllowanceTitleNotDisplayed()

      Then("I verify the status of LA - details of your event is NOT STARTED")
      assert(TaskListPage.getLADetailsOfYourEventStatus() == TaskListPage.NOT_STARTED)

      When("I click sign out from the page")
      TaskListPage.signOutPage()

    }

    /** Below journey covers 4.1,4.3,4.4,4.5,4.6,4.7,4.8 pages in the mural board* */
    Scenario("LTA user journey through negative tests set 2") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      //######### Sandip
      Then("I verify HadBenefitCrystallisationEventPage information")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I click back button")
      HadBenefitCrystallisationEventPage.clickBackButton()

      Then("I verify the status of LA - details of your event is NOT STARTED")
      assert(TaskListPage.getLADetailsOfYourEventStatus() == TaskListPage.NOT_STARTED)

      When("I click on 'Add details of your event' on lifetime allowance section")
      TaskListPage.clickAddDetailsForLifetimeAllowance()

      When("I select yes and click continue")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      Then("I verify DateOfBenefitCrystallisationEventPage page")
      DateOfBenefitCrystallisationEventPage.onDateOfBenefitCrystallisationEventPage()

      When("I click back button")
      DateOfBenefitCrystallisationEventPage.clickBackButton()

      When("I click back button")
      HadBenefitCrystallisationEventPage.clickBackButton()

      Then("I verify the status of LA - details of your event is IN Progress")
      assert(TaskListPage.getLADetailsOfYourEventStatus() == TaskListPage.IN_PROGRESS)

      When("I click on change setup questions")
      TaskListPage.clickOnChangeSetupQuestions()

      When("I click on change 'What are you reporting?' question")
      CheckYourAnswersPage.clickChangeOnWhatAreYouReportingQuestion()

      When("I select only 'Annual allowance' and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      Then("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify ScottishTaxpayerFrom2016Page page")
      ScottishTaxpayerFrom2016Page.onScottishTaxpayerFrom2016Page()

      When("I click back button")
      ScottishTaxpayerFrom2016Page.clickBackButton()

      When("I click on change 'What are you reporting?' question")
      CheckYourAnswersPage.clickChangeOnWhatAreYouReportingQuestion()

      When("I select only 'Lifetime allowance' and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      Then("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify only lifetime allowance section is displayed")
      TaskListPage.isLifeTimeAllowanceTitleDisplayed()
      TaskListPage.isAnnualAllowanceTitleNotDisplayed()

      Then("I verify the status of LA - details of your event is NOT STARTED")
      assert(TaskListPage.getLADetailsOfYourEventStatus() == TaskListPage.IN_PROGRESS)

      When("I click change details of your events link")
      TaskListPage.clickChangeDetailsForLifetimeAllowance()

      Then("I verify DateOfBenefitCrystallisationEventPage page")
      DateOfBenefitCrystallisationEventPage.onDateOfBenefitCrystallisationEventPage()

      When("I click sign out from the page")
      DateOfBenefitCrystallisationEventPage.signOutPage()

      /**
        * click Add details of your events --
        * select Yes on Have you had a benefit crystallisation event in
        * go back
        * verify cya page status is inprogress
        * go back
        * change to annual allowance
        * verify cya and continue
        * landed to Were you ever a Scottish
        * go back
        * change to life time allowance
        * verify status inprogress
        * click Add details of your events NOT STARTED
        * landed to What was the date of the
        */
    }

    /** Below journey covers 4.1,4.3,4.4,4.5 pages in the mural board */
    Scenario(
      "LTA user journey through LTA> been told about a change in the lifetime allowance percentage(No) functionality",
      ZapTests
    ) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      Then("I Should see the had-benefit-crystallisation-event page")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      Then("I Should see the date-of-benefit-crystallisation-event page")
      DateOfBenefitCrystallisationEventPage.onDateOfBenefitCrystallisationEventPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select no and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectNoAndContinue()

      Then("I Should see the not-able-to-use-this-service-lta page")
      NotAbleToUseThisServiceLtaPage.onNotAbleToUseThisServiceLtaPage()

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()
    }

    /** Below journey covers 4.1,4.2 pages in the mural board* */
    Scenario("LTA user journey through LTA> had a benefit crystallisation(No) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I clear all selected options ")
      ReportingChangePage.clearAllOptions()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      Then("I Should see the had-benefit-crystallisation-event page")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I select No and continue to next page")
      HadBenefitCrystallisationEventPage.selectNoAndContinueForLTAPage()

      Then("I Should see the not-able-to-use-this-service-lta page")
      NotAbleToUseThisServiceLtaPage.onNotAbleToUseThisServiceLtaPage()

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()
    }

    /** Below journey covers 0,1,2 pages in the mural board* */
    Scenario("User Journey through savings statement 'NOT' received functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.loadPage()

      When("I click start button")
      HomePage.clickStartButton()

      When("I select option No and continue")
      SavingsStatementPage.selectNoAndContinueForGSPage()

      Then("I Should see the EligibilityPage page")
      EligibilityPage.onEligibilityPage()

      When("I click sign out from the page")
      EligibilityPage.signOutPage()
    }
  }
}
