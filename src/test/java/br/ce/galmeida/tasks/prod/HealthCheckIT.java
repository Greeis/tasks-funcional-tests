package br.ce.galmeida.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		ChromeOptions opt = new ChromeOptions();
		cap.setCapability(ChromeOptions.CAPABILITY, opt);
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		
		try {
			driver.navigate().to("http://192.168.31.118:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}	
	}
}