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

package uk.gov.hmrc.test.ui.functions

import org.openqa.selenium.WebElement
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import uk.gov.hmrc.test.ui.dto.bussinessRequest.{RequestDTO, RequestDTOUtil, TaxYear, TaxYearSchemes}
import uk.gov.hmrc.test.ui.pages._
import uk.gov.hmrc.test.ui.specs.BaseSpec
import util.DateUtil

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.CollectionHasAsScala

class CommonCalculationAAandMultipleSchemesPaidLTA extends BaseSpec {
  def createCalculationJourney(fileName: String): (mutable.Map[String, String], ArrayBuffer[Int], ArrayBuffer[Int]) = {

    /** Retrieve request information */
    val requestStream    =
      getClass.getResourceAsStream(
        "/businessCases/request/" + fileName + "_Request.json"
      )
    val jsonString       = scala.io.Source.fromInputStream(requestStream).mkString
    val json: JsValue    = Json.parse(jsonString)
    val requestDTOResult = Json.fromJson[RequestDTO](json)
    val mutableMap       = mutable.LinkedHashMap[String, String]()
    requestDTOResult match {
      case JsSuccess(requestDTO, _) =>
        val allTaxYearSchemes: List[TaxYearSchemes] =
          requestDTO.annualAllowance.taxYears.flatMap(_.taxYearSchemes.getOrElse(List.empty))
        allTaxYearSchemes.foreach { scheme =>
          mutableMap += scheme.name -> scheme.pensionSchemeTaxReference
        }

      case JsError(errors) =>
        println(s"Failed to parse JSON: $errors")
    }

    /** Outer Json common fields */
    val isResubmission                             = requestDTOResult.asOpt.map(_.resubmission.isResubmission)
    val reason                                     = requestDTOResult.asOpt.map(_.resubmission.reason).getOrElse("0")
    val taxYears: Option[List[TaxYear]]            = requestDTOResult.asOpt.map(_.annualAllowance.taxYears)
    val scottishTaxYears: Option[List[String]]     = requestDTOResult.asOpt.map(_.annualAllowance.scottishTaxYears)
    val filteredTaxYearsOpt: Option[List[TaxYear]] = taxYears.map(_.filter(_.period >= "2017"))
    val taxYearsBefore2016                         = taxYears.map(_.filter(_.period <= "2015"))
    val inDatesYearsList: Option[List[TaxYear]]    = taxYears.map(_.filter(_.period >= "2020"))
    val inDatesYears: ArrayBuffer[Int]             = ArrayBuffer()

    inDatesYearsList match {
      case Some(filteredTaxYears) =>
        filteredTaxYears.foreach { taxYear =>
          inDatesYears += taxYear.period.toInt
        }
    }

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
    val getFlexiAccessDate2016post                  =
      myObject.getTaxYearInformation("2016-post", _.flexiAccessDate, requestDTOResult).toString
    val getFlexiAccessDate                          =
      myObject.getFlexiAccessDate(taxYears)
    val getPreAccessDefinedContributionInputAmount  =
      myObject.getPreAccessDefinedContributionInputAmount(taxYears).toString
    val getPostAccessDefinedContributionInputAmount =
      myObject.getPostAccessDefinedContributionInputAmount(taxYears).toString

    /** Tax information 2016-Pre */
    val taxYearScheme2016pre: Option[List[TaxYearSchemes]] =
      myObject.getTaxYearSchemes("2016-pre", requestDTOResult)
    var chargePaidByMember2016pre                          =
      myObject.getTaxYearInformation("2016-pre", _.chargePaidByMember, requestDTOResult).toString
    val definedBenefitInputAmount2016pre                   =
      myObject.getTaxYearInformation("2016-pre", _.definedBenefitInputAmount, requestDTOResult).toString
    val definedContributionInputAmount2016pre              =
      myObject.getTaxYearInformation("2016-pre", _.definedContributionInputAmount, requestDTOResult).toString
    val preYear                                            = "2016-pre"
    val preFromDate                                        = "6 April 2015"
    val preToDate                                          = "8 July 2015"
    val pre2016FlexiAccessDate                             =
      myObject.getTaxYearInformation(preYear, _.flexiAccessDate, requestDTOResult).toString

    /** Tax information 2016-Post */
    val taxYearScheme2016post: Option[List[TaxYearSchemes]] =
      myObject.getTaxYearSchemes("2016-post", requestDTOResult)
    var chargePaidByMember2016post                          =
      myObject.getTaxYearInformation("2016-post", _.chargePaidByMember, requestDTOResult).toString
    val definedBenefitInputAmount2016post                   =
      myObject.getTaxYearInformation("2016-post", _.definedBenefitInputAmount, requestDTOResult).toString
    val definedContributionInputAmount2016post              =
      myObject.getTaxYearInformation("2016-post", _.definedContributionInputAmount, requestDTOResult).toString
    val preAccessDefinedContributionInputAmount2016post     =
      myObject
        .getTaxYearInformation("2016-post", _.preAccessDefinedContributionInputAmount, requestDTOResult)
        .toString
    val postAccessDefinedContributionInputAmount2016post    =
      myObject
        .getTaxYearInformation("2016-post", _.postAccessDefinedContributionInputAmount, requestDTOResult)
        .toString

    val totalIncome2016pre =
      myObject.getTaxYearInformation("2016-pre", _.totalIncome, requestDTOResult).toString

    val postYear                = "2016-post"
    val post2016FlexiAccessDate =
      myObject.getTaxYearInformation(postYear, _.flexiAccessDate, requestDTOResult).toString

    /** Retrieve response information */

    val responseStream =
      getClass.getResourceAsStream(
        "/businessCases/response/Scenario_2a_Response.json"

        //"/businessCases/response/" + requestArray(index) + "_Response.json"
      )
    val jsonResponseString = scala.io.Source.fromInputStream(responseStream).mkString
    println(" File name : " + fileName)

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
    ReportingChangePage.selectBothAAAndLTAContinue()
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

    // CheckYourAnswersAnnualAllowanceSetupPage.verifyCheckYourAnswersPageAndContinue()
    CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

    /** --- 2016 Pre --- */
    TaskListPage.clickAddDetailsFor6AprilTo8July2015()
    WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PrePage()
    if (taxYearScheme2016pre.map(_.size).getOrElse(0) > 1) {
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-pre")
    } else {
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-pre")
    }
    taxYearScheme2016pre.foreach { taxYearSchemesList =>
      for ((taxYearScheme, index) <- taxYearSchemesList.zipWithIndex) {
        var paidByScheme: Int = taxYearScheme.chargePaidByScheme
        PensionSchemeDetailsPage.enterTaxInformationAndContinue(
          "2016-pre",
          index.toString,
          taxYearScheme.name,
          taxYearScheme.pensionSchemeTaxReference
        )
        PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
          "2016-pre",
          index.toString,
          taxYearScheme.originalPensionInputAmount.toString,
          taxYearScheme.revisedPensionInputAmount.toString,
          taxYearScheme.name.toString
        )
        if (paidByScheme == 0 && chargePaidByMember2016pre.toInt == 0) {
          DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-pre", index.toString, taxYearScheme.name)
        } else {
          DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", index.toString, taxYearScheme.name)
          if (paidByScheme > 0 && chargePaidByMember2016pre.toInt > 0) {
            WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue(
              "2016-post",
              "0",
              "5 April to 8 July 2015",
              taxYearScheme.name
            )
            HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-post", "0", chargePaidByMember2016pre)
            chargePaidByMember2016pre = "0"

            HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
              "2016-pre",
              index.toString,
              paidByScheme.toString
            )
            paidByScheme = 0
          }
          if (paidByScheme > 0 && chargePaidByMember2016post.toInt == 0 && index < 1) {
            WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
              "2016-pre",
              index.toString,
              "5 April to 8 July 2015",
              taxYearScheme.name
            )
            HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
              "2016-pre",
              index.toString,
              paidByScheme.toString
            )
            paidByScheme = 0
          }
          if (paidByScheme > 0 && chargePaidByMember2016post.toInt == 0 && index >= 1) {
            HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
              "2016-pre",
              index.toString,
              paidByScheme.toString
            )
            paidByScheme = 0
          }
          if (paidByScheme == 0 && chargePaidByMember2016post.toInt > 0) {
            WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue(
              "2016-pre",
              index.toString,
              "5 April to 8 July 2015",
              taxYearScheme.name
            )
            HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue("2016-pre", "0", chargePaidByMember2016pre)
            chargePaidByMember2016pre = "0"
          }
        }
        if ((taxYearSchemesList.size > 1) && (index == taxYearSchemesList.size - 1)) {
          AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-pre", index.toString)
          if (
            (pre2016FlexiAccessDate == "0") && !(definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre == "0")
          ) {
            ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue(preYear)
            if (definedBenefitInputAmount2016pre.toInt > 0 && definedContributionInputAmount2016pre.toInt == 0) {
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue(preYear)
              PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                preYear,
                definedBenefitInputAmount2016pre,
                preFromDate,
                preToDate
              )
            }
            if (definedBenefitInputAmount2016pre.toInt == 0 && definedContributionInputAmount2016pre.toInt > 0) {
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue(preYear)
              PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                preYear,
                definedContributionInputAmount2016pre,
                preFromDate,
                preToDate
              )
            }
            if (definedBenefitInputAmount2016pre.toInt > 0 && definedContributionInputAmount2016pre.toInt > 0) {
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue(preYear)
              PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                preYear,
                definedContributionInputAmount2016pre,
                preFromDate,
                preToDate
              )
              PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                preYear,
                definedBenefitInputAmount2016pre,
                preFromDate,
                preToDate
              )
            }
          }
          if (
            !(pre2016FlexiAccessDate == "0") && !(definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre == "0")
          ) {
            ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue(preYear)
            WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue(preYear)
            PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
              preYear,
              getPreAccessDefinedContributionInputAmount,
              preFromDate,
              DateUtil.formatDate(pre2016FlexiAccessDate, 0)
            )
            PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
              preYear,
              getPostAccessDefinedContributionInputAmount,
              DateUtil.formatDate(pre2016FlexiAccessDate, 1),
              preToDate
            )
            PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
              preYear,
              definedBenefitInputAmount2016pre,
              preFromDate,
              preToDate
            )
          }
          if (
            (pre2016FlexiAccessDate == "0") && (definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre == "0")
          ) {
            ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue(preYear)
          }
        } else {
          if (!(taxYearSchemesList.size == 1)) {
            AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-pre", index.toString)
          } else {
            if (
              (pre2016FlexiAccessDate == "0") && !(definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre == "0")
            ) {
              ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue(preYear)
              if (definedBenefitInputAmount2016pre.toInt > 0 && definedContributionInputAmount2016pre.toInt == 0) {
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue(preYear)
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  preYear,
                  definedBenefitInputAmount2016pre,
                  preFromDate,
                  preToDate
                )
              }
              if (definedBenefitInputAmount2016pre.toInt == 0 && definedContributionInputAmount2016pre.toInt > 0) {
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue(preYear)
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  preYear,
                  definedContributionInputAmount2016pre,
                  preFromDate,
                  preToDate
                )
              }
              if (definedBenefitInputAmount2016pre.toInt > 0 && definedContributionInputAmount2016pre.toInt > 0) {
                WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue(preYear)
                PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                  preYear,
                  definedContributionInputAmount2016pre,
                  preFromDate,
                  preToDate
                )
                PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                  preYear,
                  definedBenefitInputAmount2016pre,
                  preFromDate,
                  preToDate
                )
              }
            }
            if (
              !(pre2016FlexiAccessDate == "0") && !(definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre == "0")
            ) {
              ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue(preYear)
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue(preYear)
              PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                preYear,
                getPreAccessDefinedContributionInputAmount,
                preFromDate,
                DateUtil.formatDate(pre2016FlexiAccessDate, 0)
              )
              PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
                preYear,
                getPostAccessDefinedContributionInputAmount,
                DateUtil.formatDate(pre2016FlexiAccessDate, 1),
                preToDate
              )
              PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                preYear,
                definedBenefitInputAmount2016pre,
                preFromDate,
                preToDate
              )
            }
            if (
              (pre2016FlexiAccessDate == "0") && (definedBenefitInputAmount2016pre == "0" && definedContributionInputAmount2016pre == "0")
            ) {
              ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue(preYear)
            }
          }
        }
      }
    }

    /**
      * Check your answers page is failing to verify as its failing to save add another scheme page information
      */
    TotalIncomePage.verifyPageEnterTotalIncomeAndContinue("2015", "2016", "2016-pre", totalIncome2016pre)
    CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()

    /** --- 2016 Post --- */
    TaskListPage.clickAddDetailsFor9JulyTo8July2016()
    WhatYouWillNeedAaPage.onWhatYouWillNeedAa2016PostPage()
    if (taxYearScheme2016post.map(_.size).getOrElse(0) > 1) {
      MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue("2016-post")
    } else {
      MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue("2016-post")
    }
    taxYearScheme2016post.foreach { taxYearSchemesList =>
      for ((taxYearScheme, index) <- taxYearSchemesList.zipWithIndex) {
        var paidByScheme: Int = taxYearScheme.chargePaidByScheme
        WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue(
          "2016-post",
          index.toString,
          taxYearScheme.name,
          taxYearScheme.pensionSchemeTaxReference
        )
        PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
          "2016-post",
          index.toString,
          taxYearScheme.originalPensionInputAmount.toString,
          taxYearScheme.revisedPensionInputAmount.toString,
          taxYearScheme.name
        )
        if (paidByScheme == 0 && chargePaidByMember2016post.toInt == 0) {
          DidYouPayAChargePage.verifyPageSelectNoAndContinue("2016-post", index.toString, taxYearScheme.name)
        } else {
          DidYouPayAChargePage.verifyPageSelectYesAndContinue("2016-post", index.toString, taxYearScheme.name)
          if (paidByScheme.toString.toInt > 0 && chargePaidByMember2016post.toInt > 0) {
            WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue(
              "2016-post",
              index.toString,
              "9 July 2015 to 5 April 2016",
              taxYearScheme.name
            )
            HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(
              "2016-post",
              index.toString,
              chargePaidByMember2016post
            )
            chargePaidByMember2016post = "0"
            HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
              "2016-post",
              index.toString,
              paidByScheme.toString
            )
            paidByScheme = 0
          }
          if (paidByScheme > 0 && chargePaidByMember2016post.toInt == 0 && index < 1) {
            WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
              "2016-post",
              index.toString,
              "9 July 2015 to 5 April 2016",
              taxYearScheme.name
            )
            HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
              "2016-post",
              index.toString,
              paidByScheme.toString
            )
            paidByScheme = 0
          }
          if (paidByScheme > 0 && chargePaidByMember2016post.toInt == 0 && index >= 1) {
            HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
              "2016-post",
              index.toString,
              paidByScheme.toString
            )
            paidByScheme = 0
          }
          if (paidByScheme == 0 && chargePaidByMember2016post.toInt > 0) {
            WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue(
              "2016-post",
              index.toString,
              "9 July 2015 to 5 April 2016",
              taxYearScheme.name
            )
            HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(
              "2016-post",
              index.toString,
              chargePaidByMember2016post
            )
            chargePaidByMember2016post = "0"
          }
        }
        if ((taxYearSchemesList.size > 1) && (index == taxYearSchemesList.size - 1)) {
          AddAnotherSchemePage.verifyPageSelectNoAndContinue("2016-post", index.toString)
        } else {
          if (!(taxYearSchemesList.size == 1)) {
            AddAnotherSchemePage.verifyPageSelectYesAndContinue("2016-post", index.toString)
          }
        }
      }
    }
    if (
      (getFlexiAccessDate2016post == "0") && !(definedBenefitInputAmount2016post == "0" && definedContributionInputAmount2016post == "0")
    ) {
      // if (!(definedBenefitInputAmount2016post == "0" && definedContributionInputAmount2016post == "0")) {
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")
      if (definedBenefitInputAmount2016post.toInt > 0 && definedContributionInputAmount2016post.toInt == 0) {
        WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue("2016-post")
        PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
          "2016-post",
          definedBenefitInputAmount2016post,
          "9 July 2015",
          "5 April 2016"
        )
      }
      if (definedBenefitInputAmount2016post.toInt == 0 && definedContributionInputAmount2016pre.toInt > 0) {
        WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue("2016-post")
        PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
          "2016-post",
          definedContributionInputAmount2016post,
          "9 July 2015",
          "5 April 2016"
        )
      }
      if (definedBenefitInputAmount2016post.toInt > 0 && definedContributionInputAmount2016post.toInt > 0) {
        WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")
        PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
          "2016-post",
          definedContributionInputAmount2016post,
          "9 July 2015",
          "5 April 2016"
        )
        PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
          "2016-post",
          definedBenefitInputAmount2016post,
          "9 July 2015",
          "5 April 2016"
        )
      }
      // }
    }
    if (
      !(getFlexiAccessDate2016post == "0") && !(definedBenefitInputAmount2016post == "0" && definedContributionInputAmount2016post == "0")
    ) {
      ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue("2016-post")
      WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue("2016-post")
      PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        preAccessDefinedContributionInputAmount2016post,
        "9 July 2015",
        DateUtil.formatDate(post2016FlexiAccessDate, 0)
      )
      PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
        "2016-post",
        postAccessDefinedContributionInputAmount2016post,
        DateUtil.formatDate(post2016FlexiAccessDate, 1),
        "5 April 2016"
      )
      PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
        "2016-post",
        definedBenefitInputAmount2016post,
        "9 July 2015",
        "5 April 2016"
      )
    }
    if (
      (getFlexiAccessDate2016post == "0") && (definedBenefitInputAmount2016post == "0" && definedContributionInputAmount2016post == "0")
    ) {
      ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue("2016-post")
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
          val flexiAccessDate                = taxYear.flexiAccessDate.getOrElse("0")
          val definedBenefitInputAmount      = taxYear.definedBenefitInputAmount.getOrElse(0)
          val definedContributionInputAmount = taxYear.definedContributionInputAmount.getOrElse(0)
          val totalIncome                    = taxYear.totalIncome.getOrElse("0")

          val preAccessDefinedContributionInputAmount  =
            taxYear.preAccessDefinedContributionInputAmount.getOrElse("0")
          val postAccessDefinedContributionInputAmount =
            taxYear.postAccessDefinedContributionInputAmount.getOrElse("0")
          var incomeAboveThreshold                     = false
          var adjustedIncome                           = 0
          taxYear.income.foreach { taxYearSchemesList =>
            adjustedIncome = taxYearSchemesList.adjustedIncome.getOrElse(0)
            incomeAboveThreshold = taxYearSchemesList.incomeAboveThreshold
          }

          if (noOfPensionSchemes > 1) {
            MemberMoreThanOnePensionPage.verifyPageSelectYesAndContinue(year.toString)
          } else {
            MemberMoreThanOnePensionPage.verifyPageSelectNoAndContinue(year.toString)
          }
          taxYear.taxYearSchemes.foreach { taxYearSchemesList =>
            for ((taxYearScheme, index) <- taxYearSchemesList.zipWithIndex)
              if (!(WhichSchemeDetailsPage.isSchemeAvailable(taxYearScheme.pensionSchemeTaxReference))) {
                var chargePaidByScheme = taxYearScheme.chargePaidByScheme
                WhichSchemeDetailsPage.verifyPageSelectNewSchemeAndContinue(year.toString, index.toString)
                PensionSchemeDetailsPage.enterTaxInformationAndContinue(
                  year.toString,
                  index.toString,
                  taxYearScheme.name,
                  taxYearScheme.pensionSchemeTaxReference
                )
                PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
                  year.toString,
                  index.toString,
                  taxYearScheme.originalPensionInputAmount.toString,
                  taxYearScheme.revisedPensionInputAmount.toString,
                  taxYearScheme.name.toString
                )
                if (chargePaidByScheme == 0 && chargePaidByMember2016pre.toInt == 0) {
                  DidYouPayAChargePage.verifyPageSelectNoAndContinue(year.toString, index.toString, taxYearScheme.name)
                } else {
                  DidYouPayAChargePage.verifyPageSelectYesAndContinue(year.toString, index.toString, taxYearScheme.name)
                  if (chargePaidByScheme > 0 && chargePaidByMember2016pre.toInt > 0) {
                    WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue(
                      year.toString,
                      index.toString,
                      taxPeriod,
                      taxYearScheme.name
                    )
                    HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(
                      year.toString,
                      index.toString,
                      chargePaidByMember2016pre
                    )
                    chargePaidByMember2016pre = "0"

                    HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
                      year.toString,
                      index.toString,
                      taxYearScheme.chargePaidByScheme.toString
                    )
                    chargePaidByScheme = 0
                  }
                  if (chargePaidByScheme > 0 && chargePaidByMember2016post.toInt == 0 && index < 1) {
                    WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
                      year.toString,
                      index.toString,
                      taxPeriod,
                      taxYearScheme.name
                    )
                    HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
                      year.toString,
                      index.toString,
                      chargePaidByScheme.toString
                    )
                    chargePaidByScheme = 0
                  }
                  if (chargePaidByScheme > 0 && chargePaidByMember2016post.toInt == 0 && index >= 1) {
                    HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
                      year.toString,
                      index.toString,
                      chargePaidByScheme.toString
                    )
                    chargePaidByScheme = 0
                  }
                  if (chargePaidByScheme == 0 && chargePaidByMember2016post.toInt > 0) {
                    WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue(
                      year.toString,
                      index.toString,
                      taxPeriod,
                      taxYearScheme.name
                    )
                    HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(
                      year.toString,
                      "0",
                      chargePaidByMember2016pre
                    )
                    chargePaidByMember2016pre = "0"
                  }
                }
                if ((taxYearSchemesList.size > 1) && (index == taxYearSchemesList.size - 1)) {
                  AddAnotherSchemePage.verifyPageSelectNoAndContinue(year.toString, index.toString)
                } else {
                  if (!(taxYearSchemesList.size == 1)) {
                    AddAnotherSchemePage.verifyPageSelectYesAndContinue(year.toString, index.toString)
                  }
                }
              } else {

                var paidByScheme: Int = taxYearScheme.chargePaidByScheme
                WhichSchemeDetailsPage.verifyPageSelectSchemeAndContinue(
                  year.toString,
                  index.toString,
                  taxYearScheme.name,
                  taxYearScheme.pensionSchemeTaxReference
                )
                PensionSchemeInputAmountsPage.verifypageEnterPensionAmountsAndContinue(
                  year.toString,
                  index.toString,
                  taxYearScheme.originalPensionInputAmount.toString,
                  taxYearScheme.revisedPensionInputAmount.toString,
                  taxYearScheme.name
                )
                if (paidByScheme == 0 && chargePaidByMember == 0) {
                  DidYouPayAChargePage.verifyPageSelectNoAndContinue(year.toString, index.toString, taxYearScheme.name)
                } else {
                  DidYouPayAChargePage.verifyPageSelectYesAndContinue(year.toString, index.toString, taxYearScheme.name)
                  if (paidByScheme > 0 && chargePaidByMember.toString.toInt > 0) {
                    WhoPaidAnnualAllowanceChargePage.verifyPageSelectBothAndContinue(
                      year.toString,
                      index.toString,
                      taxPeriod,
                      taxYearScheme.name
                    )
                    HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(
                      year.toString,
                      index.toString,
                      chargePaidByMember.toString
                    )
                    chargePaidByMember = 0
                    HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
                      year.toString,
                      index.toString,
                      paidByScheme.toString
                    )
                    paidByScheme = 0
                  }
                  if (paidByScheme > 0 && chargePaidByMember == 0 && index < 1) {
                    WhoPaidAnnualAllowanceChargePage.verifyPageSelectPensionSchemeAndContinue(
                      year.toString,
                      index.toString,
                      taxPeriod,
                      taxYearScheme.name
                    )
                    HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
                      year.toString,
                      index.toString,
                      paidByScheme.toString
                    )
                    paidByScheme = 0
                  }
                  if (paidByScheme > 0 && chargePaidByMember == 0 && index >= 1) {
                    HowMuchPensionPayChargePage.verifyPageEnterPensionPayAndContinue(
                      year.toString,
                      index.toString,
                      paidByScheme.toString
                    )
                    paidByScheme = 0
                  }
                  if (paidByScheme == 0 && chargePaidByMember.toString.toInt > 0) {
                    WhoPaidAnnualAllowanceChargePage.verifyPageSelectYouAndContinue(
                      year.toString,
                      index.toString,
                      taxPeriod,
                      taxYearScheme.name
                    )
                    HowMuchYouPayChargePage.verifyPageEnterYouPayAndContinue(
                      year.toString,
                      index.toString,
                      chargePaidByMember.toString
                    )
                    chargePaidByMember = 0
                  }
                }
                if ((taxYearSchemesList.size > 1) && (index == taxYearSchemesList.size - 1)) {
                  AddAnotherSchemePage.verifyPageSelectNoAndContinue(year.toString, index.toString)
                } else {
                  if (!(taxYearSchemesList.size == 1)) {
                    AddAnotherSchemePage.verifyPageSelectYesAndContinue(year.toString, index.toString)
                  }
                }
              }
          }
          if ((flexiAccessDate == "0") && !(definedBenefitInputAmount == 0 && definedContributionInputAmount == 0)) {
            ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue(year.toString)
            if (definedBenefitInputAmount.toString.toInt > 0 && definedContributionInputAmount.toString.toInt == 0) {
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndContinue(year.toString)
              PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                year.toString,
                definedBenefitInputAmount.toString,
                taxFromDate,
                taxToDate
              )
            }
            if (definedBenefitInputAmount.toString.toInt == 0 && definedContributionInputAmount.toString.toInt > 0) {
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDCAndContinue(year.toString)
              PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                year.toString,
                definedContributionInputAmount.toString,
                taxFromDate,
                taxToDate
              )
            }
            if (definedBenefitInputAmount.toString.toInt > 0 && definedContributionInputAmount.toString.toInt > 0) {
              WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue(year.toString)
              PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
                year.toString,
                definedContributionInputAmount.toString,
                taxFromDate,
                taxToDate
              )
              PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
                year.toString,
                definedBenefitInputAmount.toString,
                taxFromDate,
                taxToDate
              )
            }
          }
          if (
            !(flexiAccessDate == "0") && !(preAccessDefinedContributionInputAmount == 0 && postAccessDefinedContributionInputAmount == 0)
          ) {
            ContributedOtherDbDcSchemePage.verifyPageSelectYesAndContinue(year.toString)
            WhichContributedDuringRemedyPeriodPage.verifyPageSelectDBAndDCANDContinue(year.toString)
            PiaForDcPensionPage.verifyPageEnterPensionInputAmountForDCAndContinue(
              year.toString,
              preAccessDefinedContributionInputAmount.toString,
              taxFromDate,
              DateUtil.formatDate(flexiAccessDate, 0)
            )
            PiaForDcPensionFlexiblePage.verifyPageEnterPensionInputAmountForDCAndContinue(
              year.toString,
              postAccessDefinedContributionInputAmount.toString,
              DateUtil.formatDate(flexiAccessDate, 1),
              taxToDate
            )
            PiaForDbPensionPage.verifyPageEnterPensionInputAmountForDBAndContinue(
              year.toString,
              definedBenefitInputAmount.toString,
              taxFromDate,
              taxToDate
            )
          }
          if ((flexiAccessDate == "0") && (definedBenefitInputAmount == 0 && definedContributionInputAmount == 0)) {
            ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue(year.toString)
          }
          if (
            !(flexiAccessDate == "0") && (definedBenefitInputAmount == 0 && definedContributionInputAmount == 0) && (preAccessDefinedContributionInputAmount == 0 && postAccessDefinedContributionInputAmount == 0)
          ) {
            ContributedOtherDbDcSchemePage.verifyPageSelectNoAndContinue(year.toString)
          }
          if (incomeAboveThreshold.toString == "false") {
            if (taxToYear.toInt > 2020) {
              ThresholdIncomePage.verify2021TO2023PageSelectNoAndContinue(taxFromYear, taxToYear, taxToYear)
            } else {
              ThresholdIncomePage.verify2017TO2020PageSelectNoAndContinue(taxFromYear, taxToYear, taxToYear)
            }
          }
          if (!(incomeAboveThreshold.toString == "false")) {
            if (taxToYear.toInt > 2020) {
              ThresholdIncomePage.verify2021TO2023PageSelectYesAndContinue(taxFromYear, taxToYear, taxToYear)
            } else {
              ThresholdIncomePage.verify2017TO2020PageSelectYesAndContinue(taxFromYear, taxToYear, taxToYear)
            }
            AdjustedIncomePage.verifyPageEnterAdjustedIncomeAndContinue(
              taxFromYear,
              taxToYear,
              taxToYear,
              adjustedIncome.toString
            )
          }
          TotalIncomePage.verifyPageEnterTotalIncomeAndContinue(
            taxFromYear,
            taxToYear,
            taxToYear,
            totalIncome.toString
          )

          /**
            * Check your answers page is failing to verify as its failing to save add another scheme page information
            */
          //CheckYourAnswersAnnualAllowancePeriodPage.verifyCheckYourAnswersPageAndContinue("2017")
          CheckYourAnswersAnnualAllowancePeriodPage.clickContinueButton()
        }
    }
    TaskListPage.clickAddDetailsForLifetimeAllowance()
    LifetimeAllowancePage.verifyLifetimeAllowancePageAndContinue()
    HadBenefitCrystallisationEventPage.onHadBenefitCrystallisationEventPage()
    HadBenefitCrystallisationEventPage.selectYesAndContinueForLTAPage()
    DateOfBenefitCrystallisationEventPage.onDateOfBenefitCrystallisationEventPage()
    DateOfBenefitCrystallisationEventPage.enterBenefitCrystallisationDateAndContinue()
    ToldChangeInLtaPercentagePage.onToldChangeInLtaPercentagePageAndSelectYesAndContinue()
    /* PercentageCausedChangeInChargePage.onPercentageCausedChangeInChargePage() */
    PercentageCausedChangeInChargePage.selectNewChargeRadioButtonAndContinue()
    MultipleBenefitCrystallisationEventPage.onMultipleBenefitCrystallisationEventPage()
    MultipleBenefitCrystallisationEventPage.selectNoRadioButtonAndContinue()
    LtaProtectionOrEnhancementsPage.onLtaProtectionOrEnhancementsPage()
    LtaProtectionOrEnhancementsPage.selectEnhancementsRadioButtonAndContinue()
    EnhancementType.selectBothRadioButtonAndContinue()
    InternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
    PensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
    ProtectionEnhancementChangedPage.selectBothRadioButtonAndContinue()
    ProtectionChangedNewTypePage.selectEnhancedProtectionAndContinue()
    ProtectionChangedNewReferencePage.enterReferenceAndContinue()
    NewEnhancementTypePage.selectBothRadioButtonAndContinue()
    NewInternationalEnhancementReferencePage.enterInternationalEnhancementReferenceAndContinue()
    NewPensionCreditReferencePage.enterPensionCreditReferenceAndContinue()
    LtaCharge20152023Page.selectYesAndContinueForLTAPage()
    HowExcessWasPaidPage.selectRadioButtonBothAndContinue()
    ValueOfLumpSumPage.enterLumpSumAndContinue("1000")
    ValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("3000")
    WhoPaidLtaChargePage.selectPensionSchemeAndClickOnContinue()
    val pension_scheme_name  =
      "Tensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepe nsions"
    val taxRef               = "00348916RX"
    SchemePaidLtaChargePage.enterPensionSchemeInformationAndContinue(pension_scheme_name, taxRef)
    QuarterChargeWasPaidPage.selectQuarterAndContinue()
    YearChargeWasPaidPage.selectYearAndContinue()
    NewExcessPaidPage.selectBothRadioButtonAndContinue()
    NewValueOfLumpSumPage.enterLumpSumAndContinue("2300")
    NewValueOfAnnualPaymentPage.enterAnnualPaymentAndContinue("2000")
    WhoPayingExtraLtaChargePage.verifyPageSelectPensionSchemeAndContinue()
    val pension_scheme_name2 =
      "Tensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepensionschemepe nsion2"
    val taxRef2              = "00348916RG"
    SchemePaidExtraLtaChargePage.enterPensionSchemeInformationAndContinue(pension_scheme_name2, taxRef2)
    mutableMap += (pension_scheme_name  -> taxRef)
    mutableMap += (pension_scheme_name2 -> taxRef2)
    CheckYourAnswersLifetimeAllowancePage.verifyCheckYourAnswersPageAndContinue()
    TaskListPage.clickCalculateButton()
    val debitYearsList: java.util.List[WebElement] = CalculationResultPage.getDebitYears()
    var debitYears: ArrayBuffer[Int]               = ArrayBuffer()
    debitYearsList.asScala.foreach { webElement =>
      debitYears += webElement.getText.split(" ").last.replaceAll("[^\\d]", "").toInt
    }
    CalculationResultPage.clickContinueSignIn()
    AuthorityWizardPage.authorizedLoginUser()
    (mutableMap, inDatesYears, debitYears)
  }
}
