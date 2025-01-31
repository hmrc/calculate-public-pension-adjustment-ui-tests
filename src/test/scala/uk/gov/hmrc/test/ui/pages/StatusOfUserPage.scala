/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object StatusOfUserPage extends BasePage {

  def selectDeputyship() = driver.findElement(By.id("value_1")).click()
  def verifyPageSelectDeputyshipAndContinue() = {
    selectDeputyship()
    checkYourAnswersCalculationsMap(getHeader(), getCheckedOptions())
    submitPage()
  }
}
