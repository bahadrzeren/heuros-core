package org.heuros.core.data.ndx;

import java.util.Arrays;

import org.heuros.core.data.base.View;

/**
 * Abstract Index class to store data on one dimensinal array for indexed and fast access.
 * @author bahadrzeren
 *
 * @param <T> Type of the instances to be indexed.
 * @param <N> Type of the indexing metric (integer, datetime etc.).
 */
public abstract class OneDimIndex<T extends View, N> {

    protected int rootNdx = 0;
    protected int indexedElementCount = 0;

    protected T[][] dataArray = null;
    protected int[] dimensionLengthNdxs = null;

	public OneDimIndex(T[][] arry) {
        this.dataArray = arry;
        dimensionLengthNdxs = new int[arry.length];
    }

    public int getRootNdx() {
        return rootNdx;
    }

    public void setRootNdx(N val) {
    	this.rootNdx = this.convert(val);
    }

    public void resize() {
    	for (int i = 0; i < dimensionLengthNdxs.length; i++) {
   			dataArray[i] = Arrays.copyOf(dataArray[i], dimensionLengthNdxs[i]);
   			dimensionLengthNdxs[i] = 0;
    	}
    }

    public abstract int convert(N value);

	public void incDimensionSize(N dimensionNdx) {
    	int relNdx = this.convert(dimensionNdx);
        this.dimensionLengthNdxs[relNdx]++;
    }

    public boolean add(N dimensionNdx, T obj) {
        int relNdx = this.convert(dimensionNdx);
        if ((relNdx >= this.dataArray.length) || (relNdx < 0)) return false;
        if ((this.dataArray[relNdx].length == 0) || (this.dataArray[relNdx][this.dataArray[relNdx].length - 1] != null)) {
	        this.dataArray[relNdx] = Arrays.copyOf(this.dataArray[relNdx], this.dataArray[relNdx].length + 1);
	        this.dataArray[relNdx][this.dataArray[relNdx].length - 1] = obj;
        } else {
        	this.dataArray[relNdx][this.dimensionLengthNdxs[relNdx]] = obj;
        	this.dimensionLengthNdxs[relNdx]++;
        }
        indexedElementCount++;
        return true;
    }

    public T[] getArray(N dimensionNdx) {
    	int relNdx = this.convert(dimensionNdx);
    	if ((this.dataArray.length > (relNdx)) && (relNdx >= 0))
    		return this.dataArray[relNdx];
    	else
    		return null;
    }

    public int getNumOfIndexedElements() {
        return indexedElementCount;
    }
}
