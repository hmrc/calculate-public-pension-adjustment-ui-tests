#!/bin/bash -e

# Safari
sbt -Dbrowser="browserstack" -Denvironment=local -DtestDataSet="testSet1" 'testOnly uk.gov.hmrc.test.ui.specs.* -- -n BSTests'
sbt -Dbrowser="browserstack" -Denvironment=local -DtestDataSet="testSet2" 'testOnly uk.gov.hmrc.test.ui.specs.* -- -n BSTests'
sbt -Dbrowser="browserstack" -Denvironment=local -DtestDataSet="testSet3" 'testOnly uk.gov.hmrc.test.ui.specs.* -- -n BSTests'
sbt -Dbrowser="browserstackMobileWeb" -Denvironment=local -DtestDataSet="testSet4" 'testOnly uk.gov.hmrc.test.ui.specs.* -- -n BSTests'