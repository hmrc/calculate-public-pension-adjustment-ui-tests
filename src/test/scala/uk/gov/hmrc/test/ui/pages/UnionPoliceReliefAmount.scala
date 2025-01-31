/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object UnionPoliceReliefAmount extends BasePage {

  def enterUnionPoliceReliefAmount(unionPoliceReliefAmount: String) = {
    driver.findElement(By.id("value")).sendKeys(unionPoliceReliefAmount)
    submitPage()
  }
}
