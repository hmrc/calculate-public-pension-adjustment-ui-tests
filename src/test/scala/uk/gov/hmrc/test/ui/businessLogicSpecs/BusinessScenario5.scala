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

class BusinessScenario5 extends BaseSpec {

  val signInPage: String = TestConfiguration.optionalAuthFlag()

  Scenario("Calculation Logic Verification Scenario 5") {

    Given("I am on the Public Service Pensions Remediation home page")
    HomePage.goToHomepage()

    When("0.1 I click on ContinueWithoutSignIn and move to next page")
    signInPage match {
      case "true" => SignInGovernmentGateway.ContinueWithoutSignIn()
      case _      =>
    }

    //setup journey
    When("0.3 I select I'm not resubmitting the adjustment and click continue")
    ResubmittingAdjustmentPage.selectNoThenContinue()

    When("0.5 I verify Affected by remedy page select yes and click continue")
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
    When("I land on Scottish Taxpayer page, select yes and continue")
    ScottishTaxpayerFrom2016Page.selectYesAndContinueForAASPage()

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxPayerYear("2017")

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxPayerYear("2018")

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxPayerYear("2019")

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxPayerYear("2020")

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxPayerYear("2021")

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxPayerYear("2022")

    When("I land on Which Years Scottish Taxpayer page, select all the years and continue")
    WhichYearsScottishTaxpayer.selectScottishTaxpayerYearAndContinue("2023")

    When("I land on paying-into-public-scheme-page, select yes and click continue")
    PayingIntoPublicPensionSchemePage.selectYesAndContinueForAASPage()

    When("I land on defined-contributions-scheme page, select no and continue")
    HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

    When("I land on pay-tax-charge 14/15 page, select Yes and continue to next page")
    PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

    When("I verify check your answers page for annual allowance and click continue")
    CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

    Then("I verify annual allowance period shows up to 2022 in the task list")
    TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

    /** --- 2016 */

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

    When(
      "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
    )
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify TaxableIncomePage, enter Taxable income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("47890")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

    When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
    TradeUnionRelief.verifyPageSelectNoAndContinue()

    When("3.16.15 I verify BlindPersonAllowance page, select Yes and continue")
    BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectYesAndContinue()

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, enter BlindPersonsAllowance amount and continue")
    BlindPersonsAllowanceAmount.enterBlindPersonsAllowanceAmount("2290")

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("40000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("50790")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("42000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("53320")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("400")

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

    When("I verify what-you-will-need-aa/2019 page and click continue")
    WhatYouWillNeedAaPage.onWhatYouWillNeedAa2019Page()

    Then("I verify member-more-than-one-pension page and click continue")
    MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

    When("I verify WhichSchemeDetailsPage and select an existing scheme")
    WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

    When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("47000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("55740")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("600")

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

    When("I verify what-you-will-need-aa/2020 page and click continue")
    WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()

    Then("I verify member-more-than-one-pension page and click continue")
    MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

    When("I verify WhichSchemeDetailsPage and select an existing scheme")
    WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

    When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("50000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60450")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("800")

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

    When("I verify what-you-will-need-aa/2021 page and click continue")
    WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()

    Then("I verify member-more-than-one-pension page and click continue")
    MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

    When("I verify WhichSchemeDetailsPage and select an existing scheme")
    WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

    When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("56000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("72500")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1000")

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

    When("I verify what-you-will-need-aa/2022 page and click continue")
    WhatYouWillNeedAaPage.onWhatYouWillNeedAa2022Page()

    Then("I verify member-more-than-one-pension page and click continue")
    MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

    When("I verify WhichSchemeDetailsPage and select an existing scheme")
    WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

    When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("61000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("83590")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1200")

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

    Then("I verify member-more-than-one-pension page and click continue")
    MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

    When("I verify WhichSchemeDetailsPage and select an existing scheme")
    WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00348916RX")

    When("I verify PensionSchemeInputAmountsPage, enter revised pension input amount")
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("69000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
    ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("85670")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("500")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.11 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1400")

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

    //end of AA Triage journey
    TaskListPage.clickCalculateButton()

    /** Total verification * */

    val outDatesCompensation = 0
    val inDatesDebit         = 31160
    val inDatesCredit        = 0

    assert(CalculationResultPage.getTotCompensation() == outDatesCompensation)
    assert(CalculationResultPage.getIncreasedTaxCharges() == inDatesDebit)
    assert(CalculationResultPage.getDecreasedTaxCharges() == inDatesCredit)

    //      calculationResults Map = [Year-> [Amount on which tax is due,Total revised tax charge]]

    val calculationResults: Map[Int, Array[Int]] = Map(
      2016 -> Array(0, 0),
      2017 -> Array(0, 0),
      2018 -> Array(2000, 800),
      2019 -> Array(7000, 2870),
      2020 -> Array(10000, 4100),
      2021 -> Array(16000, 6560),
      2022 -> Array(21000, 8610),
      2023 -> Array(29000, 11890)
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

  }

}
