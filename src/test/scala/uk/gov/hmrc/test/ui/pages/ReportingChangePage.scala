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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.scalatest.Assertion
import uk.gov.hmrc.test.ui.constants.Errors
import uk.gov.hmrc.test.ui.constants.PageInformation.{REPORTING_CHANGE_PAGE_HEADER, REPORTING_CHANGE_PAGE_LABEL, REPORTING_CHANGE_PAGE_TITLE}

import scala.collection.mutable.ListBuffer

object ReportingChangePage extends BasePage {


  def clearAllOptions() =
    if (driver.findElement(By.id("value_0")).isSelected) {
      driver.findElement(By.id("value_0")).click()
    } else if (driver.findElement(By.id("value_1")).isSelected) {
      driver.findElement(By.id("value_1")).click()
    }

  def clickAnnualAllowance() =
    driver.findElement(By.id("value_0")).click()

  def clickLifetimeAllowance() =
    driver.findElement(By.id("value_1")).click()

  def clickOtherCompensation() =
    driver.findElement(By.id("value_2")).click()

  def selectAnnualAllowanceAndContinue() = {
    clearAllOptions()
    clickAnnualAllowance()
    continueToNextPage()
  }

  def selectLifetimeAllowanceAndContinue() = {
    clearAllOptions()
    clickLifetimeAllowance()
    continueToNextPage()
  }

  def selectBothAAAndLTAContinue() = {
    clearAllOptions()
    clickLifetimeAllowance()
    clickAnnualAllowance()
    continueToNextPage()
  }

  def selectOtherCompensationAndContinue() = {
    clearAllOptions()
    clickOtherCompensation()
    continueToNextPage()
  }

  def continueToNextPage() = {
    val selectedOptions: ListBuffer[String] = ListBuffer()
    if (driver.findElement(By.id("value_0")).isSelected) {
      selectedOptions += "Annual allowance"
    }
    if (driver.findElement(By.id("value_1")).isSelected) {
      selectedOptions += "Lifetime allowance"
    }

    val resultString: String = selectedOptions.mkString(", ")

    checkYourAnswersGSMap(REPORTING_CHANGE_PAGE_LABEL, resultString)
    submitPage()
  }

}
