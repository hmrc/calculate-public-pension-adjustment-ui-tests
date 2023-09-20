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

import util.DataCollectorMap

object WhatYouWillNeedAaPage extends BasePage {

  val YEAR_2016_PRE_PAGE_TITLE   =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2016_PRE_PAGE_HEADER  = "What you will need"
  val YEAR_2016_POST_PAGE_TITLE  =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2016_POST_PAGE_HEADER = "What you will need"
  val YEAR_2017_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2017_PAGE_HEADER      = "What you will need"
  val YEAR_2018_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2018_PAGE_HEADER      = "What you will need"
  val YEAR_2019_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2019_PAGE_HEADER      = "What you will need"
  val YEAR_2020_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2020_PAGE_HEADER      = "What you will need"
  val YEAR_2021_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2021_PAGE_HEADER      = "What you will need"
  val YEAR_2022_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2022_PAGE_HEADER      = "What you will need"
  val YEAR_2023_PAGE_TITLE       =
    "What you will need - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2023_PAGE_HEADER      = "What you will need"

  def onWhatYouWillNeedAa2016PrePage() = {
    verifyPageUrl("annual-allowance/2016-pre/information")
    onPage(YEAR_2016_PRE_PAGE_TITLE)
    isHeader(YEAR_2016_PRE_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2016PostPage() = {
    verifyPageUrl("annual-allowance/2016-post/information")
    onPage(YEAR_2016_POST_PAGE_TITLE)
    isHeader(YEAR_2016_POST_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2017Page() = {
    verifyPageUrl("annual-allowance/2017/information")
    onPage(YEAR_2017_PAGE_TITLE)
    isHeader(YEAR_2017_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2018Page() = {
    verifyPageUrl("annual-allowance/2018/information")
    onPage(YEAR_2018_PAGE_TITLE)
    isHeader(YEAR_2018_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2019Page() = {
    verifyPageUrl("annual-allowance/2019/information")
    onPage(YEAR_2019_PAGE_TITLE)
    isHeader(YEAR_2019_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2020Page() = {
    verifyPageUrl("annual-allowance/2020/information")
    onPage(YEAR_2020_PAGE_TITLE)
    isHeader(YEAR_2020_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2021Page() = {
    verifyPageUrl("annual-allowance/2021/information")
    onPage(YEAR_2021_PAGE_TITLE)
    isHeader(YEAR_2021_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2022Page() = {
    verifyPageUrl("annual-allowance/2022/information")
    onPage(YEAR_2022_PAGE_TITLE)
    isHeader(YEAR_2022_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2023Page() = {
    verifyPageUrl("annual-allowance/2023/information")
    onPage(YEAR_2023_PAGE_TITLE)
    isHeader(YEAR_2023_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }

}
