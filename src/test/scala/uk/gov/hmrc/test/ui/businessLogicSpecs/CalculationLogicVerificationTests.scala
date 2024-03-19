/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.businessLogicSpecs

import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.test.ui.dto.bussinessRequest.{RequestDTO, RequestDTOUtil, TaxYear, TaxYearSchemes}
import uk.gov.hmrc.test.ui.dto.bussinessResponse.{OutDate, ResponseDTO, ResponseDTOUtil}
import uk.gov.hmrc.test.ui.pages.HomePage.signOutPage
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.BaseSpec
import uk.gov.hmrc.test.ui.specs.tags.ZapTests

class CalculationLogicVerificationTests extends BaseSpec {

  Feature("Business scenario AA journeys") {
    val requestArray: Array[String] =
      Array(
        "Scenario_3a",
        "Scenario_2d",
        "Scenario_2c",
        "Scenario_36",
        "Scenario_35",
        "Scenario_34",
        "Scenario_33",
        "Scenario_32",
        "Scenario_31",
        "Scenario_30",
        "Scenario_29",
        "Scenario_28",
        "Scenario_27",
        "Scenario_26",
        "Scenario_25",
        "Scenario_1a",
        "Scenario_1b",
        "Scenario_2a"
      )
    requestArray.indices.foreach { index =>
      Scenario(s"Calculate Business Journey $index", ZapTests) {

        /** Retrieve request information */
        val requestStream    =
          getClass.getResourceAsStream(
            "/businessCases/request/" + requestArray(index) + "_Request.json"
          )
        val jsonString       = scala.io.Source.fromInputStream(requestStream).mkString
        val json: JsValue    = Json.parse(jsonString)
        val requestDTOResult = Json.fromJson[RequestDTO](json)

        /** Outer Json common fields */
        val isResubmission                             = requestDTOResult.asOpt.map(_.resubmission.isResubmission)
        val reason                                     = requestDTOResult.asOpt.map(_.resubmission.reason).getOrElse("0")
        val taxYears: Option[List[TaxYear]]            = requestDTOResult.asOpt.map(_.annualAllowance.taxYears)
        val scottishTaxYears: Option[List[String]]     = requestDTOResult.asOpt.map(_.annualAllowance.scottishTaxYears)
        val filteredTaxYearsOpt: Option[List[TaxYear]] = taxYears.map(_.filter(_.period >= "2017"))
        val taxYearsBefore2016                         = taxYears.map(_.filter(_.period <= "2015"))

        val lastPeriodOpt: String = filteredTaxYearsOpt
          .flatMap { filteredTaxYears =>
            if (filteredTaxYears.nonEmpty) {
              Some(filteredTaxYears.last.period)
            } else {
              None
            }
          }
          .getOrElse("0")

        /** Inner Json common fields */
        val myObject: RequestDTOUtil                    = new RequestDTOUtil()
        val getFlexiAccessDate                          =
          myObject.getFlexiAccessDate(taxYears)
        val getPreAccessDefinedContributionInputAmount  =
          myObject.getPreAccessDefinedContributionInputAmount(taxYears).toString
        val getPostAccessDefinedContributionInputAmount =
          myObject.getPostAccessDefinedContributionInputAmount(taxYears).toString

        /** Tax information 2016 */
        val taxYearScheme2016: Option[List[TaxYearSchemes]] =
          myObject.getTaxYearSchemes("2016", requestDTOResult)
        var chargePaidByMember2016                          =
          myObject.getTaxYearInformation("2016", _.chargePaidByMember, requestDTOResult).toString
        val definedBenefitInputAmount2016pre                =
          myObject.getTaxYearInformation("2016", _.definedBenefitInputAmount, requestDTOResult).toString
        val definedBenefitInputAmount2016post               =
          myObject.getTaxYearInformation("2016", _.definedBenefitInput2016PostAmount, requestDTOResult).toString
        val definedContributionInputAmount2016pre1          =
          myObject.getTaxYearInformation("2016", _.preAccessDefinedContributionInputAmount, requestDTOResult).toString
        val definedContributionInputAmount2016pre2          =
          myObject.getTaxYearInformation("2016", _.postAccessDefinedContributionInputAmount, requestDTOResult).toString
        val definedContributionInputAmount2016post1         =
          myObject.getTaxYearInformation("2016", _.definedContributionInput2016PostAmount, requestDTOResult).toString
        val definedContributionInputAmount2016post2         =
          myObject
            .getTaxYearInformation("2016", _.postAccessDefinedContributionInput2016PostAmount, requestDTOResult)
            .toString
        val preYear                                         = "2016"
        val FlexiAccessDate2016                             =
          myObject.getTaxYearInformation(preYear, _.flexiAccessDate, requestDTOResult).toString
        val totalIncome2016                                 =
          myObject.getTaxYearInformation("2016", _.totalIncome, requestDTOResult).toString

        /** Retrieve response information */

        val responseStream                    =
          getClass.getResourceAsStream(
            "/businessCases/response/" + requestArray(index) + "_Response.json"
          )
        val jsonResponseString                = scala.io.Source.fromInputStream(responseStream).mkString
        val jsonResponse: JsValue             = Json.parse(jsonResponseString)
        val responseDTOResult                 = Json.fromJson[ResponseDTO](jsonResponse)
        val myResponseObject: ResponseDTOUtil = new ResponseDTOUtil()

        /** Test */
        Given("I am on the Public Service Pensions Remediation home page")
        HomePage.goToHomepage()
        SavingsStatementPage.selectYesAndContinueForGSPage()
        if (isResubmission.mkString == "true") {
          ResubmittingAdjustmentPage.selectOptionAndContinueForGSPage(isResubmission.mkString)
          ReasonForResubmissionPage.enterReasonAndContinue(reason.toString)
        } else {
          ResubmittingAdjustmentPage.selectNoAndContinueForGSPage()
        }
        ReportingChangePage.selectAnnualAllowanceAndContinue()
        CheckYourAnswersPage.verifyCheckYourAnswersPageAndContinue()
        if (!(scottishTaxYears.exists(_.isEmpty))) {
          ScottishTaxpayerFrom2016Page.selectYesAndContinueForAASPage()
          scottishTaxYears.foreach { taxYears =>
            taxYears.foreach { taxYear =>
              WhichYearsScottishTaxpayer.selectScottishTaxPayerYear(taxYear)
            }
          }
          WhichYearsScottishTaxpayer.submitPage()
        } else {
          ScottishTaxpayerFrom2016Page.selectNoAndContinueForAASPage()
        }
        if (lastPeriodOpt == "2023") {
          PayingIntoPublicPensionSchemePage.selectYesAndContinueForAASPage()
        } else {
          PayingIntoPublicPensionSchemePage.selectNoAndContinueForAASPage()
          WhenStopPayingPublicPensionPage.enterValidDateAndClickContinue("01", "01", lastPeriodOpt)
        }
        HaveDefinedContributionPensionPage.selectYesAndContinueForAASPage()
        if (!(getFlexiAccessDate == "0")) {
          HaveFlexiblyAccessedPensionPage.selectYesAndContinueForAASPage()
          WhenFlexiblyAccessPensionPage.enterDateAndClickContinue(getFlexiAccessDate.toString)
        } else {
          HaveFlexiblyAccessedPensionPage.selectNoAndContinueForAASPage()
        }
        if (taxYearsBefore2016.exists(_.isEmpty)) {
          PayTaxChargeFrom20152016Page.selectYesAndContinueForAASPage()
        } else {
          PayTaxChargeFrom20152016Page.selectNoAndContinueForAASPage()
          taxYearsBefore2016.foreach { taxYears =>
            taxYears.foreach { taxYear =>
              val year          = taxYear.period
              val pensionAmount =
                myObject.getTaxYearInformation(year, _.pensionInputAmount, requestDTOResult).toString
              year match {
                case "2011" =>
                  Registered.onRegisteredPageSelectYesAndContinue("2010", "2011")
                  PiaPreRemedyPage2011.enterAmountAndClickContinue(pensionAmount)
                case "2012" =>
                  Registered.onRegisteredPageSelectYesAndContinue("2011", "2012")
                  PiaPreRemedyPage2012.enterAmountAndClickContinue(pensionAmount)
                case "2013" =>
                  Registered.onRegisteredPageSelectYesAndContinue("2012", "2013")
                  PiaPreRemedyPage2013.enterAmountAndClickContinue(pensionAmount)
                case "2014" =>
                  Registered.onRegisteredPageSelectYesAndContinue("2013", "2014")
                  PiaPreRemedyPage2014.enterAmountAndClickContinue(pensionAmount)
                case "2015" =>
                  Registered.onRegisteredPageSelectYesAndContinue("2014", "2015")
                  PiaPreRemedyPage2015.enterAmountAndClickContinue(pensionAmount)
              }
            }
          }
        }
        CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

        /** --- 2016 Pre --- */
        TaskListPage.clickAddDetailsFor2015To2016()
        WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()
        if (taxYearScheme2016.map(_.size).getOrElse(0) > 1) {
          MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
        } else {
          MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()
        }
        taxYearScheme2016.foreach { taxYearSchemesList =>
          for ((taxYearScheme, index) <- taxYearSchemesList.zipWithIndex) {
            var paidByScheme: Int = taxYearScheme.chargePaidByScheme
            PensionSchemeDetailsPage.enterTaxInformationAndContinue(
              taxYearScheme.name,
              taxYearScheme.pensionSchemeTaxReference
            )
            PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
              taxYearScheme.originalPensionInputAmount.toString,
              taxYearScheme.revisedPensionInputAmount.toString
            )
            PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
              taxYearScheme.originalPensionInput2016PostAmount.get.toString,
              taxYearScheme.revisedPensionInput2016PostAmount.get.toString
            )
            if (paidByScheme == 0 && chargePaidByMember2016.toInt == 0) {
              DidYouPayAChargePage.verifyPageSelectNoAndContinue()
            } else {
              DidYouPayAChargePage.verifyPageSelectYesAndContinue()
              if (paidByScheme > 0 && chargePaidByMember2016.toInt > 0) {
                WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue()
                HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(chargePaidByMember2016)
                chargePaidByMember2016 = "0"

                HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(paidByScheme.toString)
                paidByScheme = 0
              }
              if (paidByScheme > 0 && chargePaidByMember2016.toInt == 0 && index < 1) {
                WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue()
                HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(paidByScheme.toString)
                paidByScheme = 0
              }
              if (paidByScheme > 0 && chargePaidByMember2016.toInt == 0 && index >= 1) {
                HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(paidByScheme.toString)
                paidByScheme = 0
              }
              if (paidByScheme == 0 && chargePaidByMember2016.toInt > 0) {
                WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue()
                HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(chargePaidByMember2016)
                chargePaidByMember2016 = "0"
              }
            }
            if ((taxYearSchemesList.size > 1) && (index == taxYearSchemesList.size - 1)) {
              AddAnotherSchemePage.verifyPageSelectNoAndContinue()
              if (
                (FlexiAccessDate2016 == "0") && !(definedBenefitInputAmount2016pre == "0") && !(definedContributionInputAmount2016pre1 == "0")
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  definedContributionInputAmount2016pre1
                )
                if (definedContributionInputAmount2016pre2.toInt > 0) {
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount2016pre2
                  )
                }
                if (definedContributionInputAmount2016post1.toInt > 0) {
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount2016post1
                  )
                }
                if (definedContributionInputAmount2016post2.toInt > 0) {
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount2016post2
                  )
                }
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  definedBenefitInputAmount2016pre
                )
                if (definedBenefitInputAmount2016post.toInt > 0) {
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedBenefitInputAmount2016post
                  )
                }
              }
              if (
                !(FlexiAccessDate2016 == "-1") && !(definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre1 == "0")
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  getPreAccessDefinedContributionInputAmount
                )
                PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  getPostAccessDefinedContributionInputAmount
                )
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(definedBenefitInputAmount2016pre)
              }
              if (
                (FlexiAccessDate2016 == "-1") && (definedBenefitInputAmount2016pre == "-1" && definedContributionInputAmount2016pre1 == "-1")
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue()
                TotalIncomePage.verifyPageEnterTotalIncomeAndContinue(totalIncome2016)
              }
            } else {
              if (!(taxYearSchemesList.size == 1)) {
                AddAnotherSchemePage.verifyPageSelectYesAndContinue()
              } else {
                if (
                  (FlexiAccessDate2016 == "-1") && (definedBenefitInputAmount2016pre.toInt > 0) && (definedContributionInputAmount2016pre1.toInt > 0)
                ) {

                  ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount2016pre1
                  )
                  if (definedContributionInputAmount2016pre2.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016pre2
                    )
                  }
                  if (definedContributionInputAmount2016pre2.toInt == 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016pre2
                    )
                  }
                  if (definedContributionInputAmount2016post1.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post1
                    )
                  }
                  if (definedContributionInputAmount2016post1.toInt == 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post1
                    )
                  }
                  if (definedContributionInputAmount2016post2.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post2
                    )
                  }
                  PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                    definedBenefitInputAmount2016pre
                  )
                  if (definedBenefitInputAmount2016post.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedBenefitInputAmount2016post
                    )
                  }
                  if (definedBenefitInputAmount2016post.toInt == 0) {
                    PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                      definedBenefitInputAmount2016post
                    )
                  }
                  if (definedContributionInputAmount2016post2.toInt == 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      "0"
                    )
                  }
                }
                if (
                  (FlexiAccessDate2016 == "-1") && (definedBenefitInputAmount2016pre.toInt == -1) && (definedContributionInputAmount2016pre1.toInt > 0)
                ) {

                  ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue()
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount2016pre1
                  )
                  if (definedContributionInputAmount2016pre2.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016pre2
                    )
                  }
                  if (definedContributionInputAmount2016post1.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post1
                    )
                  }
                  if (definedContributionInputAmount2016post1.toInt == 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post1
                    )
                  }
                  if (definedContributionInputAmount2016post2.toInt > 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post2
                    )
                  }
                  if (definedContributionInputAmount2016post2.toInt == 0) {
                    PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                      definedContributionInputAmount2016post2
                    )
                  }
                }
                if (
                  !(FlexiAccessDate2016 == "-1") && (definedBenefitInputAmount2016pre > "0") && (definedContributionInputAmount2016pre1 > "0")
                ) {
                  ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    getPreAccessDefinedContributionInputAmount
                  )
                  PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    getPostAccessDefinedContributionInputAmount
                  )
                  PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                    definedBenefitInputAmount2016pre
                  )
                }
                if (
                  (FlexiAccessDate2016 == "-1") && (definedBenefitInputAmount2016pre > "0") && (definedContributionInputAmount2016pre1 == "-1")
                ) {
                  ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue()
                  PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                    definedBenefitInputAmount2016pre
                  )
                  PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                    definedBenefitInputAmount2016post
                  )
                }
                if (
                  (FlexiAccessDate2016 == "-1") && (definedBenefitInputAmount2016pre == "-1") && (definedContributionInputAmount2016pre1 == "-1")
                ) {
                  ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue()
                }
                TotalIncomePage.verifyPageEnterTotalIncomeAndContinue(totalIncome2016)
              }
            }
          }
        }
        CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

        /** --- 2017 and above years --- */
        filteredTaxYearsOpt match {
          case Some(filteredTaxYears) =>
            filteredTaxYears.foreach { taxYear =>
              val year        = taxYear.period.toInt
              var taxPeriod   = ""
              var taxFromDate = ""
              var taxToDate   = ""
              var taxFromYear = ""
              var taxToYear   = ""

              year match {
                case 2017 =>
                  TaskListPage.clickAddDetailsFor2016To2017()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2017Page()
                  taxPeriod = "6 April 2016 to 5 April 2017"
                  taxFromDate = "6 April 2016"
                  taxToDate = "5 April 2017"
                  taxFromYear = "2016"
                  taxToYear = "2017"
                case 2018 =>
                  TaskListPage.clickAddDetailsFor2017To2018()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2018Page()
                  taxPeriod = "6 April 2017 to 5 April 2018"
                  taxFromDate = "6 April 2017"
                  taxToDate = "5 April 2018"
                  taxFromYear = "2017"
                  taxToYear = "2018"
                case 2019 =>
                  TaskListPage.clickAddDetailsFor2018To2019()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2019Page()
                  taxPeriod = "6 April 2018 to 5 April 2019"
                  taxFromDate = "6 April 2018"
                  taxToDate = "5 April 2019"
                  taxFromYear = "2018"
                  taxToYear = "2019"
                case 2020 =>
                  TaskListPage.clickAddDetailsFor2019To2020()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2020Page()
                  taxPeriod = "6 April 2019 to 5 April 2020"
                  taxFromDate = "6 April 2019"
                  taxToDate = "5 April 2020"
                  taxFromYear = "2019"
                  taxToYear = "2020"
                case 2021 =>
                  TaskListPage.clickAddDetailsFor2020To2021()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2021Page()
                  taxPeriod = "6 April 2020 to 5 April 2021"
                  taxFromDate = "6 April 2020"
                  taxToDate = "5 April 2021"
                  taxFromYear = "2020"
                  taxToYear = "2021"
                case 2022 =>
                  TaskListPage.clickAddDetailsFor2021To2022()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2022Page()
                  taxPeriod = "6 April 2021 to 5 April 2022"
                  taxFromDate = "6 April 2021"
                  taxToDate = "5 April 2022"
                  taxFromYear = "2021"
                  taxToYear = "2022"
                case 2023 =>
                  TaskListPage.clickAddDetailsFor2022To2023()
                  WhatYouWillNeedAaPage.onWhatYouWillNeedAa2023Page()
                  taxPeriod = "6 April 2022 to 5 April 2023"
                  taxFromDate = "6 April 2022"
                  taxToDate = "5 April 2023"
                  taxFromYear = "2022"
                  taxToYear = "2023"
                case _    =>
              }
              val noOfPensionSchemes             = taxYear.taxYearSchemes.get.size
              var chargePaidByMember             = taxYear.chargePaidByMember.getOrElse(0)
              val flexiAccessDate                = taxYear.flexiAccessDate.getOrElse("-1")
              val definedBenefitInputAmount      = taxYear.definedBenefitInputAmount.getOrElse(-1)
              val definedContributionInputAmount = taxYear.definedContributionInputAmount.getOrElse(-1)
              val totalIncome                    = taxYear.totalIncome.getOrElse("0")

              val preAccessDefinedContributionInputAmount  =
                taxYear.preAccessDefinedContributionInputAmount.getOrElse(-1)
              val postAccessDefinedContributionInputAmount =
                taxYear.postAccessDefinedContributionInputAmount.getOrElse(-1)
              var incomeAboveThreshold                     = false
              var adjustedIncome                           = 0
              taxYear.income.foreach { taxYearSchemesList =>
                adjustedIncome = taxYearSchemesList.adjustedIncome.getOrElse(0)
                incomeAboveThreshold = taxYearSchemesList.incomeAboveThreshold
              }

              if (noOfPensionSchemes > 1) {
                MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue()
              } else {
                MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue()
              }
              taxYear.taxYearSchemes.foreach { taxYearSchemesList =>
                for ((taxYearScheme, index) <- taxYearSchemesList.zipWithIndex) {
                  var paidByScheme: Int = taxYearScheme.chargePaidByScheme
                  WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue(
                    taxYearScheme.name,
                    taxYearScheme.pensionSchemeTaxReference
                  )
                  PensionSchemeInputAmountsPage.verifyPageEnterPensionAmountsAndContinue(
                    taxYearScheme.originalPensionInputAmount.toString,
                    taxYearScheme.revisedPensionInputAmount.toString
                  )
                  if (paidByScheme == 0 && chargePaidByMember == 0) {
                    DidYouPayAChargePage.verifyPageSelectNoAndContinue()
                  } else {
                    DidYouPayAChargePage.verifyPageSelectYesAndContinue()
                    if (paidByScheme > 0 && chargePaidByMember.toString.toInt > 0) {
                      WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue()
                      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(chargePaidByMember.toString)
                      chargePaidByMember = 0
                      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(paidByScheme.toString)
                      paidByScheme = 0
                    }
                    if (paidByScheme > 0 && chargePaidByMember == 0 && index < 1) {
                      WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue()
                      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(paidByScheme.toString)
                      paidByScheme = 0
                    }
                    if (paidByScheme > 0 && chargePaidByMember == 0 && index >= 1) {
                      HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(paidByScheme.toString)
                      paidByScheme = 0
                    }
                    if (paidByScheme == 0 && chargePaidByMember.toString.toInt > 0) {
                      WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue()
                      HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(chargePaidByMember.toString)
                      chargePaidByMember = 0
                    }
                  }
                  if ((taxYearSchemesList.size > 1) && (index == taxYearSchemesList.size - 1)) {
                    AddAnotherSchemePage.verifyPageSelectNoAndContinue()
                  } else {
                    if (!(taxYearSchemesList.size == 1)) {
                      AddAnotherSchemePage.verifyPageSelectYesAndContinue()
                    }
                  }
                }
              }
              if (
                (flexiAccessDate == "-1") && (definedBenefitInputAmount > 0) && (definedContributionInputAmount == -1)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue()
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  definedBenefitInputAmount.toString
                )
              }
              if (
                (flexiAccessDate == "-1") && (definedBenefitInputAmount > 0) && (definedContributionInputAmount == 0)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                if (
                  definedBenefitInputAmount.toString.toInt > 0 && definedContributionInputAmount.toString.toInt == 0
                ) {
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue()
                  PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                    definedBenefitInputAmount.toString
                  )
                }
                if (
                  definedBenefitInputAmount.toString.toInt == 0 && definedContributionInputAmount.toString.toInt > 0
                ) {
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue()
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount.toString
                  )
                }
                if (definedBenefitInputAmount.toString.toInt > 0 && definedContributionInputAmount.toString.toInt > 0) {
                  WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                  PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                    definedContributionInputAmount.toString
                  )
                  PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                    definedBenefitInputAmount.toString
                  )
                }
              }
              if (
                !(flexiAccessDate == "-1") && (preAccessDefinedContributionInputAmount > 0) && ((postAccessDefinedContributionInputAmount == 0) || (postAccessDefinedContributionInputAmount > 0)) && (definedBenefitInputAmount > 0)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  preAccessDefinedContributionInputAmount.toString
                )
                PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  postAccessDefinedContributionInputAmount.toString
                )
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  definedBenefitInputAmount.toString
                )
              }
              if (
                !(flexiAccessDate == "-1") && (definedBenefitInputAmount > 0) && (preAccessDefinedContributionInputAmount == -1)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue()
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  definedBenefitInputAmount.toString
                )
              }
              if (
                !(flexiAccessDate == "-1") && (definedBenefitInputAmount == -1) && (preAccessDefinedContributionInputAmount > 0)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue()
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  preAccessDefinedContributionInputAmount.toString
                )
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  postAccessDefinedContributionInputAmount.toString
                )
              }
              if (
                (flexiAccessDate == "-1") && (definedBenefitInputAmount == -1) && (definedContributionInputAmount == -1)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue()
              }
              if (
                (flexiAccessDate == "-1") && (definedBenefitInputAmount == -1) && (definedContributionInputAmount > 0)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue()
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  definedContributionInputAmount.toString
                )
              }
              if (
                (flexiAccessDate == "-1") && (definedBenefitInputAmount > 0) && (definedContributionInputAmount > 0)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue()
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue()
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  definedContributionInputAmount.toString
                )
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  definedBenefitInputAmount.toString
                )
              }
              if (
                !(flexiAccessDate == "-1") && (definedBenefitInputAmount == -1) && (definedContributionInputAmount == -1) && (preAccessDefinedContributionInputAmount == -1) && (postAccessDefinedContributionInputAmount == -1)
              ) {
                ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue()
              }
              if (incomeAboveThreshold.toString == "false") {
                if (taxToYear.toInt > 2020) {
                  ThresholdIncomePage.verify2021TO2023PageSelectNoAndContinue()
                } else {
                  ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue()
                }
              }
              if (!(incomeAboveThreshold.toString == "false")) {
                if (taxToYear.toInt > 2020) {
                  ThresholdIncomePage.verify2021TO2023PageSelectYesAndContinue()
                } else {
                  ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue()
                }
                AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue(adjustedIncome.toString)
              }
              TotalIncomePage.verifyPageEnterTotalIncomeAndContinue(totalIncome.toString)
              CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
            }
        }
        TaskListPage.clickCalculateButton()

        /** TaskList verification */
        val outDatesCompensation = responseDTOResult.asOpt.map(_.totalAmounts.outDatesCompensation).getOrElse(0)
        val inDatesDebit         = responseDTOResult.asOpt.map(_.totalAmounts.inDatesDebit).getOrElse(0)
        val inDatesCredit        = responseDTOResult.asOpt.map(_.totalAmounts.inDatesCredit).getOrElse(0)

        assert(CalculationResultPage.getTotCompensation() == outDatesCompensation)
        assert(CalculationResultPage.getIncreasedTaxCharges() == inDatesDebit)
        assert(CalculationResultPage.getDecreasedTaxCharges() == inDatesCredit)

        val responseTaxYears: Option[List[OutDate]] = responseDTOResult.asOpt.map(_.outDates)
        responseTaxYears match {
          case Some(filteredTaxYears) =>
            filteredTaxYears.foreach { taxYear =>
              val chargePaidBySchemes                 =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.chargePaidBySchemes, responseDTOResult)
                  .toString
              val chargePaidByMember                  =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.chargePaidByMember, responseDTOResult)
                  .toString
              val revisedChargableAmountAfterTaxRate  =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.revisedChargableAmountAfterTaxRate, responseDTOResult)
                  .toString
              val revisedChargableAmountBeforeTaxRate =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.revisedChargableAmountBeforeTaxRate, responseDTOResult)
                  .toString
              val directCompensation                  =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.directCompensation, responseDTOResult)
                  .toString
              val indirectCompensation                =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.indirectCompensation, responseDTOResult)
                  .toString
              val unusedAnnualAllowance               =
                myResponseObject
                  .getOutDatesInformation(taxYear.period, _.unusedAnnualAllowance, responseDTOResult)
                  .toString
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Previous annual allowance tax charge amount that you paid"
                  )
                  == chargePaidByMember.toInt,
                taxYear.period + " chargePaidByMember is different"
              )
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Previous annual allowance tax charge amount paid by your scheme"
                  )
                  == chargePaidBySchemes.toInt,
                taxYear.period + " chargePaidBySchemes is different"
              )
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Updated annual allowance tax charge amount"
                  )
                  == revisedChargableAmountAfterTaxRate.toInt,
                taxYear.period + " revisedChargableAmountAfterTaxRate is different"
              )
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Updated amount on which tax is due"
                  )
                  == revisedChargableAmountBeforeTaxRate.toInt,
                taxYear.period + " revisedChargableAmountBeforeTaxRate is different"
              )
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Amount of compensation that will be paid to you"
                  )
                  == directCompensation.toInt,
                taxYear.period + " directCompensation is different"
              )
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Amount of compensation that will be paid as an increase to your scheme benefits"
                  )
                  == indirectCompensation.toInt,
                taxYear.period + " indirectCompensation is different"
              )
              assert(
                CalculationResultPage
                  .getTaxYearInformation(
                    taxYear.period,
                    "Unused annual allowance"
                  )
                  == unusedAnnualAllowance.toInt,
                taxYear.period + " unusedAnnualAllowance is different"
              )
            }
        }
        signOutPage()
      }
    }
  }
}
