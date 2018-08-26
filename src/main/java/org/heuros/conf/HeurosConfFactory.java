package org.heuros.conf;

import java.io.File;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Generic json configuration file loader.
 * 
 * @author bahadrzeren
 *
 * @param <C> Type of the configuration class to be instantiated.
 */
public class HeurosConfFactory<C> {

	private static Logger logger = Logger.getLogger(HeurosConfFactory.class);

	private static String defaultConfFileName = "./heurosConf.json";
	private static String developmentConfFileName = "./src/main/resources/heurosConf.json";

	/**
	 * Factory method that creates configuration object instances.
	 * 
	 * @param confFileNameArg File name of the json file to load. Null is acceptable to use defaults.
	 * @param confClazz Class of the configuration object to be instantiated. 
	 * @return Instance of the configuration class. Null if the read operation is not successful.
	 */
	public C createConfObject(String confFileNameArg, Class<C> confClazz) {
    	/**
    	 * Load JSON configuration file.
    	 */
		ObjectMapper objectMapper = new ObjectMapper();
		C confObject = null;
		File confFile = null;
		/**
		 * Read configuration file.
		 */
		if ((confFileNameArg != null)
				&& (confFileNameArg.length() > 0)
				&& (confFileNameArg.toUpperCase().endsWith(".JSON"))) {
			confFile = new File(confFileNameArg);
			if (!confFile.exists()) {
				logger.error("Configuration file " + confFile + " does not exist!");
				return null;
			}
			logger.info("Configuration file is found " + confFile);
		} else {
			/**
			 * Read default configuration file.
			 */
			confFile = new File(HeurosConfFactory.defaultConfFileName);
			if (!confFile.exists()) {
				/**
				 * Read development configuration file.
				 */
				confFile = new File(HeurosConfFactory.developmentConfFileName);
				if (!confFile.exists()) {
					logger.error("Default configuration files could not be found!");
					return null;
				} else
					logger.info("Development configuration file is found " + confFile);
			} else
				logger.info("Default configuration file is found " + confFile);
		}

		try {
			confObject = objectMapper.readValue(confFile, confClazz);
		} catch (Exception ex) {
			logger.error("Configuration file mapping exception!", ex);
		}

		if (confObject != null)
			this.logConfObjectContent(confObject);
		return confObject;
	}

	/**
	 * Logs the content of the configuration object via reflection.
	 * 
	 * @param confObject Configuration object that its contents will be read.
	 */
	private void logConfObjectContent(C confObject) {
		logger.info("CONFIGURATION FILE CONTENT:");
		Arrays.stream(confObject.getClass().getDeclaredFields())
				.forEach((f) -> {
					try {
						f.setAccessible(true);
						logger.info(f.getName() + ": " + f.get(confObject));
						f.setAccessible(false);
					} catch (Exception ex) {
						logger.info(f.getName() + ": ???");
						logger.error(ex);
					}
				});
	}
}
