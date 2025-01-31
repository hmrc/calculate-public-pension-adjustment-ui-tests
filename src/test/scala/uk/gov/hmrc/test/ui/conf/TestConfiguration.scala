/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.conf

import com.typesafe.config.{Config, ConfigFactory}

object TestConfiguration {
  val config: Config        = ConfigFactory.load()
  val env: String           = config.getString("environment")
  val defaultConfig: Config = config.getConfig("local")
  val optionalAuth: Config  = config.getConfig("feature-flag")
  val envConfig: Config     = config.getConfig(env).withFallback(defaultConfig)

  def url(service: String): String = {
    val host = env match {
      case "local" => s"$environmentHost:${servicePort(service)}"
      case _       => s"${envConfig.getString(s"services.host")}"
    }
    s"$host${serviceRoute(service)}"
  }

  def environmentHost: String    = envConfig.getString("services.host")
  def optionalAuthFlag(): String = optionalAuth.getString("optionalAuth")

  def servicePort(serviceName: String): String = envConfig.getString(s"services.$serviceName.port")

  def serviceRoute(serviceName: String): String = envConfig.getString(s"services.$serviceName.productionRoute")
}
