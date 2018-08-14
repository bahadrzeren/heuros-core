package org.heuros.loader.ssim;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.heuros.data.model.Leg;
import org.heuros.data.model.LegModel;
import org.heuros.exception.InputParseException;
import org.heuros.util.TextFileReader;

/**
 * Rotation file parser class that is used to merge AC tail assignment data with the associated flight legs.
 * 
 * @author bahadrzeren
 *
 */
public class AcRotationMerger extends TextFileReader<LegModel> {

	private static String datePattern = "yyyyMMdd";
	private static String timePattern = "HHmm";

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(AcRotationMerger.datePattern, Locale.ENGLISH)
																.withZone(ZoneOffset.UTC);

	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(AcRotationMerger.timePattern, Locale.ENGLISH)
																.withZone(ZoneOffset.UTC);

	public AcRotationMerger(List<LegModel> list, File textFile) {
		super(list, textFile);
	}

	@Override
	public void parseLine(String s) throws InputParseException {
		/*
		 * Skip first line.
		 */
		if (!s.startsWith("2")) {
			Pattern ptrn = Pattern.compile("([0-9A-Z ]{3})"		//Carrier
											+ "(\\d{4})"		//Flight No
											+ "([0-9A-Z ])"		//Suffix
											+ "([0-9A-Z ]{3})"	//AC Type
											+ "([0-9]{3})"		//AC Sequence No
											+ "([A-Z]{3})"		//Dep AP
											+ "([A-Z]{3})"		//Arr AP
											+ "([0-9]{8})"		//Effective Leg Date
											+ "([0-9]{1})"		//Leg Freq
											+ "([0-9]{8})"		//Effective Flight Date
											+ "([0-9]{1})"		//Flight Freq
											+ "([0-9]{4})"		//Dep Time
											+ "([0-9]{4})"		//Arr Time
											+ "([A-Z]*)");		//Cabin Class Info4

			Matcher match = ptrn.matcher(s);
			if (match.find()) {
				String carrier = match.group(1).trim();
				int flightNo = Integer.parseInt(match.group(2));
				String suffix = match.group(3).trim();
//				String acType = match.group(4).trim();
				int acSeqNo = Integer.parseInt(match.group(5));
				String dep = match.group(6);
//				String arr = match.group(7);
				LocalDate legDate = LocalDate.parse(match.group(8), dateFormatter);
//				int legFrequency = Integer.parseInt(match.group(9));
//				LocalDate flightDate = LocalDate.parse(match.group(10), dateFormatter);
//				int flightFrequency = Integer.parseInt(match.group(11));
				LocalTime depTime = LocalTime.parse(match.group(12), timeFormatter);
//				LocalTime arrTime = LocalTime.parse(match.group(13), timeFormatter);
				LocalDateTime depDateTime = LocalDateTime.of(legDate, depTime);

				Predicate<LegModel> p = new Predicate<LegModel>() {
					@Override
					public boolean test(LegModel l) {
						return l.getCarrier().equals(carrier)
								&& (l.getFligtNo() == flightNo)
								&& l.getDep().equals(dep)
								&& l.getSobt().equals(depDateTime)
								&& ((((l.getSuffix() == null) || (l.getSuffix().length() == 0))
										&& ((suffix == null) || (suffix.length() == 0)))
									|| (l.getSuffix().equals(suffix)));
					}
				};

				long numOfLegsFound = this.list.parallelStream().filter(p).count();
				if (numOfLegsFound > 1)
					throw new InputParseException("Multiple legs are found for " + carrier + flightNo + dep + depDateTime);
				else
				if (numOfLegsFound < 1) {
//					throw new InputParseException("No legs are found for " + carrier + flightNo + dep + depDateTime);
				} else {
					Leg leg = (Leg) this.list.parallelStream()
												.filter(p)
												.findFirst()
												.get();
					leg.setAcSequence(acSeqNo);
				}
			}
		}
	}
}
