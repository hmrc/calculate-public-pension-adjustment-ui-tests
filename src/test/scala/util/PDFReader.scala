/*
 * Copyright 2024 HM Revenue & Customs
 *
 */

package util

import com.typesafe.scalalogging.LazyLogging
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import java.nio.file.{FileSystem, FileSystems, Files}

object PDFReader extends LazyLogging {
  val fileSystem: FileSystem            = FileSystems.getDefault()
  val downloadsDir                      = fileSystem.getPath(System.getProperty("user.home"), "Downloads")
  val downloadsDirExists                = Files.exists(downloadsDir)
  def deletePDF(fileName: String): Unit =
    if (downloadsDirExists) {
      val pdfFile = downloadsDir.resolve(fileName + ".pdf").toFile()
      pdfFile.delete()
      logger.info(fileName + " deleted.")
    } else logger.info(fileName + " deletion failed.")

  def isPDFContains(fileName: String, text: String): Boolean =
    if (downloadsDirExists) {
      val pdfFile         = downloadsDir.resolve(fileName + ".pdf").toFile()
      val pdfDocument     = PDDocument.load(pdfFile)
      val pdfTextStripper = new PDFTextStripper()
      val pdfText         = pdfTextStripper.getText(pdfDocument)
      pdfText.contains(text)
    } else {
      false
    }
}
