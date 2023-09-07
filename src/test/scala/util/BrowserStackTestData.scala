/*
 * Copyright 2023 HM Revenue & Customs
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
