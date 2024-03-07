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
import scala.collection.mutable

object CalculationResultPage extends BasePage {

  def returnTaxYearInformation(): Map[String, List[TableData]] = {
    val dlElement = driver.findElement(By.xpath("//div[@class='govuk-grid-column-two-thirds']"))
    // Get the HTML content of the <dl> element
    val html      = dlElement.getAttribute("innerHTML")

    val doc = Jsoup.parse(html)

    // Find all tables in the HTML
    val tables = doc.select("table")

    // Create an empty mutable map to store the table data
    val tableDataMap = mutable.Map[String, List[TableData]]()

    // Process each table
    tables.forEach { table =>
      // Find the table header (the h2 tag) to use as the key for the map
      val tableName = table.previousElementSibling().text()

      // Find all rows in the table body
      val rows = table.select("tbody tr")

      // Process each row and extract the data
      val tableDataList = rows.map { row =>
        val yourResult = row.select("th").text()
        val amount     = row.select("td").text().toInt
        TableData(yourResult, amount)
      }.toList
      tableDataMap(tableName) = tableDataList
    }
    tableDataMap.toMap
  }

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

  def getTaskListtaxYearInformation(taxYear: String, fieldName: String) = taxYear match {
    case "2016" =>
      val period = "6 April 2015 and 5 April 2016"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    /* case "2016-post" =>
      val period = "9 July 2015 to 5 April 2016"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }*/
    case "2017" =>
      val period = "6 April 2016 to 5 April 2017"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    case "2018" =>
      val period = "6 April 2017 to 5 April 2018"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    case "2019" =>
      val period = "6 April 2018 to 5 April 2019"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    case "2020" =>
      val period = "6 April 2019 to 5 April 2020"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    case "2021" =>
      val period = "6 April 2020 to 5 April 2021"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    case "2022" =>
      val period = "6 April 2021 to 5 April 2022"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
    case "2023" =>
      val period = "6 April 2022 to 5 April 2023"
      returnTaxYearInformation().get(period).flatMap { tableDataList =>
        tableDataList.find(_.yourResult == fieldName).map(_.amount)
      }
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
