package org.heuros.core.base;

import java.util.List;

import org.heuros.core.modelbase.IModel;

/**
 * Generic interface for reporting the final data and creating its artifacts.
 * 
 * @author bahadrzeren
 *
 * @param <T> Class type of the final data that will be reported.
 */
public interface Reporter<T extends IModel> {
	public void reportData(List<T> data);
}
