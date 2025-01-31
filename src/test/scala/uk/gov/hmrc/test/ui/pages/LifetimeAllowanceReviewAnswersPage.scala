/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object LifetimeAllowanceReviewAnswersPage extends BasePage {
  def verifyLifetimeAllowanceReviewAnswersPageAndContinue() =
    clickContinueButton()

  def verifyLifeTimeAllowanceReviewPageHeading() =
    driver
      .findElement(By.xpath("//h1[contains(text(),'Review your lifetime allowance answers')]"))
}
