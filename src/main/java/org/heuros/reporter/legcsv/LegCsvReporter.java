package org.heuros.reporter.legcsv;

import java.util.List;

import org.heuros.core.base.Reporter;
import org.heuros.data.model.LegImpl;
import org.heuros.util.TextFileWriter;

public class LegCsvReporter implements Reporter<LegImpl> {

	private String outputFileName = null;

	public LegCsvReporter setLegCsvReporter(String outputFileName) {
		this.outputFileName = outputFileName;
		return this;
	}

	@Override
	public void reportData(List<LegImpl> data) {
		new TextFileWriter<LegImpl>(data, this.outputFileName).writeTextFile(); 
	}
}
