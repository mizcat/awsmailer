package com.awsmailer.config;

import java.io.IOException;
import java.util.Properties;

import com.amazonaws.auth.PropertiesCredentials;

public class ConfigFileLoader {
	public static String DEVELOPMENT = "dev"; // developer boxes
	public static String STAGING = "stg"; // staging (bamboo)
	public static String PRODUCTION = "prd";  // production
	
	public String getFile(String environment) throws IOException {
		Properties configFile = new Properties();
		String directory = null;
		if(STAGING.equals(environment)) {			
			configFile.load(ConfigFileLoader.class.getResourceAsStream("/staging.properties"));
			directory = new java.io.File("").getAbsolutePath()  + configFile.getProperty("templateDirectory");
		} else if (PRODUCTION.equals(environment)) {
			configFile.load(ConfigFileLoader.class.getResourceAsStream("/production.properties"));
			directory = configFile.getProperty("templateDirectory");
		} else if (DEVELOPMENT.equals(environment)) {
			configFile.load(ConfigFileLoader.class.getResourceAsStream("/development.properties"));
			directory = new java.io.File("").getAbsolutePath()  + configFile.getProperty("templateDirectory");
		}
		
		return directory;
	}
	
    // Your AWS credentials are stored in the AwsCredentials.properties file accessible via classpath
	public PropertiesCredentials getAWSCredentails() {
        PropertiesCredentials credentials;
		try {
			credentials = new PropertiesCredentials(
					ConfigFileLoader.class
			                .getResourceAsStream("/AwsCredentials.properties"));
			
			 return credentials;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;       
	}
}
