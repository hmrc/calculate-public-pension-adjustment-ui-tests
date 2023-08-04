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

package uk.gov.hmrc.test.ui.dto.bussinessRequest

import play.api.libs.json.{Json, OFormat}

case class Resubmission(isResubmission: Boolean, reason: Option[String])

case class Income(incomeAboveThreshold: Boolean, adjustedIncome: Option[Int])

case class TaxYearSchemes(
  name: String,
  pensionSchemeTaxReference: String,
  originalPensionInputAmount: Int,
  revisedPensionInputAmount: Int,
  chargePaidByScheme: Int
)

case class TaxYear(
  pensionInputAmount: Option[Int],
  definedBenefitInputAmount: Option[Int],
  definedContributionInputAmount: Option[Int],
  flexiAccessDate: Option[String],
  preAccessDefinedContributionInputAmount: Option[Int],
  postAccessDefinedContributionInputAmount: Option[Int],
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
