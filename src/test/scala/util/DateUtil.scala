package util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {
  def formatDate(inputDate: String, daysToAdd: Int): String = {
    val formatter       = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date            = LocalDate.parse(inputDate, formatter)
    val modifiedDate    = date.plusDays(daysToAdd)
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
    modifiedDate.format(outputFormatter)
  }

}
