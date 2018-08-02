package org.heuros.util;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.exception.InputParseException;

/**
 * General purpose text file reader.
 * 
 * @author bahadrzeren
 *
 */
public abstract class TextFileReader<T> {

	protected static Logger logger = Logger.getLogger(TextFileReader.class);

	protected abstract void parseLine(String s) throws InputParseException;

	protected File textFile = null;
	protected List<T> list = null;

	public TextFileReader(List<T> list, File textFile) {
		this.list = list;
		this.textFile = textFile;
	}

	private int numOfFails = 0;

	/**
	 * Reads text file line by line and calls abstract parseLine method.
	 * 
	 * @return Number of lines that could not be parsed successfully.
	 */
	public int parseTextFile() {
		this.numOfFails = 0;
		try {
			logger.info("Text file " + textFile.getCanonicalPath() + " is being parsed.");

			Files.lines(textFile.toPath())
					.forEach((s) -> {
						try {
							this.parseLine(s);
						} catch(Exception ex) {
							logger.error(s);
							logger.error("Line read exception!", ex);
							this.numOfFails++;
						}
					});

			logger.info("Text file " + textFile.getCanonicalPath() + " is parsed.");
		} catch(Exception ex) {
			logger.error("File read exception!", ex);
			this.numOfFails = Integer.MAX_VALUE;
		}
		return this.numOfFails;
	}
}
