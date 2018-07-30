package org.heuros.core.base;

import java.util.List;

/**
 * Generic interface for reporting the final data and creating its artifacts.
 * 
 * @author bahadrzeren
 *
 * @param <T> Class type of the final data that will be reported.
 */
public interface Reporter<T> {
	public void reportData(List<T> data);
}
