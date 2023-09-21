package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By

object MultipleBenefitCrystallisationEventPage extends BasePage {

  def onMultipleBenefitCrystallisationEventPage() =
    verifyPageUrl("lifetime-allowance/more-than-one-benefit-crystallisation-event")
  // TODO Page content validations to be added.

  def selectNoRadioButtonAndContinue(): Unit = {
    val text = "No"
    driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).click()
    checkYourAnswersLASMap(getHeader(), text)
    submitPage()
  }
}
