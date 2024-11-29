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

import org.scalatest.Assertions.assert
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages.HomePage.sanitiseAmount
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.BaseSpec

class BusinessScenario4 extends BaseSpec {

  val signInPage: String = TestConfiguration.optionalAuthFlag()

  Scenario("Business Scenario 4") {

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("25000")

    When(
      "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
    )
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("60000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify TaxableIncomePage, enter Taxable income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("56600")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

    When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
    PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("10600")

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select no and continue")
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("49000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("61000")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select Yes and continue")
    AnySalarySacrificeArrangements.selectYesAndContinue()

    When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
    AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("1000")

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select Yes and continue")
    FlexibleRemunerationsArrangements.selectYesAndContinue()

    When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
    AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("2000")

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
    HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("5000")

    When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

    When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
    PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("11000")

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select no and continue")
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("60000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("35500")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select No and continue")
    AnySalarySacrificeArrangements.selectNoAndContinue()

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select No and continue")
    FlexibleRemunerationsArrangements.selectNoAndContinue()

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
    HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("5000")

    When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

    When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
    PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("11500")

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select no and continue")
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("80000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("26850")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select No and continue")
    AnySalarySacrificeArrangements.selectNoAndContinue()

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select No and continue")
    FlexibleRemunerationsArrangements.selectNoAndContinue()

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
    HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("5000")

    When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("800")

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

    When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
    PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("11850")

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select no and continue")
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("67000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("62600")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select Yes and continue")
    AnySalarySacrificeArrangements.selectYesAndContinue()

    When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
    AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("3000")

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select Yes and continue")
    FlexibleRemunerationsArrangements.selectYesAndContinue()

    When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
    AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("1000")

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select No and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectNoAndContinue()

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
    HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("10000")

    When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

    When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
    PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("12500")

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select no and continue")
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("120000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("88500")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select Yes and continue")
    AnySalarySacrificeArrangements.selectYesAndContinue()

    When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
    AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("1000")

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select Yes and continue")
    FlexibleRemunerationsArrangements.selectYesAndContinue()

    When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
    AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("2000")

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select No and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select No and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select yes and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectYesAndContinue()

    When("3.16.12 I verify PersonalAllowancePage page, enter allowance amount and continue")
    PersonalAllowancePage.verifyPageEnterPersonalAllowanceAndContinue("12500")

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select No and continue")
    BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("150000")

    When("I verify DidYouPayAChargePage, select No and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select No and continue")
    AnySalarySacrificeArrangements.selectNoAndContinue()

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select No and continue")
    FlexibleRemunerationsArrangements.selectNoAndContinue()

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("430")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select No and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1600")

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

    When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
    TradeUnionRelief.verifyPageSelectNoAndContinue()

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select No and continue")
    BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("100000")

    When("I verify DidYouPayAChargePage, select No and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("162000")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select Yes and continue")
    AnySalarySacrificeArrangements.selectYesAndContinue()

    When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
    AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("10000")

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select Yes and continue")
    FlexibleRemunerationsArrangements.selectYesAndContinue()

    When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
    AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("40000")

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select Yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5 I verify HowMuchContribution page, enter amount and continue")
    HowMuchContribution.enterPreReliefPensionContributionAndContinue("40000")

    When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("1600")

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select No and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

    When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
    TradeUnionRelief.verifyPageSelectNoAndContinue()

    When("3.16.16 I verify BlindPersonsAllowanceAmount page, select No and continue")
    BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

    /** verify check your answers page */
    CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

    //end of AA Triage journey
    TaskListPage.clickCalculateButton()

    /** Total verification * */

    /*val outDatesCompensation = 0
    val inDatesDebit         = 120541
    val inDatesCredit        = 0

    assert(CalculationResultPage.getTotCompensation() == outDatesCompensation)
    assert(CalculationResultPage.getIncreasedTaxCharges() == inDatesDebit)
    assert(CalculationResultPage.getDecreasedTaxCharges() == inDatesCredit)
*/
    //      calculationResults Map = [Year-> [Amount on which tax is due,Total revised tax charge]]

    val calculationResults: Map[Int, Array[Int]] = Map(
      2016 -> Array(20000, 8000),
      2017 -> Array(9000, 3600),
      2018 -> Array(20000, 5500),
      2019 -> Array(40000, 11852),
      2020 -> Array(27000, 11070),
      2021 -> Array(80000, 33050),
      2022 -> Array(110000, 50600),
      2023 -> Array(60000, 25821)
    )

    for (year <- calculationResults.keys) {
      if (BreakdownPage.goToYearBreakdown(year)) {
        val calculationResult = calculationResults(year)

        val rawUpdatedAmount = CalculationResultPage.getTaxYearInformation(
          year.toString,
          "Updated amount on which tax is due"
        )
        println(s"Raw updated amount for year $year: $rawUpdatedAmount")

        /** Get updated amount from the page */
        val updatedAmount = CalculationResultPage.getTaxYearInformation(
          year.toString,
          "Updated amount on which tax is due"
        )

        /** Get updated tax charge from the page */
        val updatedTaxCharge = CalculationResultPage.getTaxYearInformation(
          year.toString,
          "Updated annual allowance tax charge amount"
        )

        /** assertions */
        assert(
          updatedAmount == calculationResult(0),
          s"$year revisedChargableAmountBeforeTaxRate is different. Expected: ${calculationResult(0)}, Got: $updatedAmount"
        )
        assert(
          updatedTaxCharge == calculationResult(1),
          s"$year revisedChargableAmountAfterTaxRate is different. Expected: ${calculationResult(1)}, Got: $updatedTaxCharge"
        )

        // Return to the main page
        BreakdownPage.returnToMainpage()
      } else {
        println(s"Skipping year $year due to missing breakdown page")
      }
    }
  }
}
