/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TheirInternationalAddressPage extends BasePage {

  def enterAddressInformation() = {
    driver.findElement(By.id("addressLine1")).sendKeys("No 138")
    driver.findElement(By.id("addressLine2")).sendKeys("Prince ref road")
    driver.findElement(By.id("townOrCity")).sendKeys("London")
    driver.findElement(By.id("county")).sendKeys("London")
    driver.findElement(By.id("postCode")).sendKeys("AB1 9ED")
    driver.findElement(By.id("country")).sendKeys("France")
  }

  def verifyPageEnterAddressAndContinue() = {
    enterAddressInformation()
    submitPage()
  }
}
