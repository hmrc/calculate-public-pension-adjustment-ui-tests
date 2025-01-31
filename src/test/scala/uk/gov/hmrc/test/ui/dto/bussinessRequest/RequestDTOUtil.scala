/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.dto.bussinessRequest

import play.api.libs.json.JsResult

class RequestDTOUtil {
  def getTaxYearInformation(year: String, field: TaxYear => Option[Any], myObject: JsResult[RequestDTO]): Any =
    myObject.asOpt
      .flatMap(_.annualAllowance.taxYears.find(_.period == year))
      .flatMap(field)
      .getOrElse("-1")

  def getFlexiAccessDate(myObject: Option[List[TaxYear]]): Any =
    myObject.flatMap(_.flatMap(_.flexiAccessDate).headOption).getOrElse("0")

  def getPreAccessDefinedContributionInputAmount(myObject: Option[List[TaxYear]]): Any =
    myObject.flatMap(_.flatMap(_.preAccessDefinedContributionInputAmount).headOption).getOrElse("0")

  def getPostAccessDefinedContributionInputAmount(myObject: Option[List[TaxYear]]): Any =
    myObject.flatMap(_.flatMap(_.postAccessDefinedContributionInputAmount).headOption).getOrElse("0")

  def getTaxYearSchemes(year: String, myObject: JsResult[RequestDTO]): Option[List[TaxYearSchemes]] =
    myObject.asOpt
      .flatMap(_.annualAllowance.taxYears.find(_.period == year))
      .flatMap(_.taxYearSchemes)

  def getIncomeDetails(year: String, field: Income => Any, myObject: JsResult[RequestDTO]): Any =
    myObject.asOpt
      .flatMap(_.annualAllowance.taxYears.find(_.period == year))
      .flatMap(_.income)
      .map(field)
      .getOrElse("0")

}
