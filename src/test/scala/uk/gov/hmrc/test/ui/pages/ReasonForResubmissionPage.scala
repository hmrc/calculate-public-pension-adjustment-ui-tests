/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ReasonForResubmissionPage extends BasePage {

  def enterResubmissionReason(reason: String): Unit = {
    driver.findElement(By.xpath("//textarea[@id='value']")).clear
    driver.findElement(By.xpath("//textarea[@id='value']")).sendKeys(reason)
  }

  def getResubmissionReason(): String =
    driver.findElement(By.xpath("//textarea[@id='value']")).getAttribute("value")

  def clearTextarea(): Unit =
    driver.findElement(By.xpath("//textarea[@id='value']")).clear()

  def enterReasonAndContinue() = {
    val reason = "resubmission reason"
    enterResubmissionReason(reason)
    submitPage()
  }

  def enterReasonAndContinue(reason: String) = {
    enterResubmissionReason(reason)
    submitPage()
  }

  def enterLengthierReasonAndContinue(reason: String) = {
    enterResubmissionReason(reason)
    submitPage()
  }
}
