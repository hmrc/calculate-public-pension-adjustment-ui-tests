/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PiaForDbPensionPage extends BasePage {

  def enterPensionInputAmountForDB(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterPensionInputAmountForDBAndContinue(
    adjustedIncome: String
  ) = {
    enterPensionInputAmountForDB(adjustedIncome)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
