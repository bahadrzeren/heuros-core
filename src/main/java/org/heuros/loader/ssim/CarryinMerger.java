package org.heuros.loader.ssim;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Predicate;

import org.heuros.core.data.base.ModelFactory;
import org.heuros.data.model.Leg;
import org.heuros.exception.InputParseException;
import org.heuros.util.TextFileReader;

/**
 * Carry-in file parser class that is used to merge carry-in data with the associated flight legs.
 * 
 * @author bahadrzeren
 *
 */
public class CarryinMerger extends TextFileReader<Leg> {

	private static String datePattern = "ddMMMyyyy";

	private DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
																			.appendPattern(CarryinMerger.datePattern)
																			.toFormatter(Locale.ENGLISH)
																			.withZone(ZoneOffset.UTC);

	public CarryinMerger(List<Leg> list, ModelFactory<Leg> modelFactory, File textFile) {
		super(list, modelFactory, textFile);
	}

	@Override
	public void parseLine(String s) throws InputParseException {
		/*
		 * Extract LEG data only.
		 */
		if (s.startsWith("\"")) {

			StringTokenizer st = new StringTokenizer(s, ",");
			String token = st.nextToken();
			int flightNo = Integer.valueOf(token.substring(4, 8));
			String carrier = token.substring(1, 3);

			String dep = st.nextToken().substring(1, 4);
			LocalDate legDate = LocalDate.parse(st.nextToken(), dateFormatter);

			int ciC = Integer.valueOf(st.nextToken());
			int ciP = Integer.valueOf(st.nextToken());
			int ciA = Integer.valueOf(st.nextToken());
			int ciS = Integer.valueOf(st.nextToken());

			Predicate<Leg> p = new Predicate<Leg>() {
				@Override
				public boolean test(Leg l) {
					return l.getCarrier().equals(carrier)
							&& (l.getFligtNo() == flightNo)
							&& l.getDep().equals(dep)
							&& (l.getSobt().getYear() == legDate.getYear())
							&& (l.getSobt().getMonth() == legDate.getMonth())
							&& (l.getSobt().getDayOfMonth() == legDate.getDayOfMonth());
				}
			};

			long numOfLegsFound = this.list.stream().filter(p).count();
			if (numOfLegsFound > 1)
				throw new InputParseException("Multiple legs are found for " + carrier + flightNo + dep + legDate);
			else
			if (numOfLegsFound < 1) {
//				throw new InputParseException("No legs are found for " + carrier + flightNo + dep + legDate);
			} else {
				Optional<Leg> legOpt = this.list.stream()
												.filter(p)
												.findFirst();
				if (legOpt.isPresent()) {
					Leg leg = legOpt.get();
					leg.setNeedsCockpitCrew((ciC == 0) && (ciP == 0));
					leg.setNeedsCabinCrew((ciA == 0) && (ciS == 0));
				}
			}
		}		
	}
}
