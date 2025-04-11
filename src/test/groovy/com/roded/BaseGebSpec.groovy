package com.roded

import geb.spock.GebSpec
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver

import java.time.Instant

class BaseGebSpec extends GebSpec {

    int SLEEP_MS = 1000

    void setupSpec() {
        println "FOO: ${Instant.now().toEpochMilli()} ${getClass()}.setupSpec"
//        ChromeOptions chromeOptions = new ChromeOptions()
//        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions)
//        driver = chromeDriver

        String sessionName = this.class.getName()
        String videoName = String.format("%s", sessionName.replace('.', '_'))
        ChromeOptions chromeOptions = new ChromeOptions()
        chromeOptions.addArguments("--start-maximized")
        chromeOptions.addArguments("--disable-web-security")
        chromeOptions.addArguments("--no-proxy-server")
        chromeOptions.addArguments("disable-infobars")
        chromeOptions.addArguments("--no-sandbox")
        chromeOptions.addArguments("--disable-dev-shm-usage")
        chromeOptions.setAcceptInsecureCerts(true)
        chromeOptions.setCapability("se:name", sessionName)
        chromeOptions.setCapability("se:videoName", videoName)
        chromeOptions.setCapability("se:recordVideo", true)
        chromeOptions.setCapability("se:vncEnabled", false)
        chromeOptions.setCapability("se:timeZone", "US/Central")
        chromeOptions.setCapability("se:screenResolution", "1600x1200x24")
        Map<String, Object> prefs = new HashMap<>()
        prefs.put("credentials_enable_service", false)
        prefs.put("profile.password_manager_enabled", false)
        chromeOptions.setExperimentalOption("prefs", prefs)

        String driverUrl = "http://selenium-grid-m9data.amicloud.bmc.com"
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(driverUrl), chromeOptions)
        driver = remoteWebDriver
    }

    void setup() {
        println "FOO: ${Instant.now().toEpochMilli()} ${getClass()}.setup"
    }

    void cleanupSpec() {
        exitDriver()
    }

    void exitDriver() {
        driver.quit()
        driver = null
    }
}
