/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object AmountFlexibleRemunerationArrangements extends BasePage {

  def enterFlexibleRemunerationAmount(flexibleRemuneration: String) =
    driver.findElement(By.id("value")).sendKeys(flexibleRemuneration)

  def enterFlexibleRemunerationAmountAndContinue(flexibleRemuneration: String) = {
    enterFlexibleRemunerationAmount(flexibleRemuneration: String)
    submitPage()
  }

}
