package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.View;

/**
 * Generic interface for reporting the final data and creating its artifacts.
 * 
 * @author bahadrzeren
 *
 * @param <V> Type of the instances to be reported.
 */
public interface Reporter<V extends View> {
	public void reportData(List<V> data);
}
