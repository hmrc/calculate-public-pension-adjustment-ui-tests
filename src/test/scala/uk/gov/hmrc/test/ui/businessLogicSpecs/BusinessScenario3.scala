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

package uk.gov.hmrc.test.ui.businessLogicSpecs

import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.BaseSpec

class BusinessScenario3 extends BaseSpec {

  Feature("Business Scenario 3") {
    val signInPage: String = TestConfiguration.optionalAuthFlag()

    Scenario("Business Scenario 3") {

      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I click on ContinueWithoutSignIn and move to next page")
      signInPage match {
        case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
        case _      =>
      }

      //setup journey
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

      When("0.31 I verify eligible to use the Public Service Pensions adjustment service page click continue")
      EligibleToUseServicePage.clickContinueButton()

      //AA Setup
      When("2.1 I land on Scottish taxpayer page, select no and continue to next page")
      ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

      When("2.5 I land on paying-into-public-pension-scheme, select yes and continue to next page")
      PayingIntoPublicPensionSchemePage.selectYesAndContinueForAASPage()

      When("I land on defined-contribution-pension page, select no  and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      When("I land on pay-tax-charge 14/15 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2022 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

      //AA -Triage
      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("70000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("10000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify TaxableIncomePage, enter Taxable income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("55000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select no and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */

      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("48000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("161000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select yes and continue")
      KnowAdjustedAmountPage.verifyPageSelectYesAndContinue()

      When("3.15 I verify AdjustedIncomePage page, enter amount and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("170000")

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select no and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2018 */

      When("I click Add details for 2017 to 2018")
      TaskListPage.clickAddDetailsFor2017To2018()

      When("I verify what-you-will-need-aa/2018 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2018Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("47500")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select yes and continue")
      KnowAdjustedAmountPage.verifyPageSelectYesAndContinue()

      When("3.15 I verify AdjustedIncomePage page, enter amount and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("165000")

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select no and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2019 */

      When("I click Add details for 2018 to 2019")
      TaskListPage.clickAddDetailsFor2018To2019()

      When("I verify what-you-will-need-aa/2019 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2019Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("35000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("15000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select yes and continue")
      KnowAdjustedAmountPage.verifyPageSelectYesAndContinue()

      When("3.15 I verify AdjustedIncomePage page, enter amount and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("180000")

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select no and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2020 */

      When("I click Add details for 2019 to 2020")
      TaskListPage.clickAddDetailsFor2019To2020()

      When("I verify what-you-will-need-aa/2020 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("38000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("80000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select no and continue")
      KnowAdjustedAmountPage.verifyPageSelectNoAndContinue()

      When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
      AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

      When("3.15.1 I verify ClaimingTaxReliefPensionPage page, select no and continue")
      ClaimingTaxReliefPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.5 I verify HowMuchContribution page, enter amount and continue")
      HowMuchContribution.enterPreReliefPensionContributionAndContinue("0")

      When("3.15.4 I verify AnyTaxReliefOverseasPensionPage page, select no and continue")
      AnyTaxReliefOverseasPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
      DonatedViaGiftAid.verifyPageSelectNoAndContinue()

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select no and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2021 */

      When("I click Add details for 2020 to 2021")
      TaskListPage.clickAddDetailsFor2020To2021()

      When("I verify what-you-will-need-aa/2021 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("50000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select no and continue")
      KnowAdjustedAmountPage.verifyPageSelectNoAndContinue()

      When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
      AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

      When("3.15.1 I verify ClaimingTaxReliefPensionPage page, select no and continue")
      ClaimingTaxReliefPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.5 I verify HowMuchContribution page, enter amount and continue")
      HowMuchContribution.enterPreReliefPensionContributionAndContinue("0")

      When("3.15.4 I verify AnyTaxReliefOverseasPensionPage page, select no and continue")
      AnyTaxReliefOverseasPensionPage.verifyPageSelectNoAndContinue()

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1200")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2500")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2022 */

      When("I click Add details for 2021 to 2022")
      TaskListPage.clickAddDetailsFor2021To2022()

      When("I verify what-you-will-need-aa/2021 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2022Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("52500")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select yes and continue")
      KnowAdjustedAmountPage.verifyPageSelectYesAndContinue()

      When("3.15 I verify AdjustedIncomePage page, enter amount and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("245000")

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1500")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2520")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2023 */

      When("I click Add details for 2022 to 2023")
      TaskListPage.clickAddDetailsFor2022To2023()

      When("I verify what-you-will-need-aa/2023 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2023Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("50000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

      When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
      ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

      When("3.15.0 I verify KnowAdjustedAmountPage page, select yes and continue")
      KnowAdjustedAmountPage.verifyPageSelectYesAndContinue()

      When("3.15 I verify AdjustedIncomePage page, enter amount and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("235000")

      When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
      DonatedViaGiftAid.verifyPageSelectYesAndContinue()

      When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
      DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("2000")

      When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
      DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

      When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
      TradeUnionRelief.verifyPageSelectNoAndContinue()

      When("3.16.15 I verify BlindPersonAllowance page, select yes and continue")
      BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

      When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2600")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      //end of AA Triage journey
      TaskListPage.clickCalculateButton()

      /** Total verification * */
      /**
        * calculationResults Map = [Year-> [Amount on which tax is due,Total revised tax charge]]
        */

      val calculationResults: Map[Int, Array[Int]] = Map(
        2016 -> Array(0, 0),
        2017 -> Array(18000, 8100),
        2018 -> Array(15000, 6750),
        2019 -> Array(10000, 4250),
        2020 -> Array(0, 0),
        2021 -> Array(8000, 3600),
        2022 -> Array(15000, 6750),
        2023 -> Array(10000, 4500)
      )

      for (year <- calculationResults.keys) {
        val calculationResult = calculationResults(year)

        CalculationResultPage.clickOnViewBreakdown(year.toString)
        val revisedChargableAmountBeforeTaxRate = f"£${calculationResult(0)}%,d"
        val revisedChargableAmountAfterTaxRate  = f"£${calculationResult(1)}%,d"

        assert(
          CalculationResultPage
            .getTaxYearInformationValue(
              year.toString,
              "Updated amount on which tax is due"
            )
            == revisedChargableAmountBeforeTaxRate,
          year.toString + " revisedChargableAmountBeforeTaxRate is different"
        )
        assert(
          CalculationResultPage
            .getTaxYearInformationValue(
              year.toString,
              "Updated annual allowance tax charge amount"
            )
            == revisedChargableAmountAfterTaxRate,
          year.toString + " revisedChargableAmountAfterTaxRate is different"
        )

        CalculationResultPage.clickOnReturnToSummaryOnDetailedBreakdownPage()
      }

    }
  }
}
