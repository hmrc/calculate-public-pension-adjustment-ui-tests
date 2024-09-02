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
import uk.gov.hmrc.test.ui.specs.tags.ISJJourney3

class IncomeSubJourneys3 extends BaseSpec {

  Feature("Calculate...") {
    val signInPage: String = TestConfiguration.optionalAuthFlag()

    Scenario("Income Sub-Journeys 13", ISJJourney3) {

      /** User not resubmitting, AA adjustment, non-Scottish taxpayer, left scheme 1/1/2017, no other schemes, charge paid 14/15. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (AA), 2.1 (N), 2.3 (N), 2.4 (1/1/2017), 2.5 (N), 2.8 (Y) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoThenContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesThenContinue()

      When("0.7 I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesThenContinue()

      When("0.9 I verify Protected Member page select no and click continue")
      ProtectedMember.selectNoThenContinue()

      When("0.10 I verify Annual allowance charge page select no and click continue")
      AnnualAllowanceCharge.selectNoThenContinue()

      When("0.11 I verify ContributionRefunds page select yes and click continue")
      ContributionRefunds.selectYesThenContinue()

      When("0.12 I verify IncomeOver100Page page select yes and click continue")
      IncomeOver100Page.selectYesThenContinue()

      When("I reach CYA and click continue")
      CheckYourAnswersLifetimeAllowancePage.clickContinueButton()

      When("I land on Scottish taxpayer page, select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      When("I land on paying-into-public-pension-scheme, select no and continue to next page")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

      When(
        "I land on stopped-paying-into-public-service-pension page, enter a valid date of 1/1/2017, and click continue"
      )
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I land on defined-contribution-pension page, select no and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      When("I land on pay-tax-charge-from2015-2016 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2017 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      /** AA Journey 1 - Setup journey 1 - No DC AA Journey */
      /** Below journey covers 3.1, 3.2 (N), 3.3, 3.4, 3.5 (Y), 3.6 (User), 3.7, 3.16 (60000), 3.17, task list */

      /** --- 2016 ---
        * 3.16.8,11,12,15,16
        */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "11123456BC")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("2000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
      ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

      When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
      HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("2000")

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2000")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      /**
        * Income sub-journey 3.16.1,2,3,4,6,7,8 / 3.15.0,1 / 3.16.5 / 3.15.3,4,5 / 3.16.11,12,15,16
        */

      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
      ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("300000")

      When("3.16.1 I verify AnySalarySacrificeArrangements page, select yes and continue")
      AnySalarySacrificeArrangements.selectYesAndContinue()

      When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
      AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("1000")

      When("3.16.3 I verify FlexibleRemunerationsArrangements page, select yes and continue")
      FlexibleRemunerationsArrangements.selectYesAndContinue()

      When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
      AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("2000")

      When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select Yes and continue")
      AnyLumpSumDeathBenefitsPage.selectYesAndContinue()

      When("3.16.7 I verify LumpSumDeathBenefitsValuePage page, enter lump sum and continue")
      LumpSumDeathBenefitsValuePage.enterLumpSumAndContinue("6000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select no and continue")
      KnowAdjustedAmountPage.verifyPageSelectNoAndContinue()

      When("3.15.1 I verify ClaimingTaxReliefPensionPage page, select no and continue")
      ClaimingTaxReliefPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.5 I verify HowMuchContribution page, enter amount and continue")
      HowMuchContribution.enterPreReliefPensionContributionAndContinue("3000")

      When("3.15.4 I verify AnyTaxReliefOverseasPensionPage page, select no and continue")
      AnyTaxReliefOverseasPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify TaxReliefPage page, enter tax relief amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2130")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue()

    }

    Scenario("Income Sub-Journeys 14", ISJJourney3) {

      /** User not resubmitting, AA adjustment, non-Scottish taxpayer, left scheme 1/1/2017, no other schemes, charge paid 14/15. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (AA), 2.1 (N), 2.3 (N), 2.4 (1/1/2017), 2.5 (N), 2.8 (Y) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoThenContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesThenContinue()

      When("0.7 I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesThenContinue()

      When("0.9 I verify Protected Member page select no and click continue")
      ProtectedMember.selectNoThenContinue()

      When("0.10 I verify Annual allowance charge page select no and click continue")
      AnnualAllowanceCharge.selectNoThenContinue()

      When("0.11 I verify ContributionRefunds page select yes and click continue")
      ContributionRefunds.selectYesThenContinue()

      When("0.12 I verify IncomeOver100Page page select yes and click continue")
      IncomeOver100Page.selectYesThenContinue()

      When("I reach CYA and click continue")
      CheckYourAnswersLifetimeAllowancePage.clickContinueButton()

      When("I land on Scottish taxpayer page, select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      When("I land on paying-into-public-pension-scheme, select no and continue to next page")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

      When(
        "I land on stopped-paying-into-public-service-pension page, enter a valid date of 1/1/2017, and click continue"
      )
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I land on defined-contribution-pension page, select no and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      When("I land on pay-tax-charge-from2015-2016 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2017 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      /** AA Journey 1 - Setup journey 1 - No DC AA Journey */
      /** Below journey covers 3.1, 3.2 (N), 3.3, 3.4, 3.5 (Y), 3.6 (User), 3.7, 3.16 (60000), 3.17, task list */

      /** --- 2016 ---
        * 3.16.8,11,12,15,16
        */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "11123456BC")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("2000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
      ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

      When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
      HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("2000")

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2000")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      /**
        * Income sub-journey 3.16.1,2,3,4,6,7,8 / 3.15.0,1 / 3.16.5 / 3.15.3,4,5 / 3.16.11,12,15,16
        */

      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
      ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("300000")

      When("3.16.1 I verify AnySalarySacrificeArrangements page, select yes and continue")
      AnySalarySacrificeArrangements.selectYesAndContinue()

      When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
      AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("1000")

      When("3.16.3 I verify FlexibleRemunerationsArrangements page, select yes and continue")
      FlexibleRemunerationsArrangements.selectYesAndContinue()

      When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
      AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("2000")

      When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select Yes and continue")
      AnyLumpSumDeathBenefitsPage.selectYesAndContinue()

      When("3.16.7 I verify LumpSumDeathBenefitsValuePage page, enter lump sum and continue")
      LumpSumDeathBenefitsValuePage.enterLumpSumAndContinue("6000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select no and continue")
      KnowAdjustedAmountPage.verifyPageSelectNoAndContinue()

      When("3.15.1 I verify ClaimingTaxReliefPensionPage page, select no and continue")
      ClaimingTaxReliefPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.5 I verify HowMuchContribution page, enter amount and continue")
      HowMuchContribution.enterPreReliefPensionContributionAndContinue("3000")

      When("3.15.4 I verify AnyTaxReliefOverseasPensionPage page, select no and continue")
      AnyTaxReliefOverseasPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify TaxReliefPage page, enter tax relief amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2130")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue()

    }

    Scenario("Income Sub-Journeys 15", ISJJourney3) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 1/1/2016, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (Y), 1.4, 1.5 (AA and LTA), 1.6, 2.1 (Y), 2.2 (16/17), 2.3 (Y), 2.5 (Y), 2.6 (Y),
        * 2.7 (1/1/2016), 2.8 (N), 2.8.1 (y), 2.8.2, 2.8.3 (Y), 2.8.4, 2.8.5 (Y), 2.9, 2.9.1 (Y), 2.10, 2.10.1 (Y), 2.11, 2.12
        */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoThenContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesThenContinue()

      When("0.7 I click Annual allowance,LTA and click continue")
      ReportingChangePage.selectBothAAAndLTAContinue()

      When("0.8 I select I have not received remedial service statement select yes and continue to next page")
      SavingsStatementPage.selectYesThenContinue()

      When("0.9 I verify Protected Member page select no and click continue")
      ProtectedMember.selectNoThenContinue()

      When("0.10 I verify Annual allowance charge page select no and click continue")
      AnnualAllowanceCharge.selectNoThenContinue()

      When("0.11 I verify ContributionRefunds page select yes and click continue")
      ContributionRefunds.selectYesThenContinue()

      When("0.12 I verify IncomeOver100Page page select no and click continue")
      IncomeOver100Page.selectNoThenContinue()

      When("0.13 I verify IncomeOver190Page page select no and click continue")
      IncomeOver190Page.selectNoThenContinue()

      When("0.15 I verify PIAAmountIncreasedPage page select no and click continue")
      PIAAmountIncreasedPage.selectNoAndContinue()

      When("0.17 I verify PPIAAboveAnnualAllowanceLimit22To23Page page select no and click continue")
      PIAAboveAnnualAllowanceLimit22To23Page.selectNoThenContinue()

      When("0.18 I verify IncomeOver190Page page select no and click continue")
      IncomeOver190Page.selectNoThenContinue()

      When("0.19 I verify FlexibleAccessDcSchemePage page select no and click continue")
      FlexibleAccessDcSchemePage.selectNoThenContinue()

      When("0.20 I verify TriageJourneyNotImpactedPIADecreasePage page and click continue")
      TriageJourneyNotImpactedPIADecreasePage.clickContinueButton()

      When("0.22 I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesThenContinue()

      When("0.23 I verify LTA Charge page select No and continue to next page")
      LTACharge.selectNoAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("0.25 I verify LTA Percentage increase page select Yes and continue to next page")
      LifetimeAllowancePercentageIncreasePage.verifyLTAPercentageIncreaseSelectYesAndContinue()

      When("0.26 I verify LTA New Charge page select Yes and continue to next page")
      LifetimeAllowanceNewChargePage.selectYesAndContinueForLTAPage()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I land on Scottish Taxpayer page, select yes and continue")
      ScottishTaxpayerFrom2016Page.selectYesAndContinueForAASPage()

      When("I land on Which Years Scottish Taxpayer page, select 16/17 and continue")
      WhichYearsScottishTaxpayer.selectScottishTaxpayerYearAndContinue("2017")

      When("I land on paying-into-public-scheme-page, select no and click continue")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

      Then("I Should see the when-stop-paying-public-pension page")
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I land on defined-contributions-scheme page, select yes and continue")
      HaveDefinedContributionPensionPage.selectYesAndContinueForAASPage()

      When("I select yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesThenContinue()

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 1/1/16 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("01", "1", "2016")

      When("I land on pay-tax-charge 14/15 page, select No and continue to next page")
      PayTaxChargeFrom20152016Page.selectNoAndContinueForAASPage()

      Then("I Should see the pia-pre-remedy/2011 page")
      Registered.onRegisteredPageSelectYesAndContinue("2010", "2011")
      PiaPreRemedyPage2011.onPiaPreRemedyPage2011PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2012 page")
      Registered.onRegisteredPageSelectYesAndContinue("2011", "2012")
      PiaPreRemedyPage2012.onPiaPreRemedyPage2012PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2013 page")
      Registered.onRegisteredPageSelectYesAndContinue("2012", "2013")
      PiaPreRemedyPage2013.onPiaPreRemedyPage2013PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2014 page")
      Registered.onRegisteredPageSelectYesAndContinue("2013", "2014")
      PiaPreRemedyPage2014.onPiaPreRemedyPage2014PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2015 page")
      Registered.onRegisteredPageSelectYesAndContinue("2014", "2015")
      PiaPreRemedyPage2015.onPiaPreRemedyPage2015PageEnterValueAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      // end of setup journey
      /** DC AA journey 2 */
      /** Below journey covers 3.1, 3.2 ... */

      /** --- 2016 ---
        * 3.16.8,10(y),10.1,11(n),11.1(n),15,16
        */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("2000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )
      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("10000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select no and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 --- */
      /**
        * Income sub-journey 3.16.8,10(n),11(n),11.4(n),15,16
        */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00123456AA")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("17000")

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify WhoPaidAnnualAllowanceChargePage, select NHS pension scheme and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue()

      When("I verify HowMuchYouPayChargePage, enter amount you paid and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("500")

      When("I verify HowMuchPensionPayChargePage, enter amount pension scheme paid and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("500")

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter MyCSP pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456BB")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Police pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Police", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Army pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Fire pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ContributedOtherDbDcSchemePage page, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue()

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("10000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select no and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue()

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

    Scenario("Income Sub-Journeys 16", ISJJourney3) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 1/1/2016, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (Y), 1.4, 1.5 (AA and LTA), 1.6, 2.1 (Y), 2.2 (16/17), 2.3 (Y), 2.5 (Y), 2.6 (Y),
        * 2.7 (1/1/2016), 2.8 (N), 2.8.1 (y), 2.8.2, 2.8.3 (Y), 2.8.4, 2.8.5 (Y), 2.9, 2.9.1 (Y), 2.10, 2.10.1 (Y), 2.11, 2.12
        */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoThenContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesThenContinue()

      When("0.7 I click Annual allowance,LTA and click continue")
      ReportingChangePage.selectBothAAAndLTAContinue()

      When("0.8 I select I have not received remedial service statement select yes and continue to next page")
      SavingsStatementPage.selectYesThenContinue()

      When("0.9 I verify Protected Member page select no and click continue")
      ProtectedMember.selectNoThenContinue()

      When("0.10 I verify Annual allowance charge page select no and click continue")
      AnnualAllowanceCharge.selectNoThenContinue()

      When("0.11 I verify ContributionRefunds page select yes and click continue")
      ContributionRefunds.selectYesThenContinue()

      When("0.12 I verify IncomeOver100Page page select no and click continue")
      IncomeOver100Page.selectNoThenContinue()

      When("0.13 I verify IncomeOver190Page page select no and click continue")
      IncomeOver190Page.selectNoThenContinue()

      When("0.15 I verify PIAAmountIncreasedPage page select no and click continue")
      PIAAmountIncreasedPage.selectNoAndContinue()

      When("0.17 I verify PPIAAboveAnnualAllowanceLimit22To23Page page select no and click continue")
      PIAAboveAnnualAllowanceLimit22To23Page.selectNoThenContinue()

      When("0.18 I verify IncomeOver190Page page select no and click continue")
      IncomeOver190Page.selectNoThenContinue()

      When("0.19 I verify FlexibleAccessDcSchemePage page select no and click continue")
      FlexibleAccessDcSchemePage.selectNoThenContinue()

      When("0.20 I verify TriageJourneyNotImpactedPIADecreasePage page and click continue")
      TriageJourneyNotImpactedPIADecreasePage.clickContinueButton()

      When("I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select Yes and continue to next page")
      LTACharge.selectYesAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I land on Scottish Taxpayer page, select yes and continue")
      ScottishTaxpayerFrom2016Page.selectYesAndContinueForAASPage()

      When("I land on Which Years Scottish Taxpayer page, select 16/17 and continue")
      WhichYearsScottishTaxpayer.selectScottishTaxpayerYearAndContinue("2017")

      When("I land on paying-into-public-scheme-page, select no and click continue")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

      Then("I Should see the when-stop-paying-public-pension page")
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I land on defined-contributions-scheme page, select yes and continue")
      HaveDefinedContributionPensionPage.selectYesAndContinueForAASPage()

      When("I select yes and continue to next page")
      HaveFlexiblyAccessedPensionPage.selectYesThenContinue()

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 1/1/16 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("01", "1", "2016")

      When("I land on pay-tax-charge 14/15 page, select No and continue to next page")
      PayTaxChargeFrom20152016Page.selectNoAndContinueForAASPage()

      Then("I Should see the pia-pre-remedy/2011 page")
      Registered.onRegisteredPageSelectYesAndContinue("2010", "2011")
      PiaPreRemedyPage2011.onPiaPreRemedyPage2011PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2012 page")
      Registered.onRegisteredPageSelectYesAndContinue("2011", "2012")
      PiaPreRemedyPage2012.onPiaPreRemedyPage2012PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2013 page")
      Registered.onRegisteredPageSelectYesAndContinue("2012", "2013")
      PiaPreRemedyPage2013.onPiaPreRemedyPage2013PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2014 page")
      Registered.onRegisteredPageSelectYesAndContinue("2013", "2014")
      PiaPreRemedyPage2014.onPiaPreRemedyPage2014PageEnterValueAndContinue()

      Then("I Should see the pia-pre-remedy/2015 page")
      Registered.onRegisteredPageSelectYesAndContinue("2014", "2015")
      PiaPreRemedyPage2015.onPiaPreRemedyPage2015PageEnterValueAndContinue()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      // end of setup journey
      /** DC AA journey 2 */
      /** Below journey covers 3.1, 3.2 ... */

      /** --- 2016 ---
        * 3.16.8,11,12,15,16
        */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("2000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )
      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify TaxReliefPage page, enter tax relief amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2030")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 --- */
      /**
        * Income sub-journey 3.16.8,9,11,12,13,14,15,16
        */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00123456AA")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("17000")

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify WhoPaidAnnualAllowanceChargePage, select NHS pension scheme and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue()

      When("I verify HowMuchYouPayChargePage, enter amount you paid and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("500")

      When("I verify HowMuchPensionPayChargePage, enter amount pension scheme paid and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("500")

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter MyCSP pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456BB")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Police pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Police", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Army pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Fire pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("6000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ContributedOtherDbDcSchemePage page, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue()

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("1000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("200000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
      ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

      When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
      HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("2000")

      When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify TaxReliefPage page, enter tax relief amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2030")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue()

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

    Scenario("Income Sub-Journeys 17", ISJJourney3) {

      /** User not resubmitting, AA adjustment, non-Scottish taxpayer, left scheme 1/1/2017, no other schemes, charge paid 14/15. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (AA), 2.1 (N), 2.3 (N), 2.4 (1/1/2017), 2.5 (N), 2.8 (Y) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoThenContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesThenContinue()

      When("0.7 I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesThenContinue()

      When("0.9 I verify Protected Member page select no and click continue")
      ProtectedMember.selectNoThenContinue()

      When("0.10 I verify Annual allowance charge page select no and click continue")
      AnnualAllowanceCharge.selectNoThenContinue()

      When("0.11 I verify ContributionRefunds page select yes and click continue")
      ContributionRefunds.selectYesThenContinue()

      When("0.12 I verify IncomeOver100Page page select yes and click continue")
      IncomeOver100Page.selectYesThenContinue()

      When("I reach CYA and click continue")
      CheckYourAnswersLifetimeAllowancePage.clickContinueButton()

      When("I land on Scottish taxpayer page, select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      When("I land on paying-into-public-pension-scheme, select no and continue to next page")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

      When(
        "I land on stopped-paying-into-public-service-pension page, enter a valid date of 1/1/2017, and click continue"
      )
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I land on defined-contribution-pension page, select no and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      When("I land on pay-tax-charge-from2015-2016 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2017 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      /** AA Journey 1 - Setup journey 1 - No DC AA Journey */
      /** Below journey covers 3.1, 3.2 (N), 3.3, 3.4, 3.5 (Y), 3.6 (User), 3.7, 3.16 (60000), 3.17, task list */

      /** --- 2016 ---
        * 3.16.8,11,12,15,16
        */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "11123456BC")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("2000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
      ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

      When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
      HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("2000")

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2000")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      /**
        * Income sub-journey 3.16.1,2,3,4,5,6,7,8,11,12,13,14,15,16
        */

      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
      ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("1000")

      When("3.16.1 I verify AnySalarySacrificeArrangements page, select yes and continue")
      AnySalarySacrificeArrangements.selectYesAndContinue()

      When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
      AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("1000")

      When("3.16.3 I verify FlexibleRemunerationsArrangements page, select no and continue")
      FlexibleRemunerationsArrangements.selectNoAndContinue()

      When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select Yes and continue")
      AnyLumpSumDeathBenefitsPage.selectYesAndContinue()

      When("3.16.7 I verify LumpSumDeathBenefitsValuePage page, enter lump sum and continue")
      LumpSumDeathBenefitsValuePage.enterLumpSumAndContinue("5000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify TaxReliefPage page, enter tax relief amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2130")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue()

    }

    Scenario("Income Sub-Journeys 18", ISJJourney3) {

      /** User not resubmitting, AA adjustment, non-Scottish taxpayer, left scheme 1/1/2017, no other schemes, charge paid 14/15. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (AA), 2.1 (N), 2.3 (N), 2.4 (1/1/2017), 2.5 (N), 2.8 (Y) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoThenContinue()

      When("I verify Affected by remedy page select yes and click continue")
      AffectedByRemedyPage.selectYesThenContinue()

      When("0.7 I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("0.8 I select I have not received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesThenContinue()

      When("0.9 I verify Protected Member page select no and click continue")
      ProtectedMember.selectNoThenContinue()

      When("0.10 I verify Annual allowance charge page select no and click continue")
      AnnualAllowanceCharge.selectNoThenContinue()

      When("0.11 I verify ContributionRefunds page select yes and click continue")
      ContributionRefunds.selectYesThenContinue()

      When("0.12 I verify IncomeOver100Page page select yes and click continue")
      IncomeOver100Page.selectYesThenContinue()

      When("I reach CYA and click continue")
      CheckYourAnswersLifetimeAllowancePage.clickContinueButton()

      When("I land on Scottish taxpayer page, select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      When("I land on paying-into-public-pension-scheme, select no and continue to next page")
      PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()

      When(
        "I land on stopped-paying-into-public-service-pension page, enter a valid date of 1/1/2017, and click continue"
      )
      WhenStopPayingPublicPensionPage.enterValid2016_2017_DateAndContinue()

      When("I land on defined-contribution-pension page, select no and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      When("I land on pay-tax-charge-from2015-2016 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2017 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2017")

      /** AA Journey 1 - Setup journey 1 - No DC AA Journey */
      /** Below journey covers 3.1, 3.2 (N), 3.3, 3.4, 3.5 (Y), 3.6 (User), 3.7, 3.16 (60000), 3.17, task list */

      /** --- 2016 ---
        * 3.16.8,11,12,13,15,16
        */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "11123456BC")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("2000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

      When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
      PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("2000")

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      /**
        * Income sub-journey 3.16.1,2,3,4,5,6,7,8,9,10(n),11(n),11.4(n),15,16
        */

      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("8000")

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
      ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("43001")

      When("3.16.1 I verify AnySalarySacrificeArrangements page, select yes and continue")
      AnySalarySacrificeArrangements.selectYesAndContinue()

      When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
      AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("1000")

      When("3.16.3 I verify FlexibleRemunerationsArrangements page, select yes and continue")
      FlexibleRemunerationsArrangements.selectYesAndContinue()

      When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
      AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("2000")

      When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select Yes and continue")
      AnyLumpSumDeathBenefitsPage.selectYesAndContinue()

      When("3.16.7 I verify LumpSumDeathBenefitsValuePage page, enter lump sum and continue")
      LumpSumDeathBenefitsValuePage.enterLumpSumAndContinue("5000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("200000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select no and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue()

    }

  }
}
