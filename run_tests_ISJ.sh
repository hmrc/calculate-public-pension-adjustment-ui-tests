#!/bin/bash -e

BROWSER=$1
ENVIRONMENT=$2

sbt scalafmtCheckAll scalafmtSbtCheck
sbt clean -Dbrowser="${BROWSER:=chrome}" -Denvironment="${ENVIRONMENT:=local}" "testOnly uk.gov.hmrc.test.ui.specs.* -- -n ISJJourney" testReport