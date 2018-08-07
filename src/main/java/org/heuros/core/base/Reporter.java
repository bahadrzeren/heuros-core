package org.heuros.core.base;

import java.util.List;

/**
 * Generic interface for reporting the final data and creating its artifacts.
 * 
 * @author bahadrzeren
 *
 * @param <M> Class type of the final data that will be reported.
 */
public interface Reporter<M> {
	public void reportData(List<M> data);
}
