import sbt._

object Dependencies {

  val test = Seq(
    "com.typesafe"         % "config"            % "1.4.2"    % Test,
    "com.vladsch.flexmark" % "flexmark-all"      % "0.62.2"   % Test,
    "org.scalatest"       %% "scalatest"         % "3.2.13"   % Test,
    "org.scalatestplus"   %% "selenium-4-2"      % "3.2.13.0" % Test,
    "uk.gov.hmrc"         %% "webdriver-factory" % "0.41.0"   % Test
  )

}
