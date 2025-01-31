/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

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
    submitPage()
  }

}
