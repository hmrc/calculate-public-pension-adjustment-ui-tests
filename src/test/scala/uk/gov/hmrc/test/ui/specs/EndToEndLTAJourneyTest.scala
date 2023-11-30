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

/** import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage */
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

import scala.collection.mutable

class EndToEndLTAJourneyTest extends BaseSpec with BeforeAndAfter {
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

  Feature("Business scenario LTA journeys") {

    /** 5.2(N debit) */
    Scenario(s"Calculate Business Journey4", ZapTests) {
      When("I verify ClaimOnBehalfPage, select yes and click continue button")
      ClaimOnBehalfPage.verifyPageSelectNoAndContinue()

    }

  }
}
