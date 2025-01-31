/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object InterestFromSavings extends BasePage {

  def enterInterestFromSavings(interestAmount: String) =
    driver.findElement(By.xpath("//input[@id='value']")).sendKeys(interestAmount)

  def enterInterestFromSavingsAndContinue(interestAmount: String) = {
    enterInterestFromSavings(interestAmount)
    submitPage()

  }

}
