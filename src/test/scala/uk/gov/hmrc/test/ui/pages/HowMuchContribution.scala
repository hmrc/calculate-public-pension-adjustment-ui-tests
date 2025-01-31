/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages
import org.openqa.selenium.By

object HowMuchContribution extends BasePage {

  def enterPreReliefPensionContribution(preReliefPensionContribution: String) =
    driver.findElement(By.xpath("//input[@id='value']")).sendKeys(preReliefPensionContribution)
  def enterPreReliefPensionContributionAndContinue(preReliefPensionContribution: String) = {
    enterPreReliefPensionContribution(preReliefPensionContribution)
    submitPage()
  }
}
