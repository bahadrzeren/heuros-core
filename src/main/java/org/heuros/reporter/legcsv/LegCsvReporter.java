package org.heuros.reporter.legcsv;

import java.util.List;

import org.heuros.core.base.Reporter;
import org.heuros.data.model.Leg;
import org.heuros.util.TextFileWriter;

public class LegCsvReporter implements Reporter<Leg> {

	private String outputFileName = null;

	public LegCsvReporter setLegCsvReporter(String outputFileName) {
		this.outputFileName = outputFileName;
		return this;
	}

	@Override
	public void reportData(List<Leg> data) {
		new TextFileWriter<Leg>(data, this.outputFileName).writeTextFile(); 
	}
}
