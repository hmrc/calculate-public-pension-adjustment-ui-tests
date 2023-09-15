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

import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.constants.Errors.{TEXT_WITH_500_CHARACTERS, TEXT_WITH_501_CHARACTERS}
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class UserJourneyValidationTests extends BaseSpec {

  Feature("Calculate public pension adjustment user journey with validation tests") {

    /** Below journey covers 0,1.1,1.3(N),1.5 pages in the mural board* */
    Scenario("User Journey error validation till general setup page - Resubmission(NO) workflow", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage()

      When("I click continue button")
      SavingsStatementPage.submitPage()

      Then("I Should see the error messages")
      SavingsStatementPage.validateRadioButtonError(Errors.SAVINGS_STATEMENT_RADIO_BUTTON_ERROR_SUMMARY)

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage()

      When("I click continue button")
      ResubmittingAdjustmentPage.submitPage()

      Then("I Should see the error messages")
      ResubmittingAdjustmentPage.validateRadioButtonError(Errors.RESUBMITTING_ADJUSTMENT_RADIO_BUTTON_ERROR_SUMMARY)

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

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5 pages in the mural board* */
    Scenario("User Journey error validation till general setup page - Resubmission(Yes) workflow") {
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

      When("I enter resubmission reason with 501 characters")
      ReasonForResubmissionPage.enterResubmissionReason(TEXT_WITH_501_CHARACTERS)

      Then("I Should see only 500 characters in the resubmission reason text box")
      ReasonForResubmissionPage.enterResubmissionReason(TEXT_WITH_500_CHARACTERS)

      ReasonForResubmissionPage.verifiedMaxCharacterLength()

      Then("I click continue")
      ReasonForResubmissionPage.submitPage()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage()

      When("I click sign out from the page")
      ReportingChangePage.signOutPage()
    }

    Scenario("LTA user journey - Task list validation Test 1") {
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

    Scenario("LTA user journey - Task list validation Test 2") {
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

    }

    /** Below journey covers 0,1.1,1.2 pages in the mural board* */
    Scenario("User Journey general setup page - kick out page", ZapTests) {
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
