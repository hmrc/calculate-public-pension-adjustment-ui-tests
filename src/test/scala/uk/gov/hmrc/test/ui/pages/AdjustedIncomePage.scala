/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object AdjustedIncomePage extends BasePage {
  val ADJUSTED_INCOME_PAGE_TITLE  =
    "What was your adjusted income from 6 April fromYear to 5 April toYear? - Calculate your public service pension adjustment - GOV.UK"
  val ADJUSTED_INCOME_TITLE       =
    "What was your adjusted income from 6 April fromYear to 5 April toYear?"
  val ADJUSTED_INCOME_PAGE_HEADER = "Adjusted income"

  def enterAdjustedIncome(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterAdjustedIncomeAndContinue(
    adjustedIncome: String
  ) = {
    enterAdjustedIncome(adjustedIncome)
    submitPage()
  }
}
