package com.testcases;

import java.io.FileInputStream;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
	public Properties envprop;
	public Properties prop;//env.properties

	@BeforeTest
	public void init()
	{

		prop=new Properties();
		envprop=new Properties();
		try {
			FileInputStream fn=new FileInputStream("D:\\mydata\\workspace\\codingexam\\src\\main\\resources\\env.properties");
			prop.load(fn);
		
			String enviroment=prop.getProperty("env");
			System.out.println(enviroment);
	
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
	}

}
