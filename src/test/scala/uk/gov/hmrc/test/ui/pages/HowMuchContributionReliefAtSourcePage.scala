/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object HowMuchContributionReliefAtSourcePage extends BasePage {

  def enterContributionReliefAmountAndContinue(ContributionReliefAmount: String) = {
    driver.findElement(By.xpath("//input[@id='value']")).sendKeys(ContributionReliefAmount)
    submitPage()
  }

}
