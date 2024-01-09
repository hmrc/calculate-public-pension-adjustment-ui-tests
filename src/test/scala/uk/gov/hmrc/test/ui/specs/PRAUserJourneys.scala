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

import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests
class PRAUserJourneys extends BaseSpec {

  Feature("Calculate...") {

    Scenario("Setup Journey 1", ZapTests) {

      /** User not eligible */
      /** Below journey covers 0, 1.1 (N), 1.2 */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectNoAndContinueForGSPage()

    }

    Scenario("Setup Journey 2, and LTA journey 1", ZapTests) {

      /** User not resubmitting, submitting LTA adjustment only, */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 1 */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select No and continue to next page")
      HadBenefitCrystallisationEventPage.selectNoAndContinueForLTAPage()

      When("I click sign out from the page")
      NotAbleToUseThisServiceLtaPage.signOutPage()

    }

    Scenario("Setup Journey 3, AA Journey 1", ZapTests) {

      /** User not resubmitting, AA adjustment, non-Scottish taxpayer, left scheme 1/1/2017, no other schemes, charge paid 14/15. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (AA), 2.1 (N), 2.3 (N), 2.4 (1/1/2017), 2.5 (N), 2.8 (Y) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectAnnualAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

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

      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify WhoPaidAnnualAllowanceChargePage, select you and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue(
        "2016-pre",
        "0",
        "6 April 2015 to 8 July 2015",
        "NHS"
      )

      When("I verify HowMuchYouPayChargePage, enter amount and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", "500")

      When("I verify TotalIncomePage, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      Then("I verify CYA Page and click continue")
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      Then("I verify member-more-than-one-pension page")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "20000",
        "20000"
      )

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "0")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

      /** --- 2017 */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()

      When("I verify what-you-will-need-aa/2017 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page and click continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "11123456BC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "8000",
        "8000"
      )

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "0")

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "8000",
        "8000"
      )

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "1")

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "8000",
        "8000"
      )

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "2")

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "8000",
        "8000"
      )

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select no and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2017", "3")

      When("I verify ThresholdIncomePage(2017-2020) page, select no and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")

    }

    Scenario("Setup Journey 4, AA Journey 2, LTA Journey 6", ZapTests) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 1/1/2016, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (Y), 1.4, 1.5 (AA and LTA), 1.6, 2.1 (Y), 2.2 (16/17), 2.3 (Y), 2.5 (Y), 2.6 (Y),
        * 2.7 (1/1/2016), 2.8 (N), 2.8.1 (y), 2.8.2, 2.8.3 (Y), 2.8.4, 2.8.5 (Y), 2.9, 2.9.1 (Y), 2.10, 2.10.1 (Y), 2.11, 2.12
        */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I am resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectYesAndContinueForGSPage()

      When("I land on resubmission reason page, enter reason and click continue")
      ReasonForResubmissionPage.enterReasonAndContinue()

      When("I select both Annual allowance and LTA, and click continue")
      ReportingChangePage.selectBothAAAndLTAContinue()

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
      HaveFlexiblyAccessedPensionPage.selectYesAndContinueForGSPage()

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

      /** --- 2016 Pre --- */
      When("I click AddDetailsFor6AprilTo8July2015")
      TaskListPage.clickAddDetailsFor6AprilTo8July2015()
      When("I verify 2016 pre page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()

      Then("I verify member-more-than-one-pension page,select yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "22500",
        "20000"
      )

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify WhoPaidAnnualAllowanceChargePage, select pension scheme and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
        "2016-pre",
        "0",
        "6 April 2015 to 8 July 2015",
        "NHS"
      )

      When("I verify HowMuchPensionPayChargePage, enter amount and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("500")

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", "0")

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456BB")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "15000",
        "15000"
      )

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify HowMuchPensionPayChargePage, enter amount and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("500")

      When("I verify AddAnotherSchemePage page, select NO and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", "1")

      When("I verify ContributedOtherDbDcSchemePage page, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue("2016-pre")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-pre",
        "5000",
        "6 April 2015",
        "8 July 2015"
      )

      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("120000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-pre")

      /** --- 2016 Post --- */
      When("I click Add details for 9 July to 5 April 2016")
      TaskListPage.clickAddDetailsFor9JulyTo8July2016()
      Then("I verify WhatYouWillNeedAaPage with the remedy period")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()

      Then("I verify member-more-than-one-pension page,select yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00123456AA")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "17500",
        "15000"
      )

      When("I verify DidYouPayAChargePage, select yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify WhoPaidAnnualAllowanceChargePage, select you and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue(
        "2016-post",
        "0",
        "9 July 2015 to 5 April 2016",
        "NHS"
      )

      When("I verify HowMuchYouPayChargePage, select you pay and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-post", "0", "500")

      When("I verify AddAnotherSchemePage page, select Yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-post", "0")

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("MyCSP", "00123456BB")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "10000",
        "100000"
      )

      When("I verify DidYouPayAChargePage page, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select No and continue")
      AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", "1")

      When("I verify ContributedOtherDbDcSchemePage, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DC and DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")

      When("I verify PiaForDcPensionPage, enter pension amount for DC and continue")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "5000",
        "9 July 2015",
        "1 January 2016"
      )

      When("I verify PiaForDcPensionFlexiblePage, enter pension amount for DC and continue")
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        "5000",
        "2 January 2016",
        "5 April 2016"
      )

      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "5000"
      )

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2016-post")

      /** --- 2017 --- */
      When("I click Add details for 2016 to 2017")
      TaskListPage.clickAddDetailsFor2016To2017()
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()

      Then("I verify member-more-than-one-pension page,select Yes and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()

      When("I verify WhichSchemeDetailsPage and select an existing scheme")
      WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue("NHS", "00123456AA")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "12500",
        "17000"
      )

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectYesAndContinue()

      When("I verify WhoPaidAnnualAllowanceChargePage, select NHS pension scheme and continue")
      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue("2017", "0", "2017", "NHS")

      When("I verify HowMuchYouPayChargePage, enter amount you paid and continue")
      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2017", "0", "500")

      When("I verify HowMuchPensionPayChargePage, enter amount pension scheme paid and continue")
      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue("500")

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "0")

      When("I verify page and select new pension scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter MyCSP pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("MyCSP", "00123456BB")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "6000",
        "6000"
      )

      When("I verify DidYouPayAChargePage page, select Yes and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select yes and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "1")

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Police pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Police", "00123456CC")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "6000",
        "6000"
      )

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "2")

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Army pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("Army", "00123456DD")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "6000",
        "6000"
      )

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify AddAnotherSchemePage page, select YES and continue")
      AddAnotherSchemePage.verifyPageSelectYesAndContinue("2017", "3")

      When("I verify WhichSchemeDetailsPage and select a New scheme")
      WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue()

      When("I verify PensionSchemeDetailsPage, enter Fire pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("fire", "00123456EE")

      When("I verify PensionSchemeInputAmountsPage, enter pension input amount and revised pension input amount")
      PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
        "6000",
        "6000"
      )

      When("I verify DidYouPayAChargePage, select NO and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify ContributedOtherDbDcSchemePage page, select yes and continue")
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()

      When("I verify WhichContributedDuringRemedyPeriodPage, select DB and continue")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue("2017")

      When("I verify PiaForDbPensionPage, input amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify ThresholdIncomePage(2017-2020) page, select yes and continue")
      ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()

      When("I verify AdjustedIncomePage page, enter income and continue")
      AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue("2016", "2017", "2017", "120000")

      When("I verify TotalIncomePage page, enter total income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("140000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 6 */
      /** User has no protections, no previous charge, user paying new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargePage.selectIncreasedChargeRadioButtonAndContinue()

      When("I select no radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

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

    Scenario("Setup Journey 2, and LTA journey 2", ZapTests) {

      /** User has protections and both enhancements (international enhancement and pension credit), all have changed. Previous LTA charge, paid with both lump sum and annual payment, scheme paid, new excess to be taken as both, pension scheme to pay increase */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 2 */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargePage.selectIncreasedChargeRadioButtonAndContinue()

      When("I select yes radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectYesRadioButtonAndContinue()

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

    Scenario("Setup Journey 2, and LTA journey 3", ZapTests) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 3 */
      /** User has protections only, has changed, user paid initial charge, new charge annual payment only, user paying new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargePage.selectIncreasedChargeRadioButtonAndContinue()

      When("I select no radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

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

    Scenario("Setup Journey 2, and LTA journey 4", ZapTests) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 4 */
      /** User has enhancements, has changed, previous charge, annual payment, positive value, user will pay new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargePage.selectIncreasedChargeRadioButtonAndContinue()

      When("I select yes radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectYesRadioButtonAndContinue()

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

    Scenario("Setup Journey 2, and LTA journey 5", ZapTests) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 5 */
      /** User has no protections or enhancements, neither has changed, no previous charge, annual payment = 0, user hits kickout */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargePage.selectIncreasedChargeRadioButtonAndContinue()

      When("I select no radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

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

    Scenario("Setup Journey 2, and LTA journey 6", ZapTests) {

      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (LTA) */
      Given("I am on the Public Service Pensions Remediation home page")
      HomePage.goToHomepage()

      When("I select I received remedial service statement and continue to next page")
      SavingsStatementPage.selectYesAndContinueForGSPage()

      When("I select I'm not resubmitting the adjustment and click continue")
      ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()

      When("I click Annual allowance and click continue")
      ReportingChangePage.selectLifetimeAllowanceAndContinue()

      When("I verify check your answers page and click continue")
      CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()

      When("I verify task list page and click add details for lifetime allowance")
      TaskListPage.verifyPageAndClickAddDetailsForLifetimeAllowance()

      /** LTA Journey 6 */
      /** User has no protections, no previous charge, user paying new charge */
      LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()

      When("I select Yes and continue to next page")
      HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()

      When("I enter date and click continue")
      DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()

      Then("I Should see the told-change-in-lta-percentage page and then select yes and continue")
      ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()

      Then("I Should see the percentage-caused-change-in-charge page")
      PercentageCausedChangeInChargePage.selectIncreasedChargeRadioButtonAndContinue()

      When("I select no radio button and click continue")
      MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()

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
