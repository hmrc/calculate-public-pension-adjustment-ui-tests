package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

object BreakdownPage extends BasePage {

  def goToYearBreakdown(year : Int) : Boolean ={
    try {

      val dateString = s"5 April $year"

      val breakdownLink = driver.findElement(By.xpath(s"//*[contains(text(),'$dateString')]/../td/a[contains(text(),'View breakdown')]"))
      breakdownLink.click()
      fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"main-content\"]/div/div/h2[2]")))  // maybe update this xpath?
      true
    }
    catch{
      case _: NoSuchElementException =>
        println(s"Breakdown link for year $year not found")
        false
    }
  }

  def returnToMainpage(): Unit = {

    try {
      val backlink = driver.findElement(By.linkText("Back")).click() //
      fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Calculation results')]")))
    }
    catch {
      case _: NoSuchElementException =>
        throw new RuntimeException("Cannot return to the main page.")
    }
  }

}
