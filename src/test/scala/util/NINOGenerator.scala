/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package util

import scala.util.Random

object NINOGenerator {
  val nino = {
    val firstTwoLetters = "ABCEHJKLMPRSTWXY"
    val letterPart      = Random.shuffle(firstTwoLetters.toList).take(2).mkString
    val numberPart      = Random.nextInt(999999).toString.reverse.padTo(6, '0').reverse
    val lastLetters     = "ABCD"
    val lastLetterPart  = Random.shuffle(lastLetters.toList).take(1).mkString
    s"$letterPart$numberPart$lastLetterPart"
  }
}
