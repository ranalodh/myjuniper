package com.ibm.testautomation.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesFileReader {
	
	private final static Logger LOGGER = Logger.getLogger(PropertiesFileReader.class.getName());
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public Properties getProperty() throws IOException {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/java/Resource/page-config.properties"));
		} catch (Exception e) {
			LOGGER.info("Exception: " + e);
		}
		return properties;
	}

}
