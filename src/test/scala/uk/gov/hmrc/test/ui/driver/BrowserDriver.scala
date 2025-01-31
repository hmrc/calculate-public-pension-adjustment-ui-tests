/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.driver

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.WebDriver
import org.scalatest.concurrent.Eventually
import uk.gov.hmrc.selenium.webdriver.Driver

trait BrowserDriver extends LazyLogging with Eventually {
  logger.info(
    s"Instantiating Browser: ${sys.props.getOrElse("browser", "'browser' System property not set. This is required")}"
  )

  implicit def driver: WebDriver = sys.props.get("browser") match {
    case Some("chrome") => Driver.instance
    case _              => Driver.instance
  }

}
