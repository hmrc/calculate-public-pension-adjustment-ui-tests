/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package util

import play.api.libs.json.{Format, JsObject, Json}
case class Capabilities(
  buildName: String,
  sessionName: String,
  browserstack_local: Boolean,
  browserstack_localIdentifier: String,
  debug: Boolean
)
case class Environment(os: String, os_version: String, browserName: String, browserVersion: String)
case class BrowserStackTestData(
  server: String,
  user: String,
  key: String,
  capabilities: Capabilities,
  environments: Seq[JsObject]
)

object Capabilities {
  implicit val jsonFormat: Format[Capabilities] = Json.format[Capabilities]
}
object Environment {
  implicit val jsonFormat: Format[Environment] = Json.format[Environment]
}
object BrowserStackTestData {
  implicit val jsonFormat: Format[BrowserStackTestData] = Json.format[BrowserStackTestData]
}
