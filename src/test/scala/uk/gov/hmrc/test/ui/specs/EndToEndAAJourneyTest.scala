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

import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class EndToEndAAJourneyTest extends BaseSpec {

  Feature("Business scenario AA journeys") {

    /** 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8(Y), 5.15, 5.16, 5.17, 5.18(N), 5.20(Multiple Scheme), 5.21, 5.22, 5.23, 5.24, 5.25, 5.26 */
    Scenario(s"Calculate Business Journey1", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_MultipleSchemeCredit")

      /** Backward scripting */
      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectYesAndContinue()

      When("I verify StatusOfUserPage select deputyship and continue")
      StatusOfUserPage.verifyPageSelectDeputyshipAndContinue()

      When("I verify TheirNamePage enter pension scheme name and continue")
      TheirNamePage.verifyPageEnterPensionSchemeNameAndContinue()

      When("I verify TheirDOBPage enter date of birth and continue")
      TheirDOBPage.verifyPageEnterBirthdayAndContinue()

      When("I verify Their date of death Page enter date of death and continue")
      TheirDateOfDeathPage.verifyPageEnterDateOfDeathAndContinue()

      When("I verify TheirNinoPage enter NINO and continue")
      TheirNinoPage.verifyPageEnterNinoAndContinue()

      When("I verify TheirUTRPage enter tax reference and continue")
      TheirUTRPage.verifyPageEnterUTRAndContinue()

      When("I verify TheirResidencePage, select yes and continue")
      TheirResidencePage.verifyPageSelectYesAnContinue()

      When("I verify TheirUKAddressPage, Enter Address information and continue")
      TheirUKAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify WhichPensionSchemeWillPayTaxReliefPage Page, select pension scheme and click continue")
      WhichPensionSchemeWillPayTaxReliefPage.verifyPageSelectPensionSchemeAndContinue("Scheme 2")

      When("I verify Bank Details Page, enter bank details and click continue")
      BankDetailsPage.verifyPageEnterBankDetailsClickContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /* Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }

    /** 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8(N), 5.15, 5.16, 5.17, 5.18(N), 5.20(Single Scheme), 5.21,5.22, 5.23, 5.25, 5.26 */
    Scenario(s"Calculate Business Journey2", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeCredit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectYesAndContinue()

      When("I verify StatusOfUserPage select deputyship and continue")
      StatusOfUserPage.verifyPageSelectDeputyshipAndContinue()

      When("I verify TheirNamePage enter pension scheme name and continue")
      TheirNamePage.verifyPageEnterPensionSchemeNameAndContinue()

      When("I verify TheirDOBPage enter date of birth and continue")
      TheirDOBPage.verifyPageEnterBirthdayAndContinue()

      When("I verify Their date of death Page enter date of death and continue")
      TheirDateOfDeathPage.verifyPageEnterDateOfDeathAndContinue()

      When("I verify TheirNinoPage enter NINO and continue")
      TheirNinoPage.verifyPageEnterNinoAndContinue()

      When("I verify TheirUTRPage and continue without tax reference")
      TheirUTRPage.verifyPageContinueWithoutTaxReference()

      When("I verify TheirResidencePage, select no and continue")
      TheirResidencePage.verifyPageSelectNoAnContinue()

      When("I verify TheirInternationalAddressPage, Enter Address information and continue")
      TheirInternationalAddressPage.verifyPageEnterAddressAndContinue()

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify Bank Details Page, enter bank details and click continue")
      BankDetailsPage.verifyPageEnterBankDetailsClickContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /*Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()

      signOutPage()
    }

    /** 5.2(N credit), 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.25, 5.26 */
    Scenario(s"Calculate Business Journey4", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeCredit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify Bank Details Page, enter bank details and click continue")
      BankDetailsPage.verifyPageEnterBankDetailsClickContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /* Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()
      signOutPage()
    }

    /** 5.2(N debit), 5.10(You paid), 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.23, 5.24, 5.26 */
    Scenario(s"Calculate Business Journey5", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_MultipleSchemeDebit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select you and click continue button")
        WhoWillPayPage.verifyPageSelectYouAndContinue(element)
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify WhichPensionSchemeWillPayTaxReliefPage Page, select pension scheme and click continue")
      WhichPensionSchemeWillPayTaxReliefPage.verifyPageSelectPensionSchemeAndContinue("Scheme 1")

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /*Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()
      signOutPage()
    }

    /** 5.2(N debit), 5.10(Scheme paid), 5.11(public), 5.12(N), 5.13, 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.23, 5.26 */
    Scenario(s"Calculate Business Journey6", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeDebit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue(element)

        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
        WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("Scheme 1", element)

        When("I verify Valid Election for Scheme to pay Page and select yes and click continue")
        AskedPensionSchemeToPayTaxCharge.verifyPageSelectNoAndContinue(element)

        When("I verify Estimated quarter of Election Page and select quarter and click continue")
        WhenWillYouAskPensionSchemeToPay.verifyPageSelectQuarterAndContinue(element)
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickYesAndContinue()

      When("I verify TaxReliefAmountPage Page, enter tax relief and click continue")
      TaxReliefAmountPage.verifyPageEnterTaxReliefAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /*Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()
      signOutPage()
    }

    /** 5.2(N debit), 5.10(Scheme paid), 5.11(public), 5.12(Y), 5.14, 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.26 */
    Scenario(s"Calculate Business Journey7", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeDebit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue(element)

        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
        WhichPensionSchemeWillPayPage.verifyPageSelectPSAndContinue("Scheme 1", element)

        When("I verify Valid Election for Scheme to pay Page and select yes and click continue")
        AskedPensionSchemeToPayTaxCharge.verifyPageSelectNoAndContinue(element)

        When("I verify Estimated quarter of Election Page and select quarter and click continue")
        WhenWillYouAskPensionSchemeToPay.verifyPageSelectQuarterAndContinue(element)
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /*Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()
      signOutPage()
    }

    /** 5.2(N debit), 5.10(Scheme paid), 5.11(private),5.12(Y), 5.14, 5.15(Y), 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.26 */
    Scenario(s"Calculate Business Journey8", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeDebit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue(element)

        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
        WhichPensionSchemeWillPayPage.verifyPageSelectPrivatePSAndContinue(element)

        When("I verify PensionSchemeDetailsPage, enter pension scheme information and click continue button")
        SubmitPensionSchemeDetailsPage.verifyPageEnterPensionSchemeInformationAndContinue(element)

        Then("I verify Valid Election for Scheme to pay Page and select yes and click continue")
        AskedPensionSchemeToPayTaxCharge.verifyPageSelectYesAndContinue(element)

        Then("I verify Date of Election Page and enter date and click continue")
        WhenDidYouAskPensionSchemeToPay.verifyPageEnterDateAndContinue(element)
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectNoAndContinue()

      When("I verify InternationalAddressPage enter address and Continue")
      InternationalAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /*Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()
      signOutPage()
    }

    /** 5.2(N debit), 5.10(Scheme paid), 5.11(private),5.12(Y), 5.14, 5.15(Y), 5.16, 5.17, 5.18(Y), 5.20, 5.21, 5.22, 5.26 */
    Scenario(s"Calculate Business Journey9", ZapTests) {
      val commonCalculationJourney              = new CommonCalculationJourney()
      val (taxSchemes, inDateYears, debitYears) =
        commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeDebit")

      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

      debitYears.foreach { element =>
        When("I verify WhoWillPayPage, select pension scheme and click continue button")
        WhoWillPayPage.verifyPageSelectPensionSchemeAndContinue(element)

        When("I verify WhichPensionSchemeWillPayPage, select public pension scheme and click continue button")
        WhichPensionSchemeWillPayPage.verifyPageSelectPrivatePSAndContinue(element)

        When("I verify PensionSchemeDetailsPage, enter pension scheme information and click continue button")
        SubmitPensionSchemeDetailsPage.verifyPageEnterPensionSchemeInformationAndContinue(element)

        Then("I verify Valid Election for Scheme to pay Page and select yes and click continue")
        AskedPensionSchemeToPayTaxCharge.verifyPageSelectYesAndContinue(element)

        Then("I verify Date of Election Page and enter date and click continue")
        WhenDidYouAskPensionSchemeToPay.verifyPageEnterDateAndContinue(element)
      }

      When("I verify AlternativeNamePage, select No and continue")
      AlternativeNamePage.verifyPageSelectNoAndContinue()

      When("I verify AlternativeNamePage, enter name and continue")
      EnterAlternativeNamePage.verifyPageEnterNameAndContinue()

      When("I verify ContactNumberPage, enter phone number and continue")
      ContactNumberPage.verifyPageEnterContactNumberAndContinue()

      When("I verify ResidencePage select Yes and Continue")
      ResidencePage.verifyPageSelectYesAndContinue()

      When("I verify UKAddressPage enter address and Continue")
      UKAddressPage.verifyPageEnterAddressAndContinue()

      taxSchemes.foreach { case (key, value) =>
        When("I verify LegacyPensionSchemeReferencePage enter reference and Continue")
        LegacyPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(key, value)

        When("I verify ReformPensionSchemeReferencePage enter reference and Continue")
        ReformPensionSchemeReferencePage.verifyPageEnterReferenceAndContinue(value)
      }

      When("I verify ClaimingAdditionalTaxRateRelief Page , select yes and click continue")
      ClaimingAdditionalTaxRateReliefPage.verifyPageClickNoAndContinue()

      When("I verify DeclarationsPage Page and click confirm")
      DeclarationsPage.verifyPageAndConfirm()

      /*Then("I verify check your answers page and click continue")
      CheckYourAnswersCalculationJourneyPage.verifyCheckYourAnswersPageAndContinue()*/
      CheckYourAnswersCalculationJourneyPage.clickContinueButton()

      Then("I verify SubmissionPage Page")
      SubmissionPage.verifySubmissionPage()
      signOutPage()
    }
  }
}
