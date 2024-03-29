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

package uk.gov.hmrc.test.ui.pages

import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper

import scala.collection.mutable

object CheckYourAnswersAnnualAllowanceSetupPage extends BasePage {

  def mapKeysToLabels(checkAnswersAAS: mutable.Map[String, Any]) = {

    val keyToLabelMap = Map(
      "When did you stop paying into a public service pension scheme?"             -> "Date you stopped paying into a public service pension scheme",
      "Do you have any other pension schemes apart from your public service ones?" -> "Do you have a defined contribution pension scheme?",
      "Are you still paying into or increasing a public service pension scheme?"   -> "Are you still paying into a public service pension scheme?",
      "When did you first flexibly access your defined contribution pension?"      -> "Date you first flexibly accessed your defined contribution pension"
    )

    val mappedLabels = mutable.Map[String, Any]()

    checkAnswersAAS.keys.foreach { key =>
      val label: String         = keyToLabelMap.getOrElse(key, key)
      var labelWithReplacements = label.replaceAll("What was your pension input amount", "Pension input amount")
      if (labelWithReplacements.startsWith("Pension input amount"))
        labelWithReplacements = labelWithReplacements.replace("?", "")
      val value: Any            = checkAnswersAAS(key)
      mappedLabels += (labelWithReplacements -> value)
    }

    mappedLabels
  }

  def verifyCheckYourAnswersPageAndContinue() =
    clickContinueButton()

  def checkContains(description: String, map1: mutable.Map[String, Any], map2: mutable.Map[String, Any]) {
    map1.foreach { (kv: (String, Any)) =>
      val expected = map2(kv._1)
      val actual   = kv._2
      actual mustBe expected
    }
  }

  def clickChangeOnWhenDidYouStopPaying() = clickOnChangeLink(
    "At what date during the remedy period did you stop paying into a public service pension scheme?"
  )

}
