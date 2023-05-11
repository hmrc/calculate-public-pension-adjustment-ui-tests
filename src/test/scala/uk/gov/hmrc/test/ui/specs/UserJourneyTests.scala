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

import uk.gov.hmrc.test.ui.constants.Errors.TEXT_WITH_503_CHARACTERS
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class UserJourneyTests extends BaseSpec {

  Feature("Calculate public pension adjustment user journey") {

    /** Below journey covers 0,1,3(N),5 pages in the mural board* */
    Scenario("User Journey through resubmission 'NO' functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage

      When("I click continue button")
      SavingsStatementPage.submitPage()

      Then("I Should see the error messages")
      SavingsStatementPage.validateCheckBoxError()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage

      When("I click continue button")
      ResubmittingAdjustmentPage.submitPage()

      Then("I Should see the error messages")
      ResubmittingAdjustmentPage.validateCheckBoxError()

      When("I select option No and continue")
      ResubmittingAdjustmentPage.selectNoAndContinue()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage

      When("I click continue button")
      ReportingChangePage.submitPage()

      Then("I Should see the error messages")
      ReportingChangePage.validateReportingChangePageErrorsWhenNoCheckBoxSelected()
    }

    /** Below journey covers 0,1,3(Y),4,5 pages in the mural board* */
    Scenario("User journey through resubmission (Yes) functionality with maximum character length") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage

      When("I click continue button")
      ReasonForResubmissionPage.submitPage()

      Then("I Should see the error messages")
      ReasonForResubmissionPage.validateReasonForResubmissionPageErrorsWhenNoTextAsReason()

      When("I enter resubmission reason with  503 characters")
      ReasonForResubmissionPage.enterResubmissionReason(TEXT_WITH_503_CHARACTERS)

      Then("I Should see only 500 characters in the resubmission reason text box")
      assert(ReasonForResubmissionPage.verifiedMaxCharacterLength())

      Then("I click continue")
      ReasonForResubmissionPage.submitPage()

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage

      /*Link to next page*/
      /** Verify 'check your answers' page with 500 characters  * */

    }

    /** Below journey covers 0,1,3(Y),4,5 pages in the mural board* */
    Scenario("User journey through resubmission (Yes) functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage

      When("I click start button")
      HomePage.clickStartButton()

      Then("I Should see the SavingsStatementPage page")
      SavingsStatementPage.onSavingsStatementPage

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinue

      Then("I Should see the ResubmittingAdjustmentPage page")
      ResubmittingAdjustmentPage.onResubmittingAdjustmentPage

      When("I select I'm resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinue

      Then("I Should see the ReasonForResubmissionPage page")
      ReasonForResubmissionPage.onReasonForResubmissionPage

      When("I enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue

      Then("I Should see the reporting-change page")
      ReportingChangePage.onReportingChangePage

      /*Link to next page*/
    }

    /** Below journey covers 0,1,2 pages in the mural board* */
    Scenario("User Journey through savings statement 'NOT' received functionality", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.loadPage

      When("I click start button")
      HomePage.clickStartButton()

      When("I select option No and continue")
      SavingsStatementPage.selectNoAndContinue

      Then("I Should see the EligibilityPage page")
      EligibilityPage.onEligibilityPage
    }
  }
}
