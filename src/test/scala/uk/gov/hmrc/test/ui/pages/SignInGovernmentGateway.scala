/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object SignInGovernmentGateway extends BasePage {
  def ContinueWithoutSignIn(): Unit =
    driver.findElement(By.xpath("//a[contains(text(), 'Continue without signing in')]")).click()

  def clickSignIn(): Unit =
    driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click()

}
