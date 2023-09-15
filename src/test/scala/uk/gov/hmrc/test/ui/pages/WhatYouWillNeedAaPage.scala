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
    "6 April 2015 to 8 July 2015 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2016_PRE_PAGE_HEADER  = "6 April 2015 to 8 July 2015 remedy period"
  val YEAR_2016_POST_PAGE_TITLE  =
    "9 July 2015 to 5 April 2016 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2016_POST_PAGE_HEADER = "9 July 2015 to 5 April 2016 remedy period"
  val YEAR_2017_PAGE_TITLE       =
    "6 April 2016 to 5 April 2017 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2017_PAGE_HEADER      = "6 April 2016 to 5 April 2017 remedy period"
  val YEAR_2018_PAGE_TITLE       =
    "6 April 2017 to 5 April 2018 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2018_PAGE_HEADER      = "6 April 2017 to 5 April 2018 remedy period"
  val YEAR_2019_PAGE_TITLE       =
    "6 April 2018 to 5 April 2019 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2019_PAGE_HEADER      = "6 April 2018 to 5 April 2019 remedy period"
  val YEAR_2020_PAGE_TITLE       =
    "6 April 2019 to 5 April 2020 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2020_PAGE_HEADER      = "6 April 2019 to 5 April 2020 remedy period"
  val YEAR_2021_PAGE_TITLE       =
    "6 April 2020 to 5 April 2021 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2021_PAGE_HEADER      = "6 April 2020 to 5 April 2021 remedy period"
  val YEAR_2022_PAGE_TITLE       =
    "6 April 2021 to 5 April 2022 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2022_PAGE_HEADER      = "6 April 2021 to 5 April 2022 remedy period"
  val YEAR_2023_PAGE_TITLE       =
    "6 April 2022 to 5 April 2023 remedy period - Calculate your public service pension adjustment - GOV.UK"
  val YEAR_2023_PAGE_HEADER      = "6 April 2022 to 5 April 2023 remedy period"

  def onWhatYouWillNeedAa2016PrePage() = {
    verifyPageUrl("what-you-will-need-aa/2016-pre")
    onPage(YEAR_2016_PRE_PAGE_TITLE)
    isHeader(YEAR_2016_PRE_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2016PostPage() = {
    verifyPageUrl("what-you-will-need-aa/2016-post")
    onPage(YEAR_2016_POST_PAGE_TITLE)
    isHeader(YEAR_2016_POST_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2017Page() = {
    verifyPageUrl("what-you-will-need-aa/2017")
    onPage(YEAR_2017_PAGE_TITLE)
    isHeader(YEAR_2017_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2018Page() = {
    verifyPageUrl("what-you-will-need-aa/2018")
    onPage(YEAR_2018_PAGE_TITLE)
    isHeader(YEAR_2018_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2019Page() = {
    verifyPageUrl("what-you-will-need-aa/2019")
    onPage(YEAR_2019_PAGE_TITLE)
    isHeader(YEAR_2019_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2020Page() = {
    verifyPageUrl("what-you-will-need-aa/2020")
    onPage(YEAR_2020_PAGE_TITLE)
    isHeader(YEAR_2020_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2021Page() = {
    verifyPageUrl("what-you-will-need-aa/2021")
    onPage(YEAR_2021_PAGE_TITLE)
    isHeader(YEAR_2021_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2022Page() = {
    verifyPageUrl("what-you-will-need-aa/2022")
    onPage(YEAR_2022_PAGE_TITLE)
    isHeader(YEAR_2022_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }
  def onWhatYouWillNeedAa2023Page() = {
    verifyPageUrl("what-you-will-need-aa/2023")
    onPage(YEAR_2023_PAGE_TITLE)
    isHeader(YEAR_2023_PAGE_HEADER)
    DataCollectorMap.checkAnswersAAPeriod = List.empty[(String, Any)]
    clickContinueButton()
  }

}
