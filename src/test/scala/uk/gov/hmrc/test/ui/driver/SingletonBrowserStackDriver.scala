/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.driver

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.{MutableCapabilities, WebDriver}
import play.api.libs.json.Json
import util.{BrowserStackTestData, Environment}

import java.net.URL
import scala.io.Source
object SingletonBrowserStackDriver extends BrowserStackDriver {}
class BrowserStackDriver extends LazyLogging {
  private var instanceOption: Option[WebDriver] = None

  def getBrowserStackInstance(customOptions: Option[MutableCapabilities] = None): WebDriver = {
    if (instanceOption.isDefined && instanceOption.get.asInstanceOf[RemoteWebDriver].getSessionId == null)
      instanceOption = None

    instanceOption getOrElse initialiseBrowserStackBrowser(customOptions)
  }

  private def initialiseBrowserStackBrowser(customOptions: Option[MutableCapabilities]): WebDriver = {
    val driver = setupBrowserStackWeb()
    instanceOption = Some(driver)
    driver
  }

  def getBrowserStackMobileInstance(customOptions: Option[MutableCapabilities] = None): WebDriver = {
    if (instanceOption.isDefined && instanceOption.get.asInstanceOf[RemoteWebDriver].getSessionId == null)
      instanceOption = None

    instanceOption getOrElse initialiseBrowserStackMobileBrowser(customOptions)
  }

  private def initialiseBrowserStackMobileBrowser(customOptions: Option[MutableCapabilities]): WebDriver = {
    val driver = setupBrowserStackMobileWeb()
    instanceOption = Some(driver)
    driver
  }
  def setupBrowserStackWeb(): RemoteWebDriver = {
    val jsonStr              = Source.fromFile("src/test/resources/conf/browserstack.json").getLines.mkString
    val json                 = Json.parse(jsonStr)
    val testData             = json.as[BrowserStackTestData]
    val server               = testData.server
    val user                 = testData.user
    val key                  = testData.key
    val buildName            = testData.capabilities.buildName
    val testSet1Environments = testData.environments.filter(_.keys.head == sys.props.get("testDataSet").getOrElse())

    // Accessing the filtered environments
    val testSetData      = testSet1Environments.map(_.values.head.as[Environment])
    val firstEnvironment = testSetData.head
    val os               = firstEnvironment.os
    val osVersion        = firstEnvironment.os_version
    val browserName      = firstEnvironment.browserName
    val browserVersion   = firstEnvironment.browserVersion

    /* val desiredCaps = new MutableCapabilities()
    desiredCaps.setCapability("project", buildName)
    desiredCaps.setCapability("build", sessionName)
    desiredCaps.setCapability("browserstack.idleTimeout", "2")
    desiredCaps.setCapability("browserstack.networkLogs", true);
    desiredCaps.setCapability("browserstack.local", browserstack_local)
    desiredCaps.setCapability("browserstack.localIdentifier", browserstack_localIdentifier)
    desiredCaps.setCapability("os", os)
    desiredCaps.setCapability("os_version", osVersion)
    desiredCaps.setCapability("browser", browserName)
    desiredCaps.setCapability("browser_version ", browserVersion)*/
    var remoteDriver: RemoteWebDriver = null
    val capabilities                  = new MutableCapabilities()
    capabilities.setCapability("browserName", browserName)
    val browserstackOptions           = new java.util.HashMap[String, Object]()
    browserstackOptions.put("os", os)
    browserstackOptions.put("osVersion", osVersion)
    browserstackOptions.put("browserVersion", browserVersion)
    browserstackOptions.put("local", "true")
    browserstackOptions.put("projectName", "Public Pension Adjustments")
    browserstackOptions.put("buildName", buildName)
    browserstackOptions.put("seleniumVersion", "4.7.2")
    capabilities.setCapability("bstack:options", browserstackOptions)
    remoteDriver = new RemoteWebDriver(new URL("http://" + user + ":" + key + "@" + server + "/wd/hub"), capabilities)
    remoteDriver.manage().window().maximize()
    remoteDriver
  }

  def setupBrowserStackMobileWeb(): RemoteWebDriver = {
    val jsonStr              = Source.fromFile("src/test/resources/conf/browserstack.json").getLines.mkString
    val json                 = Json.parse(jsonStr)
    val testData             = json.as[BrowserStackTestData]
    val server               = testData.server
    val user                 = testData.user
    val key                  = testData.key
    val testSet1Environments = testData.environments.filter(_.keys.head == sys.props.get("testDataSet").getOrElse())

    // Accessing the filtered environments
    val testSetData      = testSet1Environments.map(_.values.head.as[Environment])
    val firstEnvironment = testSetData.head
    val os               = firstEnvironment.os
    val osVersion        = firstEnvironment.os_version
    val browserName      = firstEnvironment.browserName

    var remoteDriver: RemoteWebDriver = null
    val capabilities                  = new MutableCapabilities()
    capabilities.setCapability("browserName", browserName)
    val browserstackOptions           = new java.util.HashMap[String, Object]()
    browserstackOptions.put("osVersion", osVersion)
    browserstackOptions.put("deviceName", os)
    browserstackOptions.put("projectName", "Public Pension Adjustments")
    browserstackOptions.put("buildName", "Public Pensions Adjustments mobile web tests")
    browserstackOptions.put("realMobile", "true")
    browserstackOptions.put("local", "true")
    browserstackOptions.put("appiumVersion", "2.0.1");
    capabilities.setCapability("bstack:options", browserstackOptions)
    remoteDriver = new RemoteWebDriver(new URL("http://" + user + ":" + key + "@" + server + "/wd/hub"), capabilities)
    remoteDriver.manage().window().maximize()
    remoteDriver
  }
  def closeBrowserStackInstance(): Unit =
    try instanceOption foreach { instance =>
      instance.quit()
    }
}
