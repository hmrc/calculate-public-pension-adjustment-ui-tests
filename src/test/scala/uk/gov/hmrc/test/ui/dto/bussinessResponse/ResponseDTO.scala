package uk.gov.hmrc.test.ui.dto.bussinessResponse

import play.api.libs.json._

case class Resubmission(isResubmission: Boolean, reason: Option[String])

case class TotalAmounts(
  outDatesCompensation: Int,
  inDatesDebit: Int,
  inDatesCredit: Int
)

case class TaxYearScheme(
  name: String,
  pensionSchemeTaxReference: String,
  compensation: Option[Int]
)

case class OutDate(
  period: String,
  directCompensation: Option[Int],
  indirectCompensation: Option[Int],
  chargePaidByMember: Option[Int],
  chargePaidBySchemes: Option[Int],
  revisedChargableAmountBeforeTaxRate: Option[Int],
  revisedChargableAmountAfterTaxRate: Option[Int],
  unusedAnnualAllowance: Option[Int],
  taxYearSchemes: List[TaxYearScheme]
)

case class InDate(
  period: String,
  directCompensation: Option[Int],
  indirectCompensation: Option[Int],
  chargePaidByMember: Option[Int],
  chargePaidBySchemes: Option[Int],
  revisedChargableAmountBeforeTaxRate: Option[Int],
  revisedChargableAmountAfterTaxRate: Option[Int],
  unusedAnnualAllowance: Option[Int],
  taxYearSchemes: List[TaxYearScheme]
)

case class ResponseDTO(
  resubmission: Resubmission,
  totalAmounts: TotalAmounts,
  outDates: List[OutDate],
  inDates: List[InDate] // Empty array for inDates as per the provided JSON
)

object ResponseDTO {
  implicit val taxYearSchemeFormat: Format[TaxYearScheme] = Json.format[TaxYearScheme]
  implicit val outDateFormat: Format[OutDate]             = Json.format[OutDate]
  implicit val inDateFormat: Format[InDate]               = Json.format[InDate]
  implicit val resubmissionFormat: Format[Resubmission]   = Json.format[Resubmission]
  implicit val totalAmountsFormat: Format[TotalAmounts]   = Json.format[TotalAmounts]
  implicit val yourDTOFormat: Format[ResponseDTO]         = Json.format[ResponseDTO]
}
