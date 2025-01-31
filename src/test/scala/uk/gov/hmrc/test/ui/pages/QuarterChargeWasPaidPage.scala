/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object QuarterChargeWasPaidPage extends BasePage {

  def selectQuarterAndContinue(): Unit = {
    val text = "1 January to 31 March"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }
}
