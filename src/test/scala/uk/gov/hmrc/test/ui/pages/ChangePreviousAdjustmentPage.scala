/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ChangePreviousAdjustmentPage extends BasePage {

  def selectYesAndSaveAndContinue() = {
    driver.findElement(By.cssSelector("#value")).click()
    driver.findElement(By.cssSelector(".govuk-button")).click()
  }

}
