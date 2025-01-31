/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object PensionSchemeInputAmountsPage extends BasePage {

  def enterPensionInputAmount(pensionInputAmount: String) = {
    driver.findElement(By.id("originalPIA")).clear()
    driver.findElement(By.id("originalPIA")).sendKeys(pensionInputAmount)
  }

  def enterRevisedPensionInputAmount(revisedPensionInputAmount: String) = {
    driver.findElement(By.id("revisedPIA")).clear()
    driver.findElement(By.id("revisedPIA")).sendKeys(revisedPensionInputAmount)
  }

  def verifyPageEnterPensionAmountsAndContinue(revisedPensionInputAmount: String) = {
    enterRevisedPensionInputAmount(revisedPensionInputAmount)
    checkYourAnswersAAPeriodMap(getHeader(), "£" + revisedPensionInputAmount)
    submitPage()
  }

  def getRevisedPIASpiltYearCaptionText() =
    driver.findElement(By.className("govuk-caption-xl")).getText

}
