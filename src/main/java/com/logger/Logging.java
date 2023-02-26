package com.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {

	private static final Logger logger = LogManager.getLogger(Logging.class);

	private static Logging instance;

	private Logging() {
	}

	public static Logging getInstance() {
		if (instance == null) {
				if (instance == null) {
					instance = new Logging();
				}
		}
		return instance;
	}

	public Logger getLogger() {
		return logger;
	}
}
