package org.heuros.loader.legs;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.heuros.core.base.Loader;
import org.heuros.core.data.base.ModelFactory;
import org.heuros.data.model.Leg;

/**
 * Legs CVS file loader. 
 * 
 * @author bahadrzeren
 *
 */
public class LegsLoader implements Loader<Leg> {

	private static Logger logger = Logger.getLogger(LegsLoader.class);

	private String legsFileName = null;
	private ModelFactory<Leg> modelFactory = null;

	public LegsLoader setLegsFileName(String legsFileName) {
		this.legsFileName = legsFileName;
		return this;
	}

	public LegsLoader setModelFactory(ModelFactory<Leg> modelFactory) {
		this.modelFactory = modelFactory;
		return this;
	}

	@Override
	public List<Leg> extractData() {
		File legsFile = new File(this.legsFileName);

		if (!legsFile.exists()) {
			logger.error("LEGs file could not be found!");
		} else {
			/*
			 * Parse ssim.
			 */
			List<Leg> legs = new ArrayList<Leg>();
			LegsParser legsParser = new LegsParser(legs, this.modelFactory, legsFile);
			if (legsParser.parseTextFile() == 0) {
				logger.info("Legs file processed successfully!");
				logger.info(legs.size() + " number of legs extracted.");
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
