package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.View;

/**
 * Generic interface for reporting the final data and creating its artifacts.
 * 
 * @author bahadrzeren
 *
 * @param <W extends Wrapper<M>, M extends Model> Class type of the final data that will be reported.
 */
public interface Reporter<V extends View> {
	public void reportData(List<V> data);
}
