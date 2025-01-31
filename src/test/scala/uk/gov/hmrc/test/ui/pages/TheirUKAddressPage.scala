/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object TheirUKAddressPage extends BasePage {

  val addressLine1 = "No 134"
  val addressLine2 = "Prince abc road"
  val townOrCity   = "London"
  val county       = "London"
  val postCode     = "AB2 5ED"

  def enterAddressInformation() = {
    driver.findElement(By.id("addressLine1")).sendKeys(addressLine1)
    driver.findElement(By.id("addressLine2")).sendKeys(addressLine2)
    driver.findElement(By.id("townOrCity")).sendKeys(townOrCity)
    driver.findElement(By.id("county")).sendKeys(county)
    driver.findElement(By.id("postCode")).sendKeys(postCode)
  }

  def verifyPageEnterAddressAndContinue() = {
    enterAddressInformation()
    checkYourAnswersCalculationsMap(
      getHeader(),
      addressLine1 + " " + addressLine2 + " " + townOrCity + " " + county + " " + postCode
    )
    submitPage()
  }
}
