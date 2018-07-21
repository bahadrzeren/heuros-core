package org.heuros.api.loader.ssim;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.api.loader.Loader;
import org.heuros.api.model.Leg;

/**
 * SSIM based timetable loader. Needs SSIM, Rotation and Carry-in files to extact timetable data. 
 * 
 * @author bahadrzeren
 *
 */
public class SsimLoader implements Loader<Leg> {

	private static Logger logger = Logger.getLogger(SsimLoader.class);

	private String ssimFileName = null;
	private String acRotationFileName = null;
	private String carryInFileName = null;

	public SsimLoader(String ssimFileName
						, String acRotationFileName
						, String carryInFileName) {
		this.ssimFileName = ssimFileName;
		this.acRotationFileName = acRotationFileName;
		this.carryInFileName = carryInFileName;
	}

	@Override
	public List<Leg> extractData() {
		File ssimFile = new File(this.ssimFileName);
		File acRotationFile = new File(this.acRotationFileName);
		File carryInFile = new File(carryInFileName);

		if (!ssimFile.exists()) {
			logger.error("SSIM file could not be found!");
			return null;
		} else
			if (!acRotationFile.exists()) {
				logger.error("Aircraft rotation file could not be found!");
				return null;
			} else
				if (!carryInFile.exists()) {
					logger.error("Carry-in file could not be found!");
					return null;
				} else {
					SsimParser ssimParser = new SsimParser();
					List<Leg> legs = ssimParser.parseSSIM(ssimFile);
					if (legs != null) {
						legs.forEach(logger::info);
					}
					return legs;
				}
	}
}
