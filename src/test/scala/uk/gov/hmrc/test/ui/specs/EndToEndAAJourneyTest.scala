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

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.functions.CommonCalculation
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

import scala.collection.mutable

class EndToEndAAJourneyTest extends BaseSpec with BeforeAndAfter {
  var taxSchemes: mutable.Map[String, String] = mutable.Map.empty[String, String]
  var inDateYears: mutable.ArrayBuffer[Int]   = mutable.ArrayBuffer.empty[Int]
  var debitYears: mutable.ArrayBuffer[Int]    = mutable.ArrayBuffer.empty[Int]

  before {
    taxSchemes.clear()
    inDateYears.clear()
    debitYears.clear()

    val commonCalculationJourney                 = new CommonCalculation()
    val (taxSchemes1, inDateYears1, debitYears1) =
      commonCalculationJourney.createCalculationJourney("Scenario_MultipleSchemeDebitAndCredit")
    taxSchemes ++= taxSchemes1
    inDateYears ++= inDateYears1
    debitYears ++= debitYears1
  }

  Feature("Business scenario AA journeys") {

    /** 5.2, 5.3, 5.4, 5.5, 5.6, 5.7, 5.8(Y), 5.15, 5.16, 5.17, 5.18(N), 5.20(Multiple Scheme), 5.21, 5.22, 5.23, 5.24, 5.25, 5.26 */
    Scenario(s"Calculate Business Journey1", ZapTests) {

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

      CheckYourAnswersCalculationJourneyPage.clickSubmissionContinueButton()

      Then("I verify SubmissionPage Page")

      signOutPage()
    }
  }
}
