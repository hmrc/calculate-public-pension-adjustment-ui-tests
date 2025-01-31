/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object ReviewLifetimeAllowanceAnswersPage extends BasePage {

  def getReviewLTAanswersPageVerificationText(): String =
    driver
      .findElement(By.xpath("//h1[contains(text(),'Review lifetime allowance answers')]"))
      .getText

}
