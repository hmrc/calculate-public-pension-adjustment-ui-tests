/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object EnterAlternativeNamePage extends BasePage {

  val name = "ABC BCDEFGH"

  def verifyPageEnterNameAndContinue() = {
    driver.findElement(By.id("value")).sendKeys(name)
    checkYourAnswersCalculationsMap(getHeader(), name)
    submitPage()
  }
}
