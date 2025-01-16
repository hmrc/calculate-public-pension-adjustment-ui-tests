#!/bin/bash -e

BROWSER=$1
ENVIRONMENT=$2

sbt scalafmtCheckAll scalafmtSbtCheck
sbt clean -Dbrowser="${BROWSER:=chrome}" -Denvironment="${ENVIRONMENT:=local}" -Daccessibility.assessment=false -Dbrowser.logging=true -Dbrowser.option.headless=true "testOnly uk.gov.hmrc.test.ui.specs.*" testReport