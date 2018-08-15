package org.heuros.reporter.legcsv;

import java.util.List;

import org.heuros.core.base.Reporter;
import org.heuros.data.model.LegView;
import org.heuros.util.TextFileWriter;

public class LegCsvReporter implements Reporter<LegView> {

	private String outputFileName = null;

	public LegCsvReporter setLegCsvReporter(String outputFileName) {
		this.outputFileName = outputFileName;
		return this;
	}

	@Override
	public void reportData(List<LegView> data) {
		new TextFileWriter<LegView>(data, this.outputFileName).writeTextFile(); 
	}
}
