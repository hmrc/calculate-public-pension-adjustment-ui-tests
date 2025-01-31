/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object DeclarationsPage extends BasePage {

  def clickConfirmButton() = driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click()

  /** remove navigation to url */
  def verifyPageAndConfirm() =
    clickConfirmButton()

}
