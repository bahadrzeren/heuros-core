package org.heuros.core.base;

import java.util.List;

import org.heuros.core.data.base.Extension;
import org.heuros.core.data.base.Model;
import org.heuros.core.data.base.Wrapper;

/**
 * Generic interface for reporting the final data and creating its artifacts.
 * 
 * @author bahadrzeren
 *
 * @param <W extends Wrapper<M>, M extends Model> Class type of the final data that will be reported.
 */
public interface Reporter<W extends Wrapper<M, E>, 
							M extends Model, 
							E extends Extension> {
	public void reportData(List<W> data);
}
