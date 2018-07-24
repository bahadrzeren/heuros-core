package org.heuros.api.loader.ssim;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		} else
			if (!acRotationFile.exists()) {
				logger.error("Aircraft rotation file could not be found!");
			} else
				if (!carryInFile.exists()) {
					logger.error("Carry-in file could not be found!");
				} else {
					/*
					 * Parse ssim.
					 */
					List<Leg> legs = new ArrayList<Leg>();
					SsimParser ssimParser = new SsimParser(legs, ssimFile);
					if (ssimParser.parseTextFile() == 0) {
						logger.info("Ssim file processed successfully!");
						/*
						 * Merge rotation file info
						 */
						AcRotationMerger acRotationMerger = new AcRotationMerger(legs, acRotationFile);
						if (acRotationMerger.parseTextFile() == 0) {
							Optional<Leg> legOpt = legs.parallelStream()
														.filter((l) -> l.getAcSequence() == 0)
														.findFirst();
							if (legOpt.isPresent())
								logger.error("No Ac Rotation info found for " + legOpt.get());
							else {
								logger.info("AcRotation file processed successfully!");
								/*
								 * Merge carry-in info
								 */
								CarryinMerger carryInMerger = new CarryinMerger(legs, carryInFile);
								if (carryInMerger.parseTextFile() == 0) {
									logger.info("Carry-in file processed successfully!");
									return legs;
								}
							}
						}
					}
				}
		return null;
	}
}
