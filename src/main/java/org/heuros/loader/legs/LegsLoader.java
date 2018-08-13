package org.heuros.loader.legs;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.base.Loader;
import org.heuros.data.model.LegImpl;

/**
 * Legs file loader. 
 * 
 * @author bahadrzeren
 *
 */
public class LegsLoader implements Loader<LegImpl> {

	private static Logger logger = Logger.getLogger(LegsLoader.class);

	private String legsFileName = null;

	public LegsLoader setLegsFileName(String legsFileName) {
		this.legsFileName = legsFileName;
		return this;
	}

	@Override
	public List<LegImpl> extractData() {
		File legsFile = new File(this.legsFileName);

		if (!legsFile.exists()) {
			logger.error("LEGs file could not be found!");
		} else {
			/*
			 * Parse ssim.
			 */
			List<LegImpl> legs = new ArrayList<LegImpl>();
			LegsParser legsParser = new LegsParser(legs, legsFile);
			if (legsParser.parseTextFile() == 0) {
				logger.info("Legs file processed successfully!");
				logger.info(legs.size() + " number of legs extracted.");
				/*
				 * Sort Leg list
				 */
				legs.sort(new Comparator<LegImpl>() {
					@Override
					public int compare(LegImpl a, LegImpl b) {
						if (a.getSobt().isAfter(b.getSobt()))
							return 1;
						else
							if (a.getSobt().isBefore(b.getSobt()))
								return -1;
							else
								if (a.getFligtNo() > b.getFligtNo())
									return 1;
								else
									if (a.getFligtNo() < b.getFligtNo())
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
		return null;
	}
}
