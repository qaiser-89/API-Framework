package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager 
{
	private static Properties properties = new Properties();
	
	static 
	{
		// mvn clean install -Denv=qa/stage/dev/uat/prod
		// mvn clean install -Denv=qa
		// mvn clean install -- if env is not given, then run test cases on QA env by
		// default.
		// env -- environment variable(system)
		
		String envName = System.getProperty("env", "prod");
		System.out.println("Tests are Running on Env===="+envName);
		
		String filename = "config_"+ envName +".properties"; 
		
		InputStream input =	ConfigManager.class.getClassLoader().getSystemResourceAsStream("config/config.properties");
		if (input != null) 
		{
			try {
				properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static String get(String key) {
		
		return properties.getProperty(key);
	}
	
	public static void set(String key, String value)
	{
		properties.setProperty(key, value);
	}

}