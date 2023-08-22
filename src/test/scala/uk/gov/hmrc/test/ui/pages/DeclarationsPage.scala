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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.constants.PageInformation.{DECLARATION_PAGE_HEADER, DECLARATION_PAGE_TITLE}

object DeclarationsPage extends BasePage {
  def verifyDeclarationsPage() = {
    verifyPageUrl("declarations")
    onPage(DECLARATION_PAGE_TITLE)
    isHeader(DECLARATION_PAGE_HEADER)
  }

  def clickConfirmButton() = driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click()

  /** remove navigation to url */
  def verifyPageAndConfirm() = {
    verifyDeclarationsPage()
    clickConfirmButton()
  }

}
