package com.testcases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class flightTest extends BaseTest{
	WebDriver driver;
	
	public WebElement getObject(String objectkey )
	{
		WebElement e=null;
		try
		{
			
			if(objectkey.endsWith("_xpath")){
				e=driver.findElement(By.xpath(prop.getProperty(objectkey)));
			}
			else if(objectkey.endsWith("_id")){
				e=driver.findElement(By.id(prop.getProperty(objectkey)));
			}
			else if(objectkey.endsWith("_css")){
				e=driver.findElement(By.cssSelector(prop.getProperty(objectkey)));
			}
			else if(objectkey.endsWith("_name")) {
				e=driver.findElement(By.name(prop.getProperty(objectkey)));
			}
		}catch(Exception ex)
		{
			//report failure
			
			
		}
		
		return e;
	}
	
	public void javascript_click(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}

	@Test
	public void flight_selection()
	{
		
		String browser=prop.getProperty("Browser");
		
		if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",prop.getProperty("geckodriverpath"));
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
			 
			
			  	driver=new FirefoxDriver();
		}
		else if(browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverpath"));
			 driver=new ChromeDriver();
		}
		
		else if(browser.equals("chrome"))
		{
			 driver=new InternetExplorerDriver();
		}
	
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Select sel_1=new Select(driver.findElement(By.name("fromPort")));
		//System.out.println(sel_1);
		Select sel=new Select(getObject("from_name"));
		//System.out.println(sel);
		
		//fromPort
		List<WebElement> options = sel.getOptions();
		
		System.out.println(options.size());
		for(int i=0;i<options.size();i++)
		{
			options.get(1).click();
			
			
		}
		
		Select sel1=new Select(getObject("to_name"));
		List<WebElement> options1 = sel1.getOptions();
		
		System.out.println(options1.size());
		for(int i=0;i<options1.size();i++)
		{
			options1.get(1).click();
		
		}
		WebElement flight_button = getObject("flightbutton_xpath");
		
		javascript_click(flight_button);
		
		
		
		
		String beforexpath="//table[@class='table']/tbody/tr[";
		String afterxpath="]/td[6]";
		
		Set<Double> set=new TreeSet<Double>();
		
		
		for(int i=1;i<=5;i++)
		{
			String totalxpath = beforexpath+i+afterxpath;
			WebElement ele=driver.findElement(By.xpath(totalxpath));
			System.out.println(ele.getText());
			String prices=ele.getText();
			set.add(Double.valueOf(prices.substring(1)));		
		}
		System.out.println(set);
		Iterator<Double> itr = set.iterator();
		
		Double lowest = itr.next();	
		System.out.println("lowest is " + lowest.toString());
		
		for ( int j = 1; j <= 5 ; j ++){
			String totalxpath = beforexpath+j+afterxpath;
			WebElement ele=driver.findElement(By.xpath(totalxpath));
			System.out.println(ele.getText());
			String prices=ele.getText();
			
			if ( prices.contains(lowest.toString()))
			{
				
				String InputButton = "/html/body/div[2]/table/tbody/tr["+ j +"]/td[1]/input";
				driver.findElement(By.xpath(InputButton)).click();
				break;
			}
		}
		 

		System.out.println(getObject("firstname_id"));
		getObject("firstname_id").sendKeys("abc");
		getObject("address_name").sendKeys("pune");
		getObject("purchaseflight_xpath").click();
		
		List<WebElement>totalids=driver.findElements(By.xpath("//*[starts-with(text(),'160080')]"));
		if(totalids.size()==1)
		{
			System.out.println("element is present");
			String id=getObject("id_xpath").getText();
		}
		else
		{
			System.out.println("element is not present");
		}
		
	
	}
	@AfterMethod
	public void quit()
	{
		driver.quit();
	}

	

}
