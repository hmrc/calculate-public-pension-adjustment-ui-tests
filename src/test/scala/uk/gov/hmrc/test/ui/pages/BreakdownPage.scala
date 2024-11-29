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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

object BreakdownPage extends BasePage {

  def goToYearBreakdown(year: Int): Boolean =
    try {

      val dateString = s"5 April $year"

      val breakdownLink =
        driver.findElement(By.xpath(s"//*[contains(text(),'$dateString')]/../td/a[contains(text(),'View breakdown')]"))
      breakdownLink.click()
      fluentWait.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main-content\"]/div/div/h2[2]"))
      ) // maybe update this xpath?
      true
    } catch {
      case _: NoSuchElementException =>
        println(s"Breakdown link for year $year not found")
        false
    }

  def returnToMainpage(): Unit =
    try {
      val backlink = driver.findElement(By.linkText("Back")).click() //
      fluentWait.until(
        ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Calculation results')]"))
      )
    } catch {
      case _: NoSuchElementException =>
        throw new RuntimeException("Cannot return to the main page.")
    }

}
