/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TheirNamePage extends BasePage {

  val text = "ABC BCDEFGHIJK"

  def enterPensionSchemeMemberName() = driver.findElement(By.id("value")).sendKeys(text)
  def verifyPageEnterPensionSchemeNameAndContinue() = {
    enterPensionSchemeMemberName()
    checkYourAnswersCalculationsMap(getHeader(), text)
    submitPage()
  }
}
