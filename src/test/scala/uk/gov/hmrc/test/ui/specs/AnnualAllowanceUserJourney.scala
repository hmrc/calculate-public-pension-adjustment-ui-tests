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

import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages.{MemberMoreThanOnePensionPage, _}
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
class AnnualAllowanceUserJourney extends BaseSpec {

  Feature("Calculate public pension adjustment Annual Allowance user journey") {

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(Y),2.2,2.3 pages in the mural board* */
    Scenario("AA User Scottish tax payer(Yes) journey ", ZapTests) {
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
      signOutPage()

    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList pages in the mural board* */
    Scenario("AA User Scottish tax payer(No) journey", ZapTests) {
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
      HaveDefinedContributionPensionPage.clickBackButton()

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
      TaskListPage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(Y),2.5(Y),2.6(Y),2.7,2.8 pages in the mural board* */
    Scenario("AA User Paying in to PS(Yes) journey", ZapTests) {
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

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(Y),2.5(Y),2.6(N),2.8(N),2.9,2.10,2.11,2.12,TaskList pages in the mural board* */
    Scenario("AA User define contribution scheme(Yes) journey", ZapTests) {
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

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

      When("I click sign out from the page")
      TaskListPage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.9,3.2.1,  pages in the mural board*
      */
    Scenario("Annual allowance AA info loop user journey - was a charge paid (No) ", ZapTests) {
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

      /** AA Loop */
      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-pre", "0")

      /** Verify header and title */
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-pre", "1")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-post", "0")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue("2016-post", "1")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-post", "1", "PensionScheme3", "00348916AB")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "1",
        "700000000",
        "800000000",
        "PensionScheme3"
      )
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "1")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
      CheckYourAnswersAnnualAllowancePeriodPage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      */
    Scenario("Annual allowance AA info loop user journey - was a charge paid(Yes) ", ZapTests) {
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

      /** AA Loop */
      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue("2016-pre", "0", "6 April 2015 to July 8 2015")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      /** Verify header and title */
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", "0")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
        "2016-post",
        "0",
        "9 July 2015 to 5 April 2016"
      )
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-post", "0", "200000000")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
      CheckYourAnswersAnnualAllowancePeriodPage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      */
    Scenario("Annual allowance AA info loop user journey - Who paid(Both) ", ZapTests) {
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

      /** AA Loop */
      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to July 8 2015")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** Verify header and title */
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", "0")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue(
        "2016-post",
        "0",
        "9 July 2015 to 5 April 2016"
      )
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-post", "0", "100000000")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-post", "0", "200000000")

      /** Verify header and title */
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-post", "0")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "1", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "1",
        "300000000",
        "400000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", "1")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-post", "1", "200000000")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage()

      When("I click back sign and click Add details for 2017 to 2018")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2017To2018()

      When("I verify what-you-will-need-aa/2018 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2018Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage()

      When("I click back sign and click Add details for 2018 to 2019")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2018To2019()

      When("I verify what-you-will-need-aa/2019 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2019Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage()

      When("I click back sign and click Add details for 2019 to 2020")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2019To2020()

      When("I verify what-you-will-need-aa/2020 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage()

      When("I click back sign and click Add details for 2020 to 2021")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2020To2021()

      When("I verify what-you-will-need-aa/2021 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage()

      When("I click back sign and click Add details for 2021 to 2022")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2021To2022()

      When("I verify what-you-will-need-aa/2022 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2022Page()

      When("I sign out from the page")
      WhatYouWillNeedAaPage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      */
    Scenario("Annual allowance AA info loop user journey - Add another Scheme N & DC=No ", ZapTests) {
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

      /** AA Loop */
      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to July 8 2015")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** Verify header and title */
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** MCSC-292 */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      /** MCSC-292 */
      AddAnotherSchemePage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      */
    Scenario("Annual allowance AA info loop user journey - Add another Scheme N & DC=Yes ", ZapTests) {
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

      When("I enter a valid future date")
      WhenStopPayingPublicPensionPage.enterValidFutureDateAndClickContinue()

      Then("I Should see the have-defined-contribution-pension page")
      HaveDefinedContributionPensionPage.onHaveDefinedContributionPensionPage()

      When("I select no and continue to next page")
      HaveDefinedContributionPensionPage.selectYesAndContinueForAASPage()

      Then("I Should see the have-flexible-accessed-pension page")
      HaveFlexiblyAccessedPensionPage.onHaveFlexiblyAccessedPensionPage()

      When("I select No and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectNoAndContinueForAASPage()

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

      /** AA Loop */
      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to July 8 2015")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** Verify header and title */
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** MCSC-292 */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "00348916RT")
      PensionSchemeInputAmountsPage.enterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      /** MCSC-292 */
      AddAnotherSchemePage.signOutPage()
    }
  }
}
