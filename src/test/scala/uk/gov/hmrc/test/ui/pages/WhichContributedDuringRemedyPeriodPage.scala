/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhichContributedDuringRemedyPeriodPage extends BasePage {

  def selectDefinedContribution() = driver.findElement(By.id("value_1")).click()
  def selectDefinedBenefit()      = driver.findElement(By.id("value_0")).click()

  def defSelectBothDCAndDB() = {
    selectDefinedContribution()
    selectDefinedBenefit()
    checkYourAnswersAAPeriodMap(
      getHeader(),
      "Defined contribution," + " Defined benefit"
    )
  }

  def verifyPageSelectDCAndContinue(): Unit = {
    selectDefinedContribution()
    checkYourAnswersAAPeriodMap(getHeader(), "Defined contribution")
    submitPage()
  }

  def verifyPageSelectDBAndContinue(): Unit = {
    selectDefinedBenefit()
    checkYourAnswersAAPeriodMap(getHeader(), "Defined benefit")
    submitPage()
  }

  def verifyPageSelectDBAndDCANDContinue(): Unit = {
    defSelectBothDCAndDB()
    submitPage()
  }
}
