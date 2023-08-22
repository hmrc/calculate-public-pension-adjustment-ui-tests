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

import play.api.libs.json.JsResult

class RequestDTOUtil {
  def getTaxYearInformation(year: String, field: TaxYear => Option[Any], myObject: JsResult[RequestDTO]): Any =
    myObject.asOpt
      .flatMap(_.annualAllowance.taxYears.find(_.period == year))
      .flatMap(field)
      .getOrElse("0")

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
