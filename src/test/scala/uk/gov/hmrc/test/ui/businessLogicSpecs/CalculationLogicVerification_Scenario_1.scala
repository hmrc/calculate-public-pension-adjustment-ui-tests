package uk.gov.hmrc.test.ui.businessLogicSpecs

import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.BaseSpec
import uk.gov.hmrc.test.ui.specs.tags.{ISJJourney1, ZapTests}

class CalculationLogicVerification_Scenario_1 extends BaseSpec {

  val signInPage: String = TestConfiguration.optionalAuthFlag()

  Scenario("Calculation Logic Verification Scenario 1", ZapTests, ISJJourney1) {

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
    When("2.1 I land on Scottish taxpayer page, select no and continue to next page")
    ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()

    When("2.5 I land on paying-into-public-pension-scheme, select yes and continue to next page")
    PayingIntoPublicPensionSchemePage.selectYesAndContinueForAASPage()

    When("I land on defined-contribution-pension page, select yes and continue to next page")
    HaveDefinedContributionPensionPage.selectYesAndContinueForAASPage()

    When("I land on the when-flexibly-access-pension page select yes and continue to next page")
    HaveFlexiblyAccessedPensionPage.selectYesThenContinue()

    Then("I land on the when-flexibly-access-pension page, enter flexi access date 7/7/15 and continue")
    WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("01", "1", "2018")

    When("I land on pay-tax-charge 14/15 page, select yes and continue to next page")
    PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

    When("I verify check your answers page for annual allowance and click continue")
    CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

    Then("I verify annual allowance period shows up to 2022 in the task list")
    TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2022")

    //AA -Triage
    /** --- 2016 ---
      * 3.16.8,11,12,13,14,15,16
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("85000")

    When(
      "I verify PensionSchemeInputAmountsPage, enter period of 2016-post revised pension input amount"
    )
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("5000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ContributedOtherDbDcSchemePage, select no and continue")
    ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue()

    When("I verify TaxableIncomePage, enter Taxable income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("51600")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("1000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select no and continue")
    ContributeToReliefAtSourceSchemePage.selectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select Yes and continue")
    DonatedViaGiftAid.verifyPageSelectYesAndContinue()

    When("3.16.10.1 I verify DonatedViaGiftAidAmount page, enter amount and continue")
    DonatedViaGiftAidAmount.verifyPageEnterGiftAidAmountAndContinue("3000")

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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("55000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ContributedOtherDbDcSchemePage, select no and continue")
    ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue()

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("53000")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select yes and continue")
    AnySalarySacrificeArrangements.selectYesAndContinue()

    When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
    AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("3000")

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select yes and continue")
    FlexibleRemunerationsArrangements.selectYesAndContinue()

    When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
    AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("10000")

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select No and continue")
    AnyLumpSumDeathBenefitsPage.selectNoAndContinue()

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("2000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
    HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("20000")

    When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select no and continue")
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
    PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue("60000")

    When("I verify DidYouPayAChargePage, select no and continue")
    DidYouPayAChargePage.verifyPageSelectNoAndContinue()

    When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
    ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

    When("I verify WhichContributedDuringRemedyPeriodPage, select DC continue")
    WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue()

    When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue("10000")

    When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
    PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue("5000")

    When("I verify ThresholdIncomePage(2017-2020) page, select option IdoNotKnow and continue")
    ThresholdIncomePage.verifyPageSelectIdoNotKnowAndContinue()

    When("I verify TotalIncomePage page, enter total income and continue")
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("160000")

    When("3.16.1 I verify AnySalarySacrificeArrangements page, select yes and continue")
    AnySalarySacrificeArrangements.selectYesAndContinue()

    When("3.16.2 I verify AmountSalarySacrificeArrangements page, enter amount and continue")
    AmountSalarySacrificeArrangements.enterSalarySacrificeAmountAndContinue("3000")

    When("3.16.3 I verify FlexibleRemunerationsArrangements page, select yes and continue")
    FlexibleRemunerationsArrangements.selectYesAndContinue()

    When("3.16.4 I verify AmountFlexibleRemunerationArrangements page, enter amount and continue")
    AmountFlexibleRemunerationArrangements.enterFlexibleRemunerationAmountAndContinue("3000")

    When("3.16.6 I verify AnyLumpSumDeathBenefitsPage page, select Yes and continue")
    AnyLumpSumDeathBenefitsPage.selectYesAndContinue()

    When("3.16.7 I verify LumpSumDeathBenefitsValuePage page, enter lump sum and continue")
    LumpSumDeathBenefitsValuePage.enterLumpSumAndContinue("40000")

    When("3.16.8 I verify ClaimingTaxReliefPension page, select Yes and continue")
    ClaimingTaxReliefPension.verifyClaimingTaxReliefPensionSelectYesAndContinue()

    When("3.16.9 I verify TaxReliefAmount page, enter tax relief amount and continue")
    TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue("5000")

    When("3.16.5.1 I verify ContributeToReliefAtSourceSchemePage page, select yes and continue")
    ContributeToReliefAtSourceSchemePage.selectYesAndContinue()

    When("3.16.5.2 I verify HowMuchContributionReliefAtSourcePage page, enter amount and continue")
    HowMuchContributionReliefAtSourcePage.enterContributionReliefAmountAndContinue("10000")

    When("3.15.0 I verify KnowAdjustedAmountPage page, select no and continue")
    KnowAdjustedAmountPage.verifyPageSelectNoAndContinue()

    When("3.15.1 I verify ClaimingTaxReliefPensionPage page, select no and continue")
    ClaimingTaxReliefPensionPage.verifyPageSelectNoAndContinue()

    When("3.15.2 I verify HowMuchTaxReliefPensionPage page, enter amount and continue")
    HowMuchTaxReliefPensionPage.verifyPageEnterPensionPayAndContinue("30000")

    When("3.15.4 I verify AnyTaxReliefOverseasPensionPage page, select no and continue")
    AnyTaxReliefOverseasPensionPage.verifyPageSelectNoAndContinue()

    When("3.16.10 I verify DonatedViaGiftAid page, select no and continue")
    DonatedViaGiftAid.verifyPageSelectNoAndContinue()

    When("3.16.11 I verify DoYouKnowPersonalAllowancePage page, select no and continue")
    DoYouKnowPersonalAllowancePage.verifyPageSelectNoAndContinue()

    When("3.16.11.4 I verify TradeUnionRelief page, select no and continue")
    TradeUnionRelief.verifyPageSelectNoAndContinue()

    When("3.16.15 I verify BlindPersonAllowance page, select no and continue")
    BlindPersonAllowance.verifyClaimingBlindPersonAllowanceSelectNoAndContinue()

    /** verify check your answers page */
    CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

    //2019
  }

}