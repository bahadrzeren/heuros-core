package org.heuros.reporter.legcsv;

import java.util.List;

import org.heuros.core.base.Reporter;
import org.heuros.data.model.LegExtension;
import org.heuros.data.model.LegModel;
import org.heuros.data.model.LegWrapper;
import org.heuros.util.TextFileWriter;

public class LegCsvReporter implements Reporter<LegWrapper, LegModel, LegExtension> {

	private String outputFileName = null;

	public LegCsvReporter setLegCsvReporter(String outputFileName) {
		this.outputFileName = outputFileName;
		return this;
	}

	@Override
	public void reportData(List<LegWrapper> data) {
		new TextFileWriter<LegWrapper>(data, this.outputFileName).writeTextFile(); 
	}
}
