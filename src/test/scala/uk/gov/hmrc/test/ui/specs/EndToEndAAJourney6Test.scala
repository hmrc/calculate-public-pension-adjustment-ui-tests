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

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.functions.CommonCalculation
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

import scala.collection.mutable

class EndToEndAAJourney6Test extends BaseSpec with BeforeAndAfter {
  var taxSchemes: mutable.Map[String, String] = mutable.Map.empty[String, String]
  var inDateYears: mutable.ArrayBuffer[Int]   = mutable.ArrayBuffer.empty[Int]
  var debitYears: mutable.ArrayBuffer[Int]    = mutable.ArrayBuffer.empty[Int]

  before {
    taxSchemes.clear()
    inDateYears.clear()
    debitYears.clear()

    val commonCalculationJourney                 = new CommonCalculation()
    val (taxSchemes1, inDateYears1, debitYears1) =
      commonCalculationJourney.createCalculationJourney("Scenario_SingleSchemeDebit")
    taxSchemes ++= taxSchemes1
    inDateYears ++= inDateYears1
    debitYears ++= debitYears1
  }

  Feature("Business scenario AA journeys") {

    /** 5.2(N debit), 5.10(Scheme paid), 5.11(public), 5.12(Y), 5.14, 5.15, 5.16, 5.17, 5.18(N), 5.20, 5.21, 5.22, 5.26 */
    Scenario(s"Calculate Business Journey6", ZapTests) {

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
  }
}
