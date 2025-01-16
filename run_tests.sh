#!/bin/bash -e

BROWSER=$1
ENVIRONMENT=$2

sbt scalafmtCheckAll scalafmtSbtCheck
sbt clean -Dbrowser="${BROWSER:=chrome}" -Denvironment="${ENVIRONMENT:=local}" -Dbrowser.logging=true -Daccessibility.timeout="1000" -Dbrowser.option.headless=true "testOnly uk.gov.hmrc.test.ui.specs.*" testReport