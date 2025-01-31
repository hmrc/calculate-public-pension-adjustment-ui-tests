/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.specs

import org.scalatest._
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}

trait BaseSpec
    extends AnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  override def beforeEach(): Unit =
    startBrowser()

  override def afterEach(): Unit =
    quitBrowser()

}
