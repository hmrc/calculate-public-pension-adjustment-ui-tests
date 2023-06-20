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
      WhichYearsScottishTaxpayer.signOutPage()

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
  }

}
