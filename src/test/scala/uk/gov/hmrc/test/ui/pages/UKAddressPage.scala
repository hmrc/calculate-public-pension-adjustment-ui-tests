/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object UKAddressPage extends BasePage {

  def enterAddressInformation() = {
    driver.findElement(By.id("addressLine1")).sendKeys("No 137")
    driver.findElement(By.id("addressLine2")).sendKeys("Prince bcd road")
    driver.findElement(By.id("townOrCity")).sendKeys("London")
    driver.findElement(By.id("county")).sendKeys("London")
    driver.findElement(By.id("postCode")).sendKeys("AB3 7ED")
  }

  def verifyPageEnterAddressAndContinue() = {
    enterAddressInformation()
    submitPage()
  }
}
