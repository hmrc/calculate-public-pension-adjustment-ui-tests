/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object InternationalAddressPage extends BasePage {
  val addressLine1  = "No 138"
  val addressLine2  = "Prince ref road"
  val townOrCity    = "London"
  val stateOrRegion = "London"
  val postCode      = "AB1 9ED"
  val country       = "France"

  def enterAddressInformation() = {
    driver.findElement(By.id("addressLine1")).sendKeys(addressLine1)
    driver.findElement(By.id("addressLine2")).sendKeys(addressLine2)
    driver.findElement(By.id("townOrCity")).sendKeys(townOrCity)
    driver.findElement(By.id("stateOrRegion")).sendKeys(stateOrRegion)
    driver.findElement(By.id("postCode")).sendKeys(postCode)
    driver.findElement(By.id("country")).sendKeys(country)
  }

  def verifyPageEnterAddressAndContinue() = {
    enterAddressInformation()
    checkYourAnswersCalculationsMap(
      getHeader(),
      addressLine1 + " " + addressLine2 + " " + townOrCity + " " + stateOrRegion + " " + postCode + " " + country
    )
    submitPage()
  }
}
