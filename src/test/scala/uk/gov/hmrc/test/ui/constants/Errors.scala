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

package uk.gov.hmrc.test.ui.constants

object Errors {
  val ERROR_SUMMARY_TITLE                                                = "There is a problem"
  val RADIO_BUTTON_ERROR_SUMMARY                                         = "You must select one of the options"
  val TEXT_AREA_ERROR_SUMMARY                                            = "Enter your reason"
  val CHECK_BOX_ERROR_SUMMARY                                            = "You must select at least one of the options"
  val TEXT_WITH_500_CHARACTERS                                           =
    "o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789otest"
  val TEXT_WITH_501_CHARACTERS                                           =
    "o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789o'1-=2 3 456789otests"
  val REASON_FOR_RESUBMISSION_CHARACTER_LIMITATION_ERROR                 = "Your submission reason must be 500 characters or less"
  val SCOTTISH_TAX_PAYER_FROM_2016_PAGE_ERROR_SUMMARY                    =
    "Select yes if you were ever a Scottish taxpayer from 6 April 2016 onwards"
  val WHICH_YEARS_SCOTTISH_TAXPAYER_PAGE_ERROR_SUMMARY                   =
    "Select which years you were a Scottish taxpayer"
  val PAYING_INTO_PUBLIC_PENSION_SCHEME_PAGE_ERROR_SUMMARY               =
    "Select yes if you are paying into a public service pension scheme"
  val WHEN_STOP_PAYING_PUBLIC_PENSION_PAGE_NO_INPUT_ERROR_SUMMARY        =
    "Enter a date when you stopped paying into a public service pension scheme into the spaces provided as day, month, year"
  val WHEN_STOP_PAYING_PUBLIC_PENSION_PAGE_FUTURE_DATE_ERROR_SUMMARY     =
    "You have entered a future date when you stopped paying into a public service pension scheme, it must be within the remedy period 6 April 2015 to 5 April 2022"
  val WHEN_STOP_PAYING_PUBLIC_PENSION_PAGE_REAL_DATE_ERROR_SUMMARY       =
    "Enter a real date for when you stopped paying into a public service pension scheme into the spaces provided as day, month, year"
  val WHEN_STOP_PAYING_PUBLIC_PENSION_PAGE_PRE_REMEDY_DATE_ERROR_SUMMARY =
    "You have entered a pre-remedy date when you stopped paying into a public service pension scheme, it must be within the remedy period 6 April 2015 to 5 April 2022"
  val HAVE_DEFINED_CONTRIBUTION_PENSION_PAGE_ERROR_SUMMARY               =
    "Select yes if you have a defined contribution pension scheme"
  val HAVE_FLEXIBLY_ACCESSED_PENSION_PAGE_ERROR_SUMMARY                  =
    "Select yes if you have flexibly accessed your defined contribution pension saving"
  val WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_NO_INPUT_DATA_ERROR_SUMMARY      =
    "Enter the date that you flexibly accessed your defined contribution pension savings as day, month, year"
  val WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_REAL_DATE_ERROR_SUMMARY          =
    "Enter a real date for the date that you flexibly accessed your defined contribution pension savings as day, month, year"
  val WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_FUTURE_DATE_ERROR_SUMMARY        =
    "You have entered a future date for the date that you flexibly accessed your defined contribution pension savings, it must be within the remedy period"
  val WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_PRE_REMEDY_DATE_ERROR_SUMMARY    =
    "You have entered a date for the date that you flexibly accessed your defined contribution pension savings before the remedy period, enter a date after 5 April 2015"
  val WHEN_FLEXIBLY_ACCESS_PENSION_PAGE_DATES_FORMAT_ERROR_SUMMARY       =
    "Enter a date that you flexibly accessed your defined contribution pension savings as day, month, year"
}
