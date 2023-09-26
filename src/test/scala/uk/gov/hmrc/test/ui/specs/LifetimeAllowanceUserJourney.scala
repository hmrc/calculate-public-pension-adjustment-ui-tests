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
import uk.gov.hmrc.test.ui.specs.tags.{BSTests, ZapTests}
class LifetimeAllowanceUserJourney extends BaseSpec {

  Feature("Calculate public pension adjustment Lifetime Allowance user journey") {

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(Y),4.5,4.6,4.7,4.8,4.9(Y),4.10,4.11,4.12(Y),4.13,4.14,4.15(User),4.17(Decrease),4.20  pages in the mural board* */
    Scenario("LTA change of Benefit Crystallisation Event(Yes) journey", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()

      When("I should see the multiple benefit crystallisation event page")
      MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()

      When("I select any radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

      Then("I Should see the lta-protection-or-enhancements page")
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
      ProtectionReferencePage.enterProtectionReferenceAndContinue()

      Then("I Should see the ProtectionChangedPage and select Yes and continue")
      ProtectionEnhancementChangedPage.selectProtectionAndClickOnContinue()

      Then("I Should see the ProtectionChangedNewTypePage and select enhanced protection and continue")
      ProtectionChangedNewTypePage.selectEnhancedProtectionAndContinue()

      Then("I Should see the ProtectionChangedNewReferencePage and enter reference and continue")
      ProtectionChangedNewReferencePage.enterReferenceAndContinue()

      Then("I Should see the LtaCharge20152023Page , select Yes and continue")
      LtaCharge20152023Page.selectYesAndContinueForLTAPage()

      Then("I Should see the HowExcessWasPaidPage , select both payment and continue")
      HowExcessWasPaidPage.selectRadioButtonLumpSumAndContinue()

      Then("I Should see the ValueOfLumpSumPage , enter lump sum and continue")
      ValueOfLumpSumPage.enterLumpSumAndContinue("1000")

      Then("I Should see the WhoPaidLtaChargePage , select you and continue")
      WhoPaidLtaChargePage.selectYouAndClickOnContinue()

      SchemeNameReferencePage.enterSchemeNameReferenceAndContinue()

      NewExcessPaidPage.selectLumpSumRadioButtonAndContinue()

      NewValueOfLumpSumPage.enterLumpSumAndContinue("900")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(Y),4.5,4.6,4.7,4.8,4.9(Y),4.10,4.11,4.12(N),4.17(Decrease),4.18(Scheme),4.19,4.20 pages in the mural board */
    Scenario("LTA protection change(Yes) journey", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()

      When("I should see the multiple benefit crystallisation event page")
      MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()

      When("I select any radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

      Then("I Should see the lta-protection-or-enhancements page")
      LtaProtectionOrEnhancementsPage.onLtaProtectionOrEnhancementsPage()

      When("I select any radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()

      EnhancementType.selectBothRadioButtonAndContinue()
      InternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      PensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      ProtectionEnhancementChangedPage.selectNoAndClickOnContinue()
      LtaCharge20152023Page.selectNoAndContinueForLTAPage()
      NewExcessPaidPage.selectLumpSumRadioButtonAndContinue()

      NewValueOfLumpSumPage.enterLumpSumAndContinue("1000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select pension scheme and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectYouAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()
    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(Y),4.5,4.6,4.7,4.8,4.9(Y),4.10,4.11,4.12(Y),4.13,4.14,4.15(Scheme),4.16,4.17(Increase),4.18(Scheme),4.19,4.20 pages in the mural boardd* */
    Scenario("LTA who paid(scheme) journey", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()

      When("I should see the multiple benefit crystallisation event page")
      MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()

      When("I select any radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

      Then("I Should see the lta-protection-or-enhancements page")
      LtaProtectionOrEnhancementsPage.onLtaProtectionOrEnhancementsPage()

      When("I select any radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()

      EnhancementType.selectPensionCreditRadioButtonAndContinue()
      PensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      ProtectionEnhancementChangedPage.selectEnhancementsRadioButtonAndContinue()
      NewEnhancementTypePage.selectInternationalEnhancementRadioButtonAndContinue()
      NewInternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonLumpSumAndContinue()
      ValueOfLumpSumPage.enterLumpSumAndContinue("2000")
      WhoPaidLtaChargePage.selectYouAndClickOnContinue()
      SchemeNameReferencePage.enterSchemeNameReferenceAndContinue()
      NewExcessPaidPage.selectLumpSumRadioButtonAndContinue()
      NewValueOfLumpSumPage.enterLumpSumAndContinue("3000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select pension scheme and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectYouAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(Y),4.5,4.6,4.7,4.8,4.9(Y),4.10,4.11,4.12(Y),4.13,4.14,4.15(Scheme),4.16,4.17(Increase),4.18(User),4.20 pages in the mural board* */
    Scenario("LTA value of new charge(Increase) journey") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()

      When("I should see the multiple benefit crystallisation event page")
      MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()

      When("I select any radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

      Then("I Should see the lta-protection-or-enhancements page")
      LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()

      EnhancementType.selectPensionCreditRadioButtonAndContinue()
      PensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      ProtectionEnhancementChangedPage.selectEnhancementsRadioButtonAndContinue()
      NewEnhancementTypePage.selectInternationalEnhancementRadioButtonAndContinue()
      NewInternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonAnnualPaymentAndContinue()
      ValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("1000")
      WhoPaidLtaChargePage.selectYouAndClickOnContinue()
      SchemeNameReferencePage.enterSchemeNameReferenceAndContinue()
      NewExcessPaidPage.selectAnnualPaymentRadioButtonAndContinue()
      NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("2000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select pension scheme and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectPensionSchemeAndContinue()

      SchemePaidExtraLtaChargePage.enterPensionSchemeInformationAndContinue("scheme 1", "00348916KU")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(Y),4.5,4.6,4.7,4.8,4.9(N),4.12 pages in the mural board */
    Scenario("LTA protection change(No) journey") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()

      When("I should see the multiple benefit crystallisation event page")
      MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()

      When("I select any radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

      Then("I Should see the lta-protection-or-enhancements page")
      LtaProtectionOrEnhancementsPage.onLtaProtectionOrEnhancementsPage()

      When("I select any radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()
      EnhancementType.selectBothRadioButtonAndContinue()
      InternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      PensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      ProtectionEnhancementChangedPage.selectBothRadioButtonAndContinue()
      ProtectionChangedNewTypePage.selectEnhancedProtectionAndContinue()
      ProtectionChangedNewReferencePage.enterReferenceAndContinue()
      NewEnhancementTypePage.selectBothRadioButtonAndContinue()
      NewInternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      NewPensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonBothAndContinue()
      ValueOfLumpSumPage.enterLumpSumAndContinue("1000")
      ValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("3000")
      WhoPaidLtaChargePage.selectPensionSchemeAndClickOnContinue()
      SchemePaidLtaChargePage.enterPensionSchemeInformationAndContinue("AAABBB", "00348916AS")
      QuarterChargeWasPaidPage.selectQuarterAndContinue()
      YearChargeWasPaidPage.selectYearAndContinue()
      NewExcessPaidPage.selectBothRadioButtonAndContinue()
      NewValueOfLumpSumPage.enterLumpSumAndContinue("2300")
      NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("2000")

      Then("I Should see the onWhoPayingExtraLtaChargePage, select pension scheme and continue")
      WhoPayingExtraLtaChargePage.verifyPageSelectPensionSchemeAndContinue()

      SchemePaidExtraLtaChargePage.enterPensionSchemeInformationAndContinue("scheme 2", "00348916CD")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(Y),4.5,4.6,4.7,4.8,4.9(N),4.12 pages in the mural board */
    Scenario("LTA Value decrease journey") {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()

      When("I should see the multiple benefit crystallisation event page")
      MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()

      When("I select any radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

      Then("I Should see the lta-protection-or-enhancements page")
      LtaProtectionOrEnhancementsPage.onLtaProtectionOrEnhancementsPage()

      When("I select any radio button and click continue")
      LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()
      EnhancementType.selectBothRadioButtonAndContinue()
      InternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      PensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      ProtectionEnhancementChangedPage.selectBothRadioButtonAndContinue()
      ProtectionChangedNewTypePage.selectEnhancedProtectionAndContinue()
      ProtectionChangedNewReferencePage.enterReferenceAndContinue()
      NewEnhancementTypePage.selectBothRadioButtonAndContinue()
      NewInternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
      NewPensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
      LtaCharge20152023Page.selectYesAndContinueForLTAPage()
      HowExcessWasPaidPage.selectRadioButtonBothAndContinue()
      ValueOfLumpSumPage.enterLumpSumAndContinue("1000")
      ValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("3000")
      WhoPaidLtaChargePage.selectPensionSchemeAndClickOnContinue()
      SchemePaidLtaChargePage.enterPensionSchemeInformationAndContinue("AAABBB", "00348916AS")
      QuarterChargeWasPaidPage.selectQuarterAndContinue()
      YearChargeWasPaidPage.selectYearAndContinue()
      NewExcessPaidPage.selectBothRadioButtonAndContinue()
      NewValueOfLumpSumPage.enterLumpSumAndContinue("500")
      NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("600")

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()

      When("I click sign out from the page")
      CheckYourAnswersLifetimeAllowancePage.signOutPage()

    }

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(Y),4.3,4.4(N),4.2 pages in the mural board */
    Scenario("LTA change of Benefit Crystallisation Event(No) journey", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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

    /** Below journey covers 0,1.1,1.3(Y),1.4,1.5(LTA),1.6,TaskList,4.1(N),4.2 pages in the mural board* */
    Scenario("LTA kick out journey", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      Then("I Should see the had-benefit-crystallisation-event page")
      HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()

      When("I select No and continue to next page")
      HadBenefitCrystallisationEventPage.selectNoAndContinueForLTAPage()

      Then("I Should see the not-able-to-use-this-service-lta page")
      NotAbleToUseThisServiceLtaPage.onNotAbleToUseThisServiceLtaPage()

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()
    }

    /** ---Below journey covers 4.0, 4.1, 4.3, 4.4, 4.5, 4.2 */
    Scenario("LTA previous LTA change(Yes) journey", ZapTests) {
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

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

      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

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
      PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage()

      When("I select any radio button and click continue")
      PercentageCausedChangeInChargePage.selectNoneRadioButtonAndContinue()

      Then("I Should see the not-able-to-use-this-service-lta page")
      NotAbleToUseThisServiceLtaPage.onNotAbleToUseThisServiceLtaPage()

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()
    }
  }

}
