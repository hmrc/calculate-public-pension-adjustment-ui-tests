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

import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class CalculationUserJourneysFlexIDatesTests extends BaseSpec {

  Feature("Calculate...") {

    Scenario("Journey for flexi date 1", ZapTests) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 6/4/2015, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
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

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 6/4/15 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("06", "4", "2015")

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
      /** Below journey covers 3.1, 3.2, 3.3, 3.4, 3.4.1, 3.5(N), 3.6, 3.7, 3.8, 3.9, 3.10(y), 3.11(both), 3.12, 3.12.1(both), 3.13, 3.13.1, 3.16, 3.17 ... */

      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "2000",
        "2000"
      )

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
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
    }

    Scenario("Journey for flexi date 2", ZapTests) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 7/7/2015, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
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

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 7/7/15 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("07", "7", "2015")

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
      /** Below journey covers 3.1, 3.2, 3.3, 3.4, 3.4.1, 3.5(N), 3.6, 3.7, 3.8, 3.9, 3.10(y), 3.11(both), 3.12, 3.12.1(both), 3.13, 3.13.1, 3.16, 3.17 ... */

      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "2000",
        "2000"
      )

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
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
    }

    Scenario("Journey for flexi date 3", ZapTests) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 8/7/2015, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
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

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 8/7/15 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("08", "7", "2015")

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
      /** Below journey covers 3.1, 3.2, 3.3, 3.4, 3.4.1, 3.5(N), 3.6, 3.7, 3.8, 3.9, 3.10(y), 3.11(both), 3.12, 3.12.1(both), 3.13, 3.13.1, 3.16, 3.17 ... */

      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "2000",
        "2000"
      )

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

      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )
      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
    }

    Scenario("Journey for flexi date 4", ZapTests) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 9/7/2015, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
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

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 9/7/15 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("09", "7", "2015")

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
      /** Below journey covers 3.1, 3.2, 3.3, 3.4, 3.4.1, 3.5(N), 3.6, 3.7, 3.8, 3.9, 3.10(y), 3.11(both), 3.12, 3.12.1(both), 3.13, 3.13.1, 3.16, 3.17 ... */

      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "2000",
        "2000"
      )

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
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
    }

    Scenario("Journey for flexi date 5", ZapTests) {

      /** User resubmitting, AA and LTA adjustment, scottish taxpayer, stopped paying 1/1/2017, has DC scheme flexibly accessed 5/4/2016, did not pay charge in 14/15, member in each pre-remedy year with PIA in all of those years. */
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

      Then("I land on the when-flexibly-access-pension page, enter flexi access date 5/4/16 and continue")
      WhenFlexiblyAccessPensionPage.enterFlexiAcessDateAndClickContinue("05", "4", "2016")

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
      /** Below journey covers 3.1, 3.2, 3.3, 3.4, 3.4.1, 3.5(N), 3.6, 3.7, 3.8, 3.9, 3.10(y), 3.11(both), 3.12, 3.12.1(both), 3.13, 3.13.1, 3.16, 3.17 ... */

      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "00123456AA")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-post pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "2000",
        "2000"
      )

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

      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )
      When("I verify PiaForDBPensionPage, enter pension amount for DB and continue")
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "6000"
      )

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
    }

    Scenario("Setup Journey 6, AA Journey 6", ZapTests) {

      /** User not resubmitting, AA adjustment, non-Scottish taxpayer, left scheme 8/7/2015, no other schemes, charge paid 14/15. */
      /** Below journey covers 0, 1.1 (Y), 1.3 (N), 1.5 (AA), 2.1 (N), 2.3 (N), 2.4 (8/7/2015), 2.5 (N), 2.8 (Y) */
      /** Coverage on MCSC-748 */
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
        "I land on stopped-paying-into-public-service-pension page, enter a valid date of 8/7/2015, and click continue"
      )
      WhenStopPayingPublicPensionPage.enterValidPre2015DateAndClickContinue()

      When("I land on defined-contribution-pension page, select no and continue to next page")
      HaveDefinedContributionPensionPage.selectNoAndContinueForAASPage()

      When("I land on pay-tax-charge-from2015-2016 page, select yes and continue to next page")
      PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()

      When("I verify check your answers page for annual allowance and click continue")
      CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()

      Then("I verify annual allowance period shows up to 2016 in the task list")
      TaskListPage.isAnnualAllowancePeriodShowsUpToYear("2016")

      /** AA Journey 1 - Setup journey 1 - No DC AA Journey */
      /** Below journey covers 3.1, 3.2 (N), 3.3, 3.4, 3.5 (Y), 3.6 (User), 3.7, 3.16 (60000), 3.17, task list */

      /** --- 2016 --- */
      When("I click Add details for 2015 to 2016")
      TaskListPage.clickAddDetailsFor2015To2016()

      When("I verify what-you-will-need-aa/2016 page and click continue")
      WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016Page()

      Then("I verify member-more-than-one-pension page,select No and continue")
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()

      When("I verify PensionSchemeDetailsPage, enter pension scheme and tax reference")
      PensionSchemeDetailsPage.enterTaxInformationAndContinue("NHS", "11123456BC")

      When(
        "I verify PensionSchemeInputAmountsPage, enter period of 2016-pre pension input amount and revised pension input amount"
      )
      PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
        "41250",
        "40000"
      )

      When("I verify DidYouPayAChargePage, select no and continue")
      DidYouPayAChargePage.verifyPageSelectNoAndContinue()

      When("I verify TotalIncomePage, enter net income and continue")
      TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("60000")

      /** verify check your answers page */
      CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
    }

  }

}
