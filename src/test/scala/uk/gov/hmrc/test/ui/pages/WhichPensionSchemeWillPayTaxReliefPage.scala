/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhichPensionSchemeWillPayTaxReliefPage extends BasePage {

  def selectPensionScheme(pensionScheme: String) =
    driver.findElement(By.xpath("//label[contains(text(),'" + pensionScheme + "')]/preceding::input[1]")).click()

  def verifyPageSelectPensionSchemeAndContinue(pensionScheme: String) = {
    val text = driver.findElement(By.xpath("//label[contains(text(),'" + pensionScheme + "')]")).getText()
    selectPensionScheme(pensionScheme)
    checkYourAnswersCalculationsMap(getHeader(), text)
    submitPage()
  }
}
