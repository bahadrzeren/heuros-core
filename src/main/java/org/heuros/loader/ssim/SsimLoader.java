package org.heuros.loader.ssim;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.base.Loader;
import org.heuros.data.model.Leg;

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
	private int numOfBases = 0;

	public SsimLoader setSsimFileName(String ssimFileName) {
		this.ssimFileName = ssimFileName;
		return this;
	}

	public SsimLoader setAcRotationFileName(String acRotationFileName) {
		this.acRotationFileName = acRotationFileName;
		return this;
	}

	public SsimLoader setCarryInFileName(String carryInFileName) {
		this.carryInFileName = carryInFileName;
		return this;
	}

	public SsimLoader setNumOfBases(int numOfBases) {
		this.numOfBases = numOfBases;
		return this;
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
					SsimParser ssimParser = new SsimParser(legs, ssimFile, this.numOfBases);
					if (ssimParser.parseTextFile() == 0) {
						logger.info("Ssim file processed successfully!");
						logger.info(legs.size() + " number of legs extracted.");
						/*
						 * Merge rotation file info
						 */
						AcRotationMerger acRotationMerger = new AcRotationMerger(legs, acRotationFile);
						if (acRotationMerger.parseTextFile() == 0) {
							legs.parallelStream()
									.filter((l) -> l.getAcSequence() == 0)
									.forEach((l) -> logger.error("No Ac Rotation info found for " + l));
							logger.info("AcRotation file ise processed!");
							/*
							 * Merge carry-in info
							 */
							CarryinMerger carryInMerger = new CarryinMerger(legs, carryInFile);
							if (carryInMerger.parseTextFile() == 0) {
								logger.info("Carry-in file processed successfully!");
								/*
								 * Sort Leg list
								 */
								legs.sort(new Comparator<Leg>() {
									@Override
									public int compare(Leg a, Leg b) {
										if (a.getSobt().isAfter(b.getSobt()))
											return 1;
										else
											if (a.getSobt().isBefore(b.getSobt()))
												return -1;
											else
												if (a.getFlightNo() > b.getFlightNo())
													return 1;
												else
													if (a.getFlightNo() < b.getFlightNo())
														return -1;
													else
														if (a.getDepOffset() > b.getArrOffset())
															return 1;
														else
															if (a.getDepOffset() < b.getArrOffset())
																return -1;
															else 
																return a.getDep().compareTo(b.getDep());
									}
								});
								logger.info("Leg data is sorted!");
								return legs;
							}
						}
					}
				}
		return null;
	}
}
