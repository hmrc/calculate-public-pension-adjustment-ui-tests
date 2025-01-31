/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import util.NINOGenerator

object TheirNinoPage extends BasePage {

  val nino = NINOGenerator.nino

  def enterNINO() = driver.findElement(By.id("value")).sendKeys(nino)
  def verifyPageEnterNinoAndContinue() = {
    enterNINO()
    checkYourAnswersCalculationsMap(getHeader(), nino)
    submitPage()
  }
}
