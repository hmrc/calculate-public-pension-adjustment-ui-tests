/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhichSchemeDetailsPage extends BasePage {

  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//label[contains(text(),'" + pensionScheme + "')]/preceding::input[1]")).click()
  def selectNewScheme()                          = driver.findElement(By.xpath("//label[contains(text(),'New')]/preceding::input[1]")).click()
  def verifyPageSelectSchemeAndContinue(
    pensionSchemeName: String,
    pensionScheme: String
  ) = {
    selectPensionScheme(pensionScheme)
    checkYourAnswersAAPeriodMap(
      "What is the name and tax reference of the pension scheme?",
      pensionSchemeName + " / " + pensionScheme
    )
    submitPage()
  }
  def verifyPageSelectNewSchemeAndContinue() = {
    selectNewScheme()
    checkYourAnswersAAPeriodMap("What is the name and tax reference of the pension scheme?", "New")
    submitPage()
  }

  def isSchemeAvailable(schemeName: String): Boolean =
    driver.findElements(By.xpath("//label[contains(text(),'" + schemeName + "')]")).size > 0

}
