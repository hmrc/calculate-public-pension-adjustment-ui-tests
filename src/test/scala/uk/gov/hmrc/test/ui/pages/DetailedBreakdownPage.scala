/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object DetailedBreakdownPage extends BasePage {

  def getDetailedBreakdownPageVerificationText(): String =
    driver
      .findElement(By.xpath("//h2[contains(text(),'A detailed look at the results')]"))
      .getText

}
