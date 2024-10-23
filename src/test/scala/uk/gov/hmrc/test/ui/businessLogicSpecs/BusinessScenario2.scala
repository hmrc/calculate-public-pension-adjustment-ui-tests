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
import uk.gov.hmrc.test.ui.specs.tags.{ISJJourney1, ZapTests}

class BusinessScenario2 extends BaseSpec {

  Feature("Business Scenario") {
    val signInPage: String = TestConfiguration.optionalAuthFlag()

    Scenario("Business Scenario 1") {

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

      When("0.19 I verify FlexibleAccessDcSchemePage page select yes and click continue")
      FlexibleAccessDcSchemePage.selectYesThenContinue()

      When("0.21 I verify ContributionToDefinedContributionSchemePage page select yes and click continue")
      ContributionToDefinedContributionSchemePage.selectYesThenContinue()

      When("I verify Benefit crystallisation event page select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("0.23 I verify LTA Charge page select Yes and continue to next page")
      LTACharge.selectYesAndContinueForLTAPage()

      When("0.24 I verify LTA Percentage change page select Yes and continue to next page")
      LifetimeAllowancePercentageChangePage.verifyLTAPercentageChangeSelectYesAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify eligible to use the Public Service Pensions adjustment service page click continue")
      EligibleToUseServicePage.clickContinueButton()

      When("I land on Scottish Taxpayer page, select yes and continue")
      ScottishTaxpayerFrom2016Page.selectYesAndContinueForAASPage()

      When("I land on Which Years Scottish Taxpayer page, select 16/17 and continue")
      WhichYearsScottishTaxpayer.selectScottishTaxpayerYearAndContinue("2017")

      When("I land on paying-into-public-scheme-page, select yes and click continue")
      PayingIntoPublicPensionSchemePage.selectYesThenContinue()

      When("I land on defined-contributions-scheme page, select no and continue")
      HaveDefinedContributionPensionPage.selectNoThenContinue()

      When("I land on pay-tax-charge 14/15 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesThenContinue()

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
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("80000")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("15000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify taxable-income, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("55000")

      When("3.16.8 I verify ClaimingTaxRelief Pension, select yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2110")

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

      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      /**
        * Income sub-journey 3.16.8 / 3.15.0 / 3.166 / 3.15.1/ 3.16.5 / 3.15.3,4,5 / 3.16.11,12,15,16
        */

      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("58000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2016-2017) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("55000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1710")

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
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2018 */

      When("I click Add details for 2017 to 2018")
      TaskListPage.clickAddDetailsFor2017To2018()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2018Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("38000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2016-2017) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("56000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2180")

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
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2320")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2019 */

      When("I click Add details for 2018 to 2019")
      TaskListPage.clickAddDetailsFor2018To2019()

      When("I verify what-you-will-need-aa/2018 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2019Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("50000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2018-2019) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("57000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2760")

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
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2390")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2020 */

      When("I click Add details for 2019 to 2020")
      TaskListPage.clickAddDetailsFor2019To2020()

      When("I verify what-you-will-need-aa/2019 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("55000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2019-2020) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("58000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("3050")

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
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2450")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2021 */

      When("I click Add details for 2020 to 2021")
      TaskListPage.clickAddDetailsFor2020To2021()

      When("I verify what-you-will-need-aa/2020 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("50000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2020-2021) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("58000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("3000")

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
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2500")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2022 */

      When("I click Add details for 2021 to 2022")
      TaskListPage.clickAddDetailsFor2021To2022()

      When("I verify what-you-will-need-aa/2021 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2022Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("55000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2021-2022) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("57500")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2410")

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
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2520")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2023 */

      When("I click Add details for 2022 to 2023")
      TaskListPage.clickAddDetailsFor2022To2023()

      When("I verify what-you-will-need-aa/2023 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2023Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("22000")

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ThresholdIncomePage(2022-2023) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify taxable-income page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("56000")

      When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
      ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

      When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("830")

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
      BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2600")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey */

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

      TaskListPage.clickCalculateButton()

      /** Total verification * */

      val outDatesCompensation = 0
      val inDatesDebit         = 16000
      val inDatesCredit        = 0

      assert(CalculationResultPage.getTotCompensation() == outDatesCompensation)
      assert(CalculationResultPage.getIncreasedTaxCharges() == inDatesDebit)
      assert(CalculationResultPage.getDecreasedTaxCharges() == inDatesCredit)

      /**
        * calculationResults Map = [Year-> [Amount on which tax is due,Total revised tax charge]]
        */

      val calculationResults: Map[Int, Array[Int]] = Map(
        2016 -> Array(15000, 6000),
        2017 -> Array(18000, 7200),
        2018 -> Array(0, 0),
        2019 -> Array(8000, 3200),
        2020 -> Array(15000, 6000),
        2021 -> Array(10000, 4000),
        2022 -> Array(15000, 6000)
      )

      for (year <- calculationResults.keys) {
        val calculationResult = calculationResults(year)
        assert(
          CalculationResultPage
            .getTaxYearInformation(
              year.toString,
              "Updated amount on which tax is due"
            )
            == calculationResult(0),
          year.toString + " revisedChargableAmountBeforeTaxRate is different"
        )
        assert(
          CalculationResultPage
            .getTaxYearInformation(
              year.toString,
              "Updated annual allowance tax charge amount"
            )
            == calculationResult(1),
          year.toString + " revisedChargableAmountAfterTaxRate is different"
        )
      }

      /**
        * Below commented assertions can be used later for further fields verifications as we verified only two fields at the moment
        */
      /*assert(
        CalculationResultPage
          .getTaxYearInformation(
            "2016",
            "Previous annual allowance tax charge amount that you paid"
          )
          == 0,
        " 2016 chargePaidByMember is different"
      )
      assert(
        CalculationResultPage
          .getTaxYearInformation(
            "2016",
            "Previous annual allowance tax charge amount paid by your scheme"
          )
          == 0,
        " 2016 chargePaidBySchemes is different"
      )
      assert(
        CalculationResultPage
          .getTaxYearInformation(
            "2016",
            "Amount of compensation that will be paid to you"
          )
          == 0,
        " 2016 directCompensation is different"
      )
      assert(
        CalculationResultPage
          .getTaxYearInformation(
            "2016",
            "Amount of compensation that will be paid as an increase to your scheme benefits"
          )
          == 0,
        " 2016 indirectCompensation is different"
      )
      assert(
        CalculationResultPage
          .getTaxYearInformation(
            "2016",
            "Unused annual allowance"
          )
          == 0,
        " 2016 unusedAnnualAllowance is different"
      )*/
    }
  }
}
