package org.heuros.util;

import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * General purpose text file writer.
 * 
 * @author bahadrzeren
 *
 */
public class TextFileWriter<T> {

	protected static Logger logger = Logger.getLogger(TextFileWriter.class);

	protected String textFileName = null;
	protected List<T> list = null;

	public TextFileWriter(List<T> list, String textFileName) {
		this.list = list;
		this.textFileName = textFileName;
	}

	public void writeTextFile() {
		try {
			logger.info("Text file " + textFileName + " is being generated.");

			PrintWriter writer = new PrintWriter(this.textFileName, "ISO-8859-1");

			this.list.forEach((l) -> writer.println(l.toString()));

			writer.close();

		} catch(Exception ex) {
			logger.error("File write exception!", ex);
		}
	}
}
