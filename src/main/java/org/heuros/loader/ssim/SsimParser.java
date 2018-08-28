package org.heuros.loader.ssim;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

import org.heuros.core.data.base.ModelFactory;
import org.heuros.data.model.Leg;
import org.heuros.exception.InputParseException;
import org.heuros.util.TextFileReader;

/**
 * SSIM file parser class that is used to extract Leg data.
 * 
 * @author bahadrzeren
 *
 */
public class SsimParser extends TextFileReader<Leg> {

	private static String datePattern = "ddMMMyy";
	private static String timePattern = "HHmm";

	private DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
																			.appendPattern(SsimParser.datePattern)
																			.toFormatter(Locale.ENGLISH)
																			.withZone(ZoneOffset.UTC);

	private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(SsimParser.timePattern, Locale.ENGLISH)
																.withZone(ZoneOffset.UTC);

	public SsimParser(List<Leg> list, ModelFactory<Leg> modelFactory, File textFile) {
		super(list, modelFactory, textFile);
	}

	@Override
	public void parseLine(String s) throws InputParseException {
		/*
		 * Extract LEG data only.
		 */
		if (s.startsWith("3")) {

			String startDate = s.substring(14,21);
			String endDate = s.substring(21, 28);

			String depTime = s.substring(39, 43);
			String arrTime = s.substring(57, 61);

			String days = s.substring(28, 35);

			LocalDate startLD = LocalDate.parse(startDate, dateFormatter);
			LocalDate endLD = LocalDate.parse(endDate, dateFormatter).plusDays(1);	//	Exclusive
			LocalTime depLT = LocalTime.parse(depTime, timeFormatter);
			LocalTime arrLT = LocalTime.parse(arrTime, timeFormatter);

			while (endLD.isAfter(startLD)) {

				boolean add = (((startLD.getDayOfWeek() == DayOfWeek.MONDAY)
								&& (days.substring(0, 1).equals("1"))))
							|| (((startLD.getDayOfWeek() == DayOfWeek.TUESDAY)
									&& (days.substring(1, 2).equals("2"))))
							|| (((startLD.getDayOfWeek() == DayOfWeek.WEDNESDAY)
									&& (days.substring(2, 3).equals("3"))))
							|| (((startLD.getDayOfWeek() == DayOfWeek.THURSDAY)
									&& (days.substring(3, 4).equals("4"))))
							|| (((startLD.getDayOfWeek() == DayOfWeek.FRIDAY)
									&& (days.substring(4, 5).equals("5"))))
							|| (((startLD.getDayOfWeek() == DayOfWeek.SATURDAY)
									&& (days.substring(5, 6).equals("6"))))
							|| (((startLD.getDayOfWeek() == DayOfWeek.SUNDAY)
									&& (days.substring(6, 7).equals("7"))));

				if (add) {
					Leg leg = this.modelFactory.generateModel();
					leg.setSuffix(s.substring(1, 1).trim());
					leg.setCarrier(s.substring(2, 5).trim());
					leg.setFlightNo(Integer.parseInt(s.substring(5, 9).trim()));
					leg.setDep(s.substring(36, 39).trim());
					leg.setArr(s.substring(54, 57).trim());
					leg.setServiceType(s.substring(13, 14));
					leg.setAcType(s.substring(72, 75));
					leg.setSobt(LocalDateTime.of(startLD, depLT));
					leg.setSibt(LocalDateTime.of(startLD, arrLT));
					if (leg.getSobt().isAfter(leg.getSibt()))
						leg.setSibt(leg.getSibt().plusDays(1));

					String sign = s.substring(47, 48);
					int offset = (Integer.parseInt(s.substring(48, 50)) * 60)
								+ Integer.parseInt(s.substring(50, 52)) ;
					if ("-".equals(sign))
						offset = -offset;
					leg.setDepOffset(offset);

					sign = s.substring(65, 66);
					offset = (Integer.parseInt(s.substring(66, 68)) * 60)
							+ Integer.parseInt(s.substring(68, 70)) ;
					if ("-".equals(sign))
						offset = -offset;
					leg.setArrOffset(offset);

					list.add(leg);
				}

				startLD = startLD.plusDays(1);
			}
		}		
	}
}
