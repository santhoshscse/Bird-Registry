package com.saltside.bird.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirdProperties {
	private static Logger logger = LoggerFactory.getLogger(BirdProperties.class);
	private static BirdProperties Instance = new BirdProperties();

	public static BirdProperties getInstance() {
		return Instance;
	}

	private Properties prop = new Properties();

	BirdProperties() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream file = classLoader.getResourceAsStream("Config.properties");
		try {
			prop.load(file);
			for (Object key : prop.keySet()) {
				logger.info(prop.getProperty((String) key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMongoDB() {
		return prop.getProperty("mongodb");
	}

	public String getMongoServer() {
		return prop.getProperty("mongoserver");
	}

}
