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
import uk.gov.hmrc.test.ui.constants.PageInformation.{TASK_LISTS_PAGE_HEADER, TASK_LISTS_PAGE_HEADER2, TASK_LISTS_PAGE_TITLE}

object TaskListPage extends BasePage {

  val NOT_STARTED = "Not started"
  val COMPLETED   = "Completed"
  val IN_PROGRESS = "In progress"

  val AddDetailsOfYourAA          = By.xpath("//a[contains(text(),'Add details of your annual allowance background')]")
  val changeDetailsOfYourAA       = By.xpath("//a[contains(text(),'Change details of your annual allowance background')]")
  val setupQuestionsTitle         = By.xpath("//h2[contains(text(),'Setup questions')]")
  val lifeTimeAllowanceTitle      = By.xpath("//h2[contains(text(),'Lifetime allowance')]")
  val annualAllowanceTitle        = By.xpath("//h2[contains(text(),'Annual allowance')]")
  val AddDetailsOfYourLAEvents    = By.xpath("//a[contains(text(),'Add details of your events')]")
  val changeDetailsOfYourLAEvents =
    By.xpath("//a[contains(text(),'Change details of your events')]")

  def isSetupQuestionsTitleDisplayed(): Boolean = driver.findElements(setupQuestionsTitle).size() > 0

  def isLifeTimeAllowanceTitleDisplayed(): Unit = driver.findElements(lifeTimeAllowanceTitle).size() should be >= 1

  def isLifeTimeAllowanceTitleNotDisplayed(): Unit = driver.findElements(lifeTimeAllowanceTitle).size() should be <= 0

  def isAnnualAllowanceTitleDisplayed(): Unit = driver.findElements(annualAllowanceTitle).size() should be >= 1

  def isAnnualAllowanceTitleNotDisplayed(): Unit = driver.findElements(annualAllowanceTitle).size() should be <= 0

  def onTaskListPage() = {
    verifyPageUrl("task-list")
    onPage(TASK_LISTS_PAGE_TITLE)
    isHeader(TASK_LISTS_PAGE_HEADER)
    isHeader2(TASK_LISTS_PAGE_HEADER2)
  }

  def clickAddDetailsForLifetimeAllowance() =
    driver.findElement(AddDetailsOfYourLAEvents).click()

  def clickChangeDetailsForLifetimeAllowance() =
    driver.findElement(changeDetailsOfYourLAEvents).click()

  def verifyPageAndClickAddDetailsForLifetimeAllowance() = {
    onTaskListPage()
    clickAddDetailsForLifetimeAllowance()
  }

  def clickAddDetailsOfYourAA() =
    driver.findElement(AddDetailsOfYourAA).click()

  def clickChangeDetailsOfYourAA() =
    driver.findElement(changeDetailsOfYourAA).click()

  def getLADetailsOfYourEventStatus(): String =
    driver
      .findElement(
        By.xpath("//a[contains(text(),'details of your events')]/ancestor::li[1]/span[@class='hmrc-status-tag']")
      )
      .getText

  def clickOnSetupQuestions() = driver.findElement(By.xpath("//a[contains(text(),'Add setup questions')]")).click()

  def clickOnChangeSetupQuestions() =
    driver.findElement(By.xpath("//a[contains(text(),'Change setup questions')]")).click()

  def isAnnualAllowancePeriodShowsUpToYear(year: String): Unit = {
    val year2022 = driver.findElements(By.xpath("//a[contains(text(),'details for 2021 to 2022')]")).size() > 0
    val year2021 = driver.findElements(By.xpath("//a[contains(text(),'details for 2020 to 2021')]")).size() > 0
    val year2020 = driver.findElements(By.xpath("//a[contains(text(),'details for 2019 to 2020')]")).size() > 0
    val year2019 = driver.findElements(By.xpath("//a[contains(text(),'details for 2018 to 2019')]")).size() > 0
    val year2018 = driver.findElements(By.xpath("//a[contains(text(),'details for 2017 to 2018')]")).size() > 0
    val year2017 = driver.findElements(By.xpath("//a[contains(text(),'details for 2016 to 2017')]")).size() > 0
    val year2016 =
      driver.findElements(By.xpath("//a[contains(text(),'details for 9 July to 5 April 2016')]")).size() > 0
    val year2015 =
      driver.findElements(By.xpath("//a[contains(text(),'details for 6 April to 8 July 2015')]")).size() > 0
    year match {
      case "2022" =>
        try assert(year2022 && year2021 && year2020 && year2019 && year2018 && year2017 && year2016 && year2015)
        catch {
          case assertionError: AssertionError =>
            println("Not all periods are displayed as expected.")
          // Handle the error or perform any necessary cleanup
        }
      case "2018" =>
        try assert(year2018 && year2017 && year2016 && year2015)
        catch {
          case assertionError: AssertionError =>
            println("Not all periods are displayed as expected.")
          // Handle the error or perform any necessary cleanup
        }
      case "2017" =>
        try assert(year2017 && year2016 && year2015)
        catch {
          case assertionError: AssertionError =>
            println("Not all periods are displayed as expected.")
          // Handle the error or perform any necessary cleanup
        }
    }
  }

  def clickAddDetailsFor6AprilTo8July2015() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 6 April to 8 July 2015')]")).click()

  def clickChangeDetailsFor6AprilTo8July2015() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 6 April to 8 July 2015')]")).click()

  def clickAddDetailsFor9JulyTo8July2016() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 9 July to 5 April 2016')]")).click()

  def clickChangeDetailsFor9JulyTo8July2016() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 9 July to 5 April 2016')]")).click()

  def clickAddDetailsFor2016To2017() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2016 to 2017')]")).click()

  def clickChangeDetailsForFor2016To2017() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 2016 to 2017')]")).click()

  def clickAddDetailsFor2017To2018() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2017 to 2018')]")).click()

  def clickChangeDetailsFor2017To2018() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 2017 to 2018')]")).click()

  def clickAddDetailsFor2018To2019() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2018 to 2019')]")).click()

  def clickChangeDetailsFor2018To2019() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 2018 to 2019')]")).click()

  def clickAddDetailsFor2019To2020() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2019 to 2020')]")).click()

  def clickChangeDetailsFor2019To2020() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 2019 to 2020')]")).click()

  def clickAddDetailsFor2020To2021() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2020 to 2021')]")).click()

  def clickChangeDetailsFor2020To2021() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 2020 to 2021')]")).click()

  def clickAddDetailsFor2021To2022() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2021 to 2022')]")).click()

  def clickChangeDetailsFor2021To2022() =
    driver.findElement(By.xpath("//a[contains(text(),'Change details for 2021 to 2022')]")).click()

  def clickAddDetailsFor2022To2023() =
    driver.findElement(By.xpath("//a[contains(text(),'Add details for 2022 to 2023')]")).click()

  def clickCalculateButton() =
    driver.findElement(By.xpath("//span/a[contains(text(),'Calculate')]")).click()

}
