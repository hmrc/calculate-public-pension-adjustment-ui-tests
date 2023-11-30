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

package uk.gov.hmrc.test.ui.functions

import org.scalatest.BeforeAndAfter
import uk.gov.hmrc.test.ui.specs.BaseSpec

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

abstract class LTACalculation extends BaseSpec with BeforeAndAfter {

  def createLTACalculationJourney(fileName: String): (mutable.Map[String, String], ArrayBuffer[Int], ArrayBuffer[Int])

}
