/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object WhenWillYouAskPensionSchemeToPay extends BasePage {

  def selectQuarter() = driver.findElement(By.id("value_0")).click()

  def verifyPageSelectQuarterAndContinue(year: Int) = {
    selectQuarter()
    submitPage()
  }

}
