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

package util

import scala.collection.mutable

object DataCollectorMap {
  val checkAnswersGS: mutable.Map[String, Any]  = mutable.Map[String, Any]()
  val checkAnswersAAS: mutable.Map[String, Any] = mutable.Map[String, Any]()
  var checkAnswersLAS: List[(String, Any)]      = List[(String, Any)]()
  var checkAnswersAAPeriod: List[(String, Any)] = List[(String, Any)]()
  def addToGSMap(key: String, value: Any): Unit =
    checkAnswersGS += (key -> value)

  def addToAASMap(key: String, value: Any): Unit =
    checkAnswersAAS += (key -> value)

  def addToLASMap(key: String, value: Any): Unit =
    checkAnswersLAS = checkAnswersLAS :+ (key, value)

  def addToAAPeriodMap(key: String, value: Any): Unit =
    checkAnswersAAPeriod = checkAnswersAAPeriod :+ (key, value)
}
