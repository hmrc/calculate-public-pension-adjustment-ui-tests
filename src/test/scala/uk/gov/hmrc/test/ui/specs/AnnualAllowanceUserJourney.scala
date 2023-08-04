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
import uk.gov.hmrc.test.ui.pages._
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
      signOutPage()
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
      signOutPage()
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
      PiaPreRemedyPage2013.onPiaPreRemedyPage2012Page()

      When("I enter amount for 2012-2013 and click continue")
      PiaPreRemedyPage2013.enterAmountAndClickContinue()

      Then("I Should see the pia-pre-remedy/2013 page")
      PiaPreRemedyPage2014.onPiaPreRemedyPage2013Page()

      When("I enter amount for 2013-2014 and click continue")
      PiaPreRemedyPage2014.enterAmountAndClickContinue()

      Then("I Should see the pia-pre-remedy/2014 page")
      PiaPreRemedyPage2015.onPiaPreRemedyPage2014Page()

      When("I enter amount for 2014-2015 and click continue")
      PiaPreRemedyPage2015.enterAmountAndClickContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

      When("I click sign out from the page")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.9,3.2.1,  pages in the mural board*
      * also covered 3.9,3.17,Tasklist / 3.9,3.16,tasklist / 3.9,3.14,3.15,3.16,3.17,tasklist / 3.9,3.14,3.16,3.17,tasklist
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

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      When("I verify DidYouPayAChargePage page, select no and continue")
      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-pre", "0")

      /** Verify header and title */
      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      When("I verify AddAnotherSchemePage page, select No and continue")
      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()



      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()

      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-post", "0")

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue("2016-post", "1")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-post", "1", "PensionScheme3", "00348916AB")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "1",
        "700000000",
        "800000000",
        "PensionScheme3"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "1")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "1")

      /** verify url once fixed */
      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 --- */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme3", "00348916AB")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme3"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "0")

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue("2017", "1")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2017", "1", "PensionScheme4", "00348916AC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "1",
        "700000000",
        "800000000",
        "PensionScheme4"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "1")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2017", "1")

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2020 --- */
      When("I click Add details for 2019 to 2020")
      TaskListPage.clickAddDetailsFor2019To2020()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2020")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2020", "0", "PensionScheme4", "00348916AC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2020",
        "0",
        "500000000",
        "600000000",
        "PensionScheme4"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2020", "0")

      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2020", "0")

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue("2020", "1")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2020", "1", "PensionScheme5", "00348916AD")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2020",
        "1",
        "700000000",
        "800000000",
        "PensionScheme5"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2020", "1")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2020", "1")

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2019", "2020", "2020")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2019", "2020", "2020", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2019", "2020", "2020", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2021 --- */
      When("I click Add details for 2020 to 2021")
      TaskListPage.clickAddDetailsFor2020To2021()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2021")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2021", "0", "PensionScheme5", "00348916AD")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2021",
        "0",
        "500000000",
        "600000000",
        "PensionScheme5"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2021", "0")

      /** need a dev fix here to avoid add another scheme page as 5 schemes already added */
      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2021", "1")

      When("I verify ThresholdIncomePage(2021-2023) page, select no and continue")
      ThresholdIncomePage.verify2021TO2023PageSelectNoAndContinue("2020", "2021", "2021")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2020", "2021", "2021", "440000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      signOutPage()
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

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select you and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      /** Verify header and title */
      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )
      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select pension scheme and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
        "2016-post",
        "0",
        "9 July 2015 to 5 April 2016"
      )

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-post", "0", "200000000")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      /** verify url once fixed */
      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
      signOutPage()
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

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** Verify header and title */
      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()

      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue(
        "2016-post",
        "0",
        "9 July 2015 to 5 April 2016"
      )

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-post", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-post", "0", "200000000")

      /** Verify header and title */
      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-post", "0")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "1", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "1",
        "300000000",
        "400000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", "1")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-post", "1", "200000000")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "1")

      /** verify url once fixed */
      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage("2017")

      When("I click back sign and click Add details for 2017 to 2018")
      MemberMoreThanOnePensionPage.clickBackButton()

      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2017To2018()

      When("I verify what-you-will-need-aa/2018 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2018Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage("2018")

      When("I click back sign and click Add details for 2018 to 2019")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2018To2019()

      When("I verify what-you-will-need-aa/2019 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2019Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage("2019")

      When("I click back sign and click Add details for 2019 to 2020")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2019To2020()

      When("I verify what-you-will-need-aa/2020 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage("2020")

      When("I click back sign and click Add details for 2020 to 2021")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2020To2021()

      When("I verify what-you-will-need-aa/2021 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.onMemberMoreThanOnePensionPage("2021")

      When("I click back sign and click Add details for 2021 to 2022")
      MemberMoreThanOnePensionPage.clickBackButton()
      WhatYouWillNeedAaPage.clickBackButton()
      TaskListPage.clickAddDetailsFor2021To2022()

      When("I verify what-you-will-need-aa/2022 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2022Page()

      When("I sign out from the page")
      signOutPage()
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

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** Verify header and title */
      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      /** --- verify check your answers page --- */
      //CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()

      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.9,3.10,3.14 / 3.9,3.10,3.16 /3.9,3.10,3.17
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

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** Verify header and title */
      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "1", "PensionScheme2", "00348916TR")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "1",
        "300000000",
        "400000000",
        "PensionScheme2"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "1")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "1", "200000000")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      When("I verify ContributedOtherDbDcSchemePage page, select no and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue("2016-pre")

      /** Verify check your answers page */
      //CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()

      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage page, select no and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue("2016-post")

      /** verify url once fixed */
      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      //CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 --- */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "0")

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue("2017", "1")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2017", "1", "PensionScheme4", "00348916AC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "1",
        "700000000",
        "800000000",
        "PensionScheme4"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "1")

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2017", "1")

      When("I verify ContributedOtherDbDcSchemePage page, select no and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue("2017")

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      // CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.7,3.8,3.17 / 3.8,3.17 / 3.5,3.16
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi =no , Multiple Schemes = N & DC=No ",
      ZapTests
    ) {
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
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()

      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.10,3.11,3.13,3.14,3.15,3.16,3.17,TaskList
      * 3.10,3.11,3.13,3.16,3.17
      * 3.10,3.11,3.13,3.17
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi =no , Multiple Schemes = N & DC=Yes DB only ",
      ZapTests
    ) {
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
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue("2016-pre")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "750000000",
        "6 April 2015",
        "8 July 2015"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue("2016-post")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "850000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue("2017")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2017",
        "850000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.10,3.11,3.12,3.14,3.15,3.16,3.17,TaskList
      * 3.10,3.11,3.12,3.16,3.17
      * 3.10,3.11,3.12,3.17
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi = no , Multiple Schemes = N & DC=Yes DC only ",
      ZapTests
    ) {
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
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue("2016-pre")

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-pre",
        "750000000",
        "6 April 2015",
        "8 July 2015"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue("2016-post")

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-post",
        "850000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue("2017")

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2017",
        "850000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.10,3.11,3.12,3.14,3.15,3.16,3.17,TaskList
      * 3.10,3.11,3.12,3.16,3.17
      * 3.10,3.11,3.12,3.17
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi = no , Multiple Schemes = N & DC=Yes DC&DB both ",
      ZapTests
    ) {
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
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-pre")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "760000000",
        "6 April 2015",
        "8 July 2015"
      )
      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-pre",
        "750000000",
        "6 April 2015",
        "8 July 2015"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")
      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "860000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-post",
        "850000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2017")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2017",
        "850000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2017",
        "850000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.10,3.11,3.12,3.14,3.15,3.16,3.17,TaskList
      * 3.10,3.11,3.12,3.16,3.17
      * 3.10,3.11,3.12,3.17
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi = yes , Multiple Schemes = N & DC=Yes DC&DB both for 2017 ",
      ZapTests
    ) {
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

      When("I select Yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesAndContinueForAASPage()

      When("I enter flexible access date")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("25", "04", "2016")

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
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-pre")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "760000000",
        "6 April 2015",
        "8 July 2015"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-pre",
        "750000000",
        "6 April 2015",
        "8 July 2015"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "860000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-post",
        "850000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")
      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2017")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2017",
        "160000000",
        "6 April 2016",
        "25 April 2016"
      )

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2017",
        "180000000",
        "26 April 2016",
        "5 April 2017"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2017",
        "120000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.10,3.11,3.12,3.14,3.15,3.16,3.17,TaskList
      * 3.10,3.11,3.12,3.16,3.17
      * 3.10,3.11,3.12,3.17
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi = yes , Multiple Schemes = N & DC=Yes DC&DB both for 2016-pre",
      ZapTests
    ) {
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

      When("I select Yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesAndContinueForAASPage()

      When("I enter flexible access date")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("25", "04", "2015")

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
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-pre")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "360000000",
        "6 April 2015",
        "25 April 2015"
      )

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "180000000",
        "26 April 2015",
        "8 July 2015"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-pre",
        "750000000",
        "6 April 2015",
        "8 July 2015"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "860000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-post",
        "850000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2017")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2017",
        "160000000",
        "6 April 2016",
        "5 April 2017"
      )
      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2017",
        "120000000",
        "6 April 2016",
        "5 April 2017"
      )
      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(AA),1.6,2.1(N),2.3(N),2.4,2.5(N),2.8(Y),2.12,TaskList
      * 3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.9,3.8,3.2.1 pages in the mural board*
      * Also covered 3.10,3.11,3.12,3.14,3.15,3.16,3.17,TaskList
      * 3.10,3.11,3.12,3.16,3.17
      * 3.10,3.11,3.12,3.17
      */
    Scenario(
      "Annual allowance AA info loop user journey - Flexi = yes , Multiple Schemes = N & DC=Yes DC&DB both for 2016-post",
      ZapTests
    ) {
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

      When("I select Yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesAndContinueForAASPage()

      When("I enter flexible access date")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("19", "10", "2015")

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

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("2016-pre", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-pre",
        "0",
        "100000000",
        "200000000",
        "PensionScheme1"
      )
      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify WhoPaidAnnualAllowanceChargePage, select both and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2016-pre", "0", "6 April 2015 to 8 July 2015")

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "100000000")

      When("I verify HowMuchPensionPayChargePage, enter pension pay and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("2016-pre", "0", "200000000")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-pre")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-pre")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "360000000",
        "6 April 2015",
        "8 July 2015"
      )
      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-pre",
        "750000000",
        "6 April 2015",
        "8 July 2015"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2016-post", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2016-post",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "860000000",
        "9 July 2015",
        "19 October 2015"
      )

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "180000000",
        "20 October 2015",
        "5 April 2016"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-post",
        "850000000",
        "9 July 2015",
        "5 April 2016"
      )

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-post", "400000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2017 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      /** page url needs to finalize */
      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2017")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("2017", "0", "PensionScheme1", "00348916RT")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "2017",
        "0",
        "500000000",
        "600000000",
        "PensionScheme1"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue("2017", "0")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2017")

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB & DC both and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2017")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2017",
        "160000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2017",
        "120000000",
        "6 April 2016",
        "5 April 2017"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue("2016", "2017", "2017")

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "550000000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2016", "2017", "2017", "430000000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
      signOutPage()
    }
  }
}
