**This is a template README.md.  Be sure to update this with project specific content that describes your ui test project.**

# calculate-public-pension-adjustment-ui-tests
UI test suite for the `<digital service name>` using WebDriver and `<scalatest/cucumber>`.  

## Running the tests

Prior to executing the tests ensure you have:
 - Docker - to run mongo and browser (Chrome, Firefox or Edge) inside a container 
 - Appropriate [drivers installed](#installing-local-driver-binaries) - to run tests against locally installed Browser
 - Installed/configured [service manager](https://github.com/hmrc/service-manager).  

Run the following command to start services locally:

    sm2 --start PUBLIC_PENSION_ADJUSTMENT_ALL -r --wait 100

Using the `--wait 100` argument ensures a health check is run on all the services started as part of the profile. `100` refers to the given number of seconds to wait for services to pass health checks.

Then execute the `run_tests.sh` script:

    ./run_tests.sh <browser-driver> <environment> 

The `run_tests.sh` script defaults to using `chrome` in the `local` environment.  For a complete list of supported param values, see:
 - `src/test/resources/application.conf` for **environment** 
 - [webdriver-factory](https://github.com/hmrc/webdriver-factory#2-instantiating-a-browser-with-default-options) for **browser-driver**

## Running tests against a containerised browser - on a developer machine

The script `./run_browser_with_docker.sh` can be used to start a Chrome, Firefox or Edge container on a developer machine. 
The script requires `remote-chrome`, `remote-firefox` or `remote-edge` as an argument.

Read more about the script's functionality [here](run_browser_with_docker.sh).

To run against a containerised Chrome browser:

```bash
./run_browser_with_docker.sh remote-chrome 
./run_tests.sh remote-chrome local
```

`./run_browser_with_docker.sh` is **NOT** required when running in a CI environment. 

> :warning: **SM2 **: If you use [SM2](https://github.com/hmrc/sm2) rather than [service manager](https://github.com/hmrc/service-manager) please note that this is **NOT** currently supported in build Jenkins.

#### Running the tests against a test environment

To run the tests against an environment set the corresponding `host` environment property as specified under
 `<env>.host.services` in the [application.conf](/src/test/resources/application.conf). 

For example, to execute the `run_tests.sh` script using Chrome remote-webdriver against QA environment 

    ./run_tests.sh remote-chrome qa

## Running ZAP tests

ZAP tests can be automated using the HMRC Dynamic Application Security Testing approach. Running 
automated ZAP tests should not be considered a substitute for manual exploratory testing using OWASP ZAP.

#### Tagging tests for ZAP

It is not required to proxy every journey test via ZAP. The intention of proxying a test through ZAP is to expose all the
 relevant pages of an application to ZAP. So tagging a subset of the journey tests or creating a 
 single ZAP focused journey test is sufficient.

#### Configuring the browser to proxy via ZAP 

Setting the system property `zap.proxy=true` configures the browser specified in `browser` property to proxy via ZAP. 
This is achieved using [webdriver-factory](https://github.com/hmrc/webdriver-factory#proxying-trafic-via-zap).

#### Executing a ZAP test

The shell script `run_zap_tests.sh` is available to execute ZAP tests. The script proxies a set of journey tests, 
tagged as `ZapTests`, via ZAP.  

For example, to execute ZAP tests locally using a Chrome browser

```
./run_zap_test.sh chrome local
```

To execute ZAP tests locally using a remote-chrome browser

```
./run_browser_with_docker.sh remote-chrome 
./run_zap_test.sh remote-chrome local
``` 

`./run_browser_with_docker.sh` is **NOT** required when running in a CI environment.

### Running tests using BrowserStack
If you would like to run your tests via BrowserStack from your local development environment please refer to the [webdriver-factory](https://github.com/hmrc/webdriver-factory/blob/main/README.md/#user-content-running-tests-using-browser-stack) project.

## Installing local driver binaries

This project supports UI test execution using Firefox (Geckodriver) and Chrome (Chromedriver) browsers. 

See the `drivers/` directory for some helpful scripts to do the installation work for you.  They should work on both Mac and Linux by running the following command:

    ./installGeckodriver.sh <operating-system> <driver-version>
    or
    ./installChromedriver <operating-system> <driver-version>

- *<operating-system>* defaults to **linux64**, however it also supports **macos**
- *<driver-version>* defaults to **0.21.0** for Gecko/Firefox, and the latest release for Chrome.  You can, however, however pass any version available at the [Geckodriver](https://github.com/mozilla/geckodriver/tags) or [Chromedriver](http://chromedriver.storage.googleapis.com/) repositories.

**Note 1:** *You will need to ensure that you have a recent version of Chrome and/or Firefox installed for the later versions of the drivers to work reliably.*

**Note 2** *These scripts use sudo to set the right permissions on the drivers so you will likely be prompted to enter your password.*

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").