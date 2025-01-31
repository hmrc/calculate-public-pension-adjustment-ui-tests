/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import util.DataCollectorMap

object HomePage extends BasePage {
  val url: String = TestConfiguration.url("ui-frontend")

  def loadPage(): this.type = {
    driver.navigate().to(url)
    this
  }

  def tempNavigation(navUrl: String) =
    driver.navigate().to(navUrl)

  def clickStartButton(): HomePage.type = {
    driver.findElement(By.xpath("//a[contains(text(),'Start now')]")).click()
    HomePage
  }

  def goToHomepage(): Unit = {
    DataCollectorMap.checkAnswersGS.clear()
    DataCollectorMap.checkAnswersAAS.clear()
    DataCollectorMap.checkAnswersLAS = List.empty[(String, Any)]
    DataCollectorMap.checkAnswersCalculationsPage = List.empty[(String, Any)]
    signOutPage()
    loadPage()
  }
}
