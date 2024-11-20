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

import org.jsoup.Jsoup
import org.openqa.selenium.By

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`
import scala.jdk.CollectionConverters.ListHasAsScala

object CalculationResultPage extends BasePage {
  def returnTaxYearInformation(taxYear: String): Map[String, String] = {
    val dlElement        = driver.findElement(
      By.xpath("//div[@class='govuk-grid-column-two-thirds']//h2[contains(text(),'5 April " + taxYear + "')]")
    )
    val nextTableElement = dlElement.findElement(By.xpath("following-sibling::table"))
    // Get the HTML content of the <dl> element
    val html             = nextTableElement.getAttribute("outerHTML")

    val doc = Jsoup.parse(html)

    val map = doc
      .select("tbody.govuk-table__body tr")
      .asScala
      .map { row =>
        val th = row.select("th").text()
        val td = row.select("td").text()
        th -> td
      }
      .toMap
    map
  }

  def getCalculationResultsHeading(): String =
    driver
      .findElement(By.xpath("//h1[contains(text(),'Calculation results')]"))
      .getText
      .toString

  def clickOnViewBreakdownFor20152016(): Unit =
    driver
      .findElement(By.xpath("//*[contains(text(),'5 April 2016')]/../td/a[contains(text(),'View breakdown')]"))
      .click()

  def clickOnReviewAnswersForLTA(): Unit =
    driver
      .findElement(By.xpath("//a[normalize-space()='review your lifetime allowance answers']"))
      .click()

  def getTotCompensation(): Int =
    driver
      .findElement(By.xpath("//th[contains(text(),'Total amount of compensation')]//following-sibling::td"))
      .getText
      .toInt

  def getIncreasedTaxCharges(): Int =
    driver
      .findElement(By.xpath("//th[contains(text(),'Total increase in tax charges')]//following-sibling::td"))
      .getText
      .toInt

  def getDecreasedTaxCharges(): Int =
    driver
      .findElement(By.xpath("//th[contains(text(),'Total decrease in tax charges')]//following-sibling::td"))
      .getText
      .toInt

  def returnTaskListSummaryInformation(fieldName: String) = {
    val dlElement =
      driver.findElement(By.xpath("//div[@class='govuk-grid-column-two-thirds']"))
    // Get the HTML content of the <dl> element
    val html      = dlElement.getAttribute("innerHTML")

    val doc = Jsoup.parse(html)

    val rows1 = doc.select(".govuk-table__body")
    val rows  = rows1.select(".govuk-table__row")

    val valueOption = rows
      .find { row =>
        val headerCell = row.select(".govuk-table__header")
        headerCell != null && headerCell.text().trim == fieldName
      }
      .flatMap { row =>
        val dataCell = row.select(".govuk-table__cell").first()
        Option(dataCell).map(_.text().trim)
      }
    valueOption
  }

  def getTaskListTaxYearInformation(taxYear: String, fieldName: String) = taxYear match {
    case "2016" =>
      val period = "6 April 2015 and 5 April 2016"
      getTaxYearInformation(period, fieldName)
    case "2017" =>
      val period = "6 April 2016 to 5 April 2017"
      getTaxYearInformation(period, fieldName)
    case "2018" =>
      val period = "6 April 2017 to 5 April 2018"
      getTaxYearInformation(period, fieldName)
    case "2019" =>
      val period = "6 April 2018 to 5 April 2019"
      getTaxYearInformation(period, fieldName)
    case "2020" =>
      val period = "6 April 2019 to 5 April 2020"
      getTaxYearInformation(period, fieldName)
    case "2021" =>
      val period = "6 April 2020 to 5 April 2021"
      getTaxYearInformation(period, fieldName)
    case "2022" =>
      val period = "6 April 2021 to 5 April 2022"
      getTaxYearInformation(period, fieldName)
    case "2023" =>
      val period = "6 April 2022 to 5 April 2023"
      getTaxYearInformation(period, fieldName)
  }

  def getTaxYearInformation(taxYear: String, fieldName: String) = {
    val returnResult = returnTaxYearInformation(taxYear).getOrElse(fieldName, "0")
    returnResult.toInt
  }
  def clickContinueSignIn() = {
    Thread.sleep(2000)
    driver.findElement(By.xpath("//button[contains(text(),'Continue to sign in')]")).click()
  }

  def getDebitYears() = driver.findElements(
    By.xpath(
      "//table//th[contains(text(),'Amount of increased tax charge that is due to HMRC')]/following-sibling::td[not(text()='0')]/preceding::h2[1]"
    )
  )
}

case class TableData(yourResult: String, amount: Int)
