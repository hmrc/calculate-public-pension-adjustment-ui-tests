/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.dto.bussinessRequest

import play.api.libs.json.{Json, OFormat}

case class Resubmission(isResubmission: Boolean, reason: Option[String])

case class Income(incomeAboveThreshold: Boolean, adjustedIncome: Option[Int])

case class TaxYearSchemes(
  name: String,
  pensionSchemeTaxReference: String,
  originalPensionInputAmount: Int,
  revisedPensionInputAmount: Int,
  chargePaidByScheme: Int,
  originalPensionInput2016PostAmount: Option[Int],
  revisedPensionInput2016PostAmount: Option[Int]
)

case class TaxYear(
  pensionInputAmount: Option[Int],
  definedBenefitInputAmount: Option[Int],
  definedContributionInputAmount: Option[Int],
  flexiAccessDate: Option[String],
  preAccessDefinedContributionInputAmount: Option[Int],
  postAccessDefinedContributionInputAmount: Option[Int],
  definedBenefitInput2016PostAmount: Option[Int],
  definedContributionInput2016PostAmount: Option[Int],
  postAccessDefinedContributionInput2016PostAmount: Option[Int],
  totalIncome: Option[Int],
  chargePaidByMember: Option[Int],
  period: String,
  taxYearSchemes: Option[List[TaxYearSchemes]],
  income: Option[Income]
)

case class AnnualAllowance(
  scottishTaxYears: List[String],
  taxYears: List[TaxYear]
)

case class RequestDTO(resubmission: Resubmission, annualAllowance: AnnualAllowance)

object RequestDTO {
  implicit val income: OFormat[Income]                   = Json.format[Income]
  implicit val taxYearSchemes: OFormat[TaxYearSchemes]   = Json.format[TaxYearSchemes]
  implicit val taxYears: OFormat[TaxYear]                = Json.format[TaxYear]
  implicit val resubmission: OFormat[Resubmission]       = Json.format[Resubmission]
  implicit val annualAllowance: OFormat[AnnualAllowance] = Json.format[AnnualAllowance]
  implicit val requestDTO: OFormat[RequestDTO]           = Json.format[RequestDTO]
}
