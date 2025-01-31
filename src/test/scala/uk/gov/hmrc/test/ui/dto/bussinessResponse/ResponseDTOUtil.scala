/*
 * Copyright 2023 HM Revenue & Customs
 *
 */

package uk.gov.hmrc.test.ui.dto.bussinessResponse

import play.api.libs.json.JsResult

class ResponseDTOUtil {
  def getOutDatesInformation(year: String, field: OutDate => Option[Any], myObject: JsResult[ResponseDTO]): Any =
    myObject.asOpt
      .flatMap(_.outDates.find(_.period == year))
      .flatMap(field)
      .getOrElse("0")

}
