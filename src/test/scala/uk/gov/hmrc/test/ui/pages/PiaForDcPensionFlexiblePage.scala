/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PiaForDcPensionFlexiblePage extends BasePage {
  val PIA_FOR_DC_PENSION_PAGE_TITLE  =
    "What was your pension input amount for defined contribution pension schemes from fromDayMonthYear to toDayMonthYear? - Calculate your public service pension adjustment - GOV.UK"
  val PIA_FOR_DC_PENSION_PAGE_HEADER =
    "What was your pension input amount for defined contribution pension schemes from fromDayMonthYear to toDayMonthYear?"

  def enterPensionInputAmountForDC(adjustedIncome: String) = driver.findElement(By.id("value")).sendKeys(adjustedIncome)

  def verifyPageEnterPensionInputAmountForDCAndContinue(
    adjustedIncome: String
  ) = {
    enterPensionInputAmountForDC(adjustedIncome)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + driver.findElement(By.id("value")).getAttribute("value"))
    submitPage()
  }
}
