/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object DonatedViaGiftAidAmount extends BasePage {

  def enterDonatedGiftAidAmount(giftAidAmount: String) = driver.findElement(By.id("value")).sendKeys(giftAidAmount)

  def verifyPageEnterGiftAidAmountAndContinue(giftAidAmount: String) = {
    enterDonatedGiftAidAmount(giftAidAmount)
    submitPage()
  }
}
