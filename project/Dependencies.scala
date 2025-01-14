import sbt._

object Dependencies {

  val test = Seq(
    "ch.qos.logback"       % "logback-classic" % "1.5.1"    % Test,
    "com.typesafe"         % "config"          % "1.4.2"    % Test,
    "com.vladsch.flexmark" % "flexmark-all"    % "0.64.8"   % Test,
    "org.scalatest"       %% "scalatest"       % "3.2.18"   % Test,
    "org.scalatestplus"   %% "selenium-4-12"   % "3.2.17.0" % Test,
    "uk.gov.hmrc"         %% "ui-test-runner"  % "0.43.0"   % Test,
    "com.typesafe.play"   %% "play-json"       % "2.10.0-RC8"
  )

}
