/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TotalIncomePage extends BasePage {

  def enterTotalIncome(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterTotalIncomeAndContinue(
    adjustedIncome: String
  ) = {
    enterTotalIncome(adjustedIncome)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
