/*
 * Copyright 2024 HM Revenue & Customs
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

import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.{AALTATriage1, CalculationJourney2}

class AALTATriage1 extends BaseSpec {

  Feature("Calculate...") {
    val signInPage: String = TestConfiguration.optionalAuthFlag()

    Scenario("Journey for Triage 1", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, AA adjustment */
      /** Below journey covers 0.7,0.8,0.9,0.14 */
      /** This scenario covers Triage user journey where No RPSS/Protected Member (AA only).--- MCSC-1021(Scenario 1) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectNoAndContinueForGSPage()

      When("0.9 I verify Protected Member page select yes and click continue")
      ProtectedMember.selectYesAndContinueForGSPage()

      Then("0.14 I verify not impacted no Rpss page select signOut")
      NotImpactedNoRpss.signOutPage()
    }

    Scenario("Journey for Triage 2", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, AA adjustment */
      /** Below journey covers 0.7,0.8,0.9,0.14.0.22 */
      /** This scenario covers Triage user journey where No RPSS/Protected Member (AA and LTA).--- MCSC-1021(Scenario 2) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select both Annual allowance and LTA, and click continue")
      ReportingChangePage.selectBothAAAndLTAContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectNoAndContinueForGSPage()

      When("0.9 I verify Protected Member page select yes and click continue")
      ProtectedMember.selectYesAndContinueForGSPage()

      Then("0.14 I verify not impacted no Rpss page and click continue")
      NotImpactedNoRpss.clickContinueButton()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()
    }

    Scenario("Journey for Triage 3", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, AA adjustment */
      /** Below journey covers 0.7,0.8,0.9,0.10,2.1 */
      /** This scenario covers Triage user journey where RPSS/Non-Protected Member (AA Only).--- MCSC-1021(Scenario 3) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select both Annual allowance and LTA, and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("0.9 I verify Protected Member page select yes and click continue")
      ProtectedMember.selectNoAndContinueForGSPage()

      When("0.10 I verify Annual allowance charge page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("2.1 I land on Scottish taxpayer page, select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()
    }

    Scenario("Journey for Triage 4", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, AA adjustment */
      /** Below journey covers 0.7,0.8,0.9,0.10.0.22 */
      /** This scenario covers Triage user journey where RPSS(yes)/Non-Protected Member (AA/LTA)--- MCSC-1021(Scenario 4) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select both Annual allowance and LTA, and click continue")
      ReportingChangePage.selectBothAAAndLTAContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("0.9 I verify Protected Member page select yes and click continue")
      ProtectedMember.selectNoAndContinueForGSPage()

      When("0.10 I verify Annual allowance charge page select yes and click continue")
      AnnualAllowanceCharge.selectYesAndContinueForGSPage()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()
    }

    Scenario("Journey for Triage 5", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, AA adjustment */
      /** Below journey covers 0.7,0.8,0.9,0.10,0.11,0.14 */
      /** This scenario covers Triage user journey where No RPSS/Protected Member (AA only).--- MCSC-1021(Scenario 5) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectNoAndContinueForGSPage()

      When("0.9 I verify Protected Member page select yes and click continue")
      ProtectedMember.selectNoAndContinueForGSPage()

      When("0.10 I verify Annual allowance charge page select yes and click continue")
      AnnualAllowanceCharge.selectYesAndContinueForGSPage()

      When("0.11 I verify ContributionRefunds page select no and click continue")
      ContributionRefunds.selectNoAndContinueForGSPage()

      Then("0.14 I verify not impacted no Rpss page select signOut")
      NotImpactedNoRpss.signOutPage()
    }

    Scenario("Journey for Triage 6", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, AA adjustment */
      /** Below journey covers 0.7,0.8,0.9,0.10,0.11,0.14.,0.22 */
      /** This scenario covers Triage user journey where RPSS(yes)/Non-Protected Member (AA/LTA)--- MCSC-1021(Scenario 6) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select both Annual allowance and LTA, and click continue")
      ReportingChangePage.selectBothAAAndLTAContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectNoAndContinueForGSPage()

      When("0.9 I verify Protected Member page select yes and click continue")
      ProtectedMember.selectNoAndContinueForGSPage()

      When("0.10 I verify Annual allowance charge page select yes and click continue")
      AnnualAllowanceCharge.selectYesAndContinueForGSPage()

      When("0.11 I verify ContributionRefunds page select no and click continue")
      ContributionRefunds.selectNoAndContinueForGSPage()

      Then("0.14 I verify not impacted no Rpss page and click continue")
      NotImpactedNoRpss.clickContinueButton()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()
    }

    Scenario("Journey for Triage 7", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting,  LTA adjustment */
      /** Below journey covers 0.1, 0.3, 0.5, 0.7, 0.22, 0.23, 0.24 */
      /** This scenario covers Triage user journey where user has had BCE and been informed of % increase, MCSC-1018 Scenario 1 */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I click Lifetime allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      /** LTA Journey */
      When("I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select Yes and continue to next page")
      LTACharge.selectYesAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("I verify check your answers page for lifetime allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()
    }

    Scenario("Journey for Triage 8", AALTATriage1, CalculationJourney2) {

      /** User resubmitting, LTA adjustment */
      /** Below journey covers 0.7,0.22,0.23,0.24, 0.29 */
      /** This scenario covers Triage user journey where No LTA Charge + Not Informed of Change in LTA MCSC-1018(Scenario 2) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select No and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectNoAndContinue()

      When("0.29 I verify You cannot make a LTA adjustment page and signout")
      CannotUseTriageLTAService.signOutPage()

    }

    Scenario("Journey for Triage 9", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, LTA adjustment */
      /** Below journey covers 0.7, 0.22, 0.23, 0.24, 0.25, 0.29 */
      /** This scenario covers Triage user journey where No LTA Charge/Informed of % Change/No Increase (LTA only)--- MCSC-1018(Scenario 3) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select No and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectNoAndContinue()

      When("0.29 I verify You cannot make a LTA adjustment page and signout")
      CannotUseTriageLTAService.signOutPage()

    }

    Scenario("Journey for Triage 10", AALTATriage1, CalculationJourney2) {

      /** User resubmitting, LTA adjustment */
      /** Below journey covers 0.7,.0.22, 0.23, 0.24, 0.25, 0.26, CYA */
      /** This scenario covers Triage user journey where No LTA Charge/Informed of % Change/Increase/New Charge (LTA)--- MCSC-1018(Scenario 4) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select Yes and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectYesAndContinue()

      When("0.26 I verify LTA New Charge page select Yes and continue to next page")
      LifetimeAllowanceNewChargePage.selectYesAndContinueForLTAPage()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    Scenario("Journey for Triage 11", AALTATriage1, CalculationJourney2) {

      /** User resubmitting, LTA adjustment */
      /** Below journey covers 0.7,0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.29 */
      /** This scenario covers Triage user journey where No LTA Charge/Informed of % Change/Increase/No New Charge/No Multiple BCEs (LTA)--- MCSC-1018(Scenario 5) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select Yes and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectYesAndContinue()

      When("0.26 I verify LTA New Charge page select No and continue to next page")
      LifetimeAllowanceNewChargePage.selectNoAndContinueForLTAPage()

      When("0.27 I verify Multiple BCE page select No and continue to next page")
      MultipleBenefitCrystallisationEventPage.selectNoAndContinueForLTAPage()

      When("0.29 I verify You cannot make a LTA adjustment page and signout")
      CannotUseTriageLTAService.signOutPage()

    }

    Scenario("Journey for Triage 12", AALTATriage1, CalculationJourney2) {

      /** User not resubmitting, LTA adjustment */
      /** Below journey covers 0.7,0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.28, CYA */
      /** This scenario covers Triage user journey where Multiple BCEs/ Other Scheme notified (LTA)--- MCSC-1019(Scenario 1) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select Yes and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectYesAndContinue()

      When("0.26 I verify LTA New Charge page select No and continue to next page")
      LifetimeAllowanceNewChargePage.selectNoAndContinueForLTAPage()

      When("0.27 I verify Multiple BCE page select Yes and continue to next page")
      MultipleBenefitCrystallisationEventPage.selectYesRadioButtonAndContinue()

      When("0.28 I verify OtherSchemeLTANotification page and select Yes I have been informed by their pension scheme")
      OtherSchemeLTANotification.verifyPageSelectYesAndContinue()

      When("I reach CYA and click continue")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    Scenario("Journey for Triage 13", AALTATriage1, CalculationJourney2) {

      /** User resubmitting, LTA adjustment */
      /** Below journey covers 0.7,0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.28, 0.29 */
      /** This scenario covers Triage LTA user journey where Informed of % Change/Increase/No New Charge/Multiple BCEs/Other scheme not notified (LTA)--- MCSC-1019(Scenario 2) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select Yes and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectYesAndContinue()

      When("0.26 I verify LTA New Charge page select No and continue to next page")
      LifetimeAllowanceNewChargePage.selectNoAndContinueForLTAPage()

      When("0.27 I verify Multiple BCE page select Yes and continue to next page")
      MultipleBenefitCrystallisationEventPage.selectYesRadioButtonAndContinue()

      When(
        "0.28 I verify OtherSchemeLTANotification page and select No I have not been informed by their pension scheme"
      )
      OtherSchemeLTANotification.verifyPageSelectNoAndContinue()

      When("0.29 I verify You cannot make a LTA adjustment page and signout")
      CannotUseTriageLTAService.signOutPage()

    }

    Scenario("Journey for Triage 14", AALTATriage1, CalculationJourney2) {

      /** User resubmitting, LTA adjustment */
      /** Below journey covers 0.7,0.22, 0.23, 0.24, 0.25, 0.26, 0.27, 0.29 */
      /** This scenario covers Triage LTA user journey where No Multiple BCEs (LTA)--- MCSC-1019(Scenario 3) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesAndContinueForGSPage()

      When("0.7 I select LTA, and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select Yes and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectYesAndContinue()

      When("0.26 I verify LTA New Charge page select No and continue to next page")
      LifetimeAllowanceNewChargePage.selectNoAndContinueForLTAPage()

      When("0.27 I verify Multiple BCE page select No and continue to next page")
      MultipleBenefitCrystallisationEventPage.selectNoAndContinueForLTAPage()

      When("0.29 I verify You cannot make a LTA adjustment page and signout")
      CannotUseTriageLTAService.signOutPage()

    }

  }

}