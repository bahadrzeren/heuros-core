package org.heuros.loader.legs;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.heuros.core.data.base.ModelFactory;
import org.heuros.data.model.Leg;
import org.heuros.exception.InputParseException;
import org.heuros.util.TextFileReader;

public class LegsParser extends TextFileReader<Leg> {

	private static String datetimePattern = "yyyy-MM-dd'T'HH:mm";

	private DateTimeFormatter datetimeFormatter = DateTimeFormatter.ofPattern(LegsParser.datetimePattern,
																			Locale.ENGLISH)
																		.withZone(ZoneOffset.UTC);

	public LegsParser(List<Leg> list, ModelFactory<Leg> modelFactory, File textFile) {
		super(list, modelFactory, textFile);
	}

	@Override
	public void parseLine(String s) throws InputParseException {

		String[] st = s.split(",");

		Leg leg = this.modelFactory.generateModel();

		leg.setCarrier(st[0]);
		leg.setFlightNo(Integer.valueOf(st[1]));
		leg.setSuffix(st[2]);
		leg.setDep(st[3]);
		leg.setArr(st[4]);
		leg.setAcType(st[5]);
		leg.setAcSequence(Integer.valueOf(st[6]));
		leg.setSobt(LocalDateTime.parse(st[7], datetimeFormatter));
		leg.setSibt(LocalDateTime.parse(st[8], datetimeFormatter));
		leg.setDepOffset(Integer.valueOf(st[9]));
		leg.setArrOffset(Integer.valueOf(st[10]));
		leg.setServiceType(st[11]);
		leg.setNeedsCockpitCrew(Boolean.valueOf(st[12]));
		leg.setNeedsCabinCrew(Boolean.valueOf(st[13]));

		this.list.add(leg);
	}
}
