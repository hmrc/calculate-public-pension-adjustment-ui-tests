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
import uk.gov.hmrc.test.ui.specs.tags.CalculationJourney1

class CalculationUserJourneys extends BaseSpec {

  Feature("Calculate...") {
    val signInPage: String = TestConfiguration.optionalAuthFlag()

    Scenario("Setup Journey 1", CalculationJourney1) {

      /** User not eligible */
      /** Below journey covers 0, 1.1 (N), 1.2 */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I verify Affected by remedy page select no and click continue")
      AffectedByRemedyPage.selectNoAndContinueForGSPage()

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()

    }

    Scenario("Setup Journey 2, and LTA journey 2", CalculationJourney1) {

      /** User has protections and both enhancements (international enhancement and pension credit), all have changed. Previous LTA charge, paid with both lump sum and annual payment, scheme paid, new excess to be taken as both, pension scheme to pay increase */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
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

      When("I click Lifetime allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 2 */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      When("I select both radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectBothRadioButtonAndContinue()

      When("I select Both protection and enhancement radio button and click continue")
      ProtectionTypePage.selectEnhancedProtectionRadioButtonAndContinue()

      When("I enter protection reference and click continue")
      ProtectionReferencePage.enterNewProtectionReferenceAndContinue("123456789AB")

      When("I select both enhancement and credit, and click continue")
      EnhancementType.selectBothRadioButtonAndContinue()

      When("I enter new international enhancement reference and click continue")
      InternationalEnhancementReferencePage.enterNewInternationalEnhancementReferenceAndContinue("123456789CD")

      When("I enter pension credit reference and click continue")
      PensionCreditReferencePage.enterNewPensionCreditReferenceAndContinue("123456789EF")

      When("I select both have changed and click continue")
      ProtectionEnhancementChangedPage.selectBothAndClickOnContinue()

      Then("I Should see the ProtectionChangedNewTypePage and select enhanced protection and continue")
      ProtectionChangedNewTypePage.selectEnhancedProtectionAndContinue()

      Then("I Should see the ProtectionChangedNewReferencePage and enter reference and continue")
      ProtectionChangedNewReferencePage.enterNewReferenceAndContinue("123456789GH")

      NewEnhancementTypePage.selectBothRadioButtonAndContinue()
      NewInternationalEnhancementReferencePage.enterNewInternationalEnhancementReferenceAndContinue("123456789IJ")
      NewPensionCreditReferencePage.enterNewPensionCreditReferenceAndContinue("123456789LM")
      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonBothAndContinue()
      ValueOfLumpSumPage.enterLumpSumAndContinue("3000")
      ValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("3000")
      WhoPaidLtaChargePage.selectPensionSchemeAndClickOnContinue()
      SchemePaidLtaChargePage.enterPensionSchemeInformationAndContinue("army", "11123456ED")
      QuarterChargeWasPaidPage.selectQuarterAndContinue()
      YearChargeWasPaidPage.selectYearAndContinue()
      NewExcessPaidPage.selectBothRadioButtonAndContinue()
      NewValueOfLumpSumPage.enterLumpSumAndContinue("4000")
      NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("4000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select pension scheme and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectPensionSchemeAndContinue()
      SchemePaidExtraLtaChargePage.enterPensionSchemeInformationAndContinue("army", "11123456ED")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    Scenario("Setup Journey 2, and LTA journey 3", CalculationJourney1) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
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

      When("I click Lifetime allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 3 */
      /** User has protections only, has changed, user paid initial charge, new charge annual payment only, user paying new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      When("I select protection radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectProtectionRadioButtonAndContinue()

      When("I select Both protection and enhancement radio button and click continue")
      ProtectionTypePage.selectFixedProtection2016RadioButtonAndContinue()

      When("I enter protection reference and click continue")
      ProtectionReferencePage.enterNewProtectionReferenceAndContinue("123456789GH")

      When("I select Protection has changed and click continue")
      ProtectionEnhancementChangedPage.selectProtectionAndClickOnContinue()

      Then("I Should see the ProtectionChangedNewTypePage and select fixed protection 2016 and continue")
      ProtectionChangedNewTypePage.selectFixedProtection2016AndContinue()

      Then("I Should see the ProtectionChangedNewReferencePage and enter reference and continue")
      ProtectionChangedNewReferencePage.enterNewReferenceAndContinue("123456789GH")

      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonLumpSumAndContinue()

      ValueOfLumpSumPage.enterLumpSumAndContinue("3000")
      WhoPaidLtaChargePage.selectYouAndClickOnContinue()

      SchemeNameReferencePage.enterSchemeNameReferenceAndContinue("army", "11123456ED")
      NewExcessPaidPage.selectLumpSumRadioButtonAndContinue()
      NewValueOfLumpSumPage.enterLumpSumAndContinue("4000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select user and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectYouAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    Scenario("Setup Journey 2, and LTA journey 4", CalculationJourney1) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
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

      When("I click Lifetime allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 4 */
      /** User has enhancements, has changed, previous charge, annual payment, positive value, user will pay new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      When("I select enhancement radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()

      When("I select both enhancement and credit, and click continue")
      EnhancementType.selectPensionCreditRadioButtonAndContinue()

      When("I enter pension credit reference and click continue")
      PensionCreditReferencePage.enterNewPensionCreditReferenceAndContinue("123456789GH")

      When("I select both have changed and click continue")
      ProtectionEnhancementChangedPage.selectEnhancementAndClickOnContinue()

      NewEnhancementTypePage.selectPensionCreditRadioButtonAndContinue()

      NewPensionCreditReferencePage.enterNewPensionCreditReferenceAndContinue("123456789GH")

      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonAnnualPaymentAndContinue()
      ValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("3000")
      WhoPaidLtaChargePage.selectYouAndClickOnContinue()
      SchemeNameReferencePage.enterSchemeNameReferenceAndContinue("army", "11123456ED")
      NewExcessPaidPage.selectAnnualPaymentRadioButtonAndContinue()
      NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("2000")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    Scenario("Setup Journey 2, and LTA journey 5", CalculationJourney1) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
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

      When("I click Lifetime allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 5 */
      /** User has no protections or enhancements, neither has changed, no previous charge, annual payment = 0, user hits kickout */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      When("I select No LTA protection or enhancement radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectNoRadioButtonAndContinue()

      When("I select neither have changed and click continue")
      ProtectionEnhancementChangedPage.selectNoAndClickOnContinue()

      When("I select No LTA charge was paid")
      LtaCharge20152023Page.selectNoAndClickOnContinue()

      When("I select new excess was paid by annual payment and continue")
      NewExcessPaidPage.selectLumpSumRadioButtonAndContinue()

      When("I select new excess was paid by annual payment and continue")
      NewValueOfLumpSumPage.enterLumpSumAndContinue("0")

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()

    }

    Scenario("Setup Journey 2, and LTA journey 6", CalculationJourney1) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
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

      When("I click Lifetime allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 6 */
      /** User has no protections, no previous charge, user paying new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      When("I select No LTA protection or enhancement radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectNoRadioButtonAndContinue()

      When("I select neither have changed and click continue")
      ProtectionEnhancementChangedPage.selectNoAndClickOnContinue()

      When("I select No LTA charge was paid")
      LtaCharge20152023Page.selectNoAndClickOnContinue()

      When("I select new excess was paid by annual payment and continue")
      NewExcessPaidPage.selectAnnualPaymentRadioButtonAndContinue()

      When("I select new excess was paid by annual payment and continue")
      NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("1000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select you and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectYouAndContinue()
      SchemeNameReferencePage.enterSchemeNameReferenceAndContinue("army", "11123456ED")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

  }
}
