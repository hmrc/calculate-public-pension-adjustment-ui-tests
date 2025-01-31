/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package util

import scala.collection.mutable

object DataCollectorMap {
  val checkAnswersGS: mutable.Map[String, Any]          = mutable.Map[String, Any]()
  val checkAnswersAAS: mutable.Map[String, Any]         = mutable.Map[String, Any]()
  var checkAnswersLAS: List[(String, Any)]              = List[(String, Any)]()
  var checkAnswersAAPeriod: List[(String, Any)]         = List[(String, Any)]()
  var checkAnswersCalculationsPage: List[(String, Any)] = List[(String, Any)]()
  def addToGSMap(key: String, value: Any): Unit         =
    checkAnswersGS += (key -> value)

  def addToAASMap(key: String, value: Any): Unit =
    checkAnswersAAS += (key -> value)

  def addToLASMap(key: String, value: Any): Unit =
    checkAnswersLAS = checkAnswersLAS :+ (key, value)

  def addToAAPeriodMap(key: String, value: Any): Unit =
    checkAnswersAAPeriod = checkAnswersAAPeriod :+ (key, value)

  def addToCalculationsPeriodMap(key: String, value: Any): Unit =
    checkAnswersCalculationsPage = checkAnswersCalculationsPage :+ (key, value)
}
