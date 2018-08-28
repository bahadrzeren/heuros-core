package org.heuros.core.data.ndx;

import java.util.Arrays;

import org.heuros.core.data.base.View;

/**
 * Abstract Index class to store data on two dimensinal array for indexed and fast access.
 * 
 * @author bahadrzeren
 *
 * @param <T> Type of the instances to be indexed.
 * @param <N> Type of the first indexing metric (integer, datetime etc.).
 * @param <D> Type of the second indexing metric (integer, datetime etc.).
 */
public abstract class TwoDimIndex<T extends View, N, D> {

	protected int rootNdxN = 0;
    protected int rootNdxD = 0;
    protected int indexedElementCount = 0;

    protected T[][][] dataArray = null;
    protected int[][] dimensionLengthNdxs = null;

	public TwoDimIndex(T[][][] arry) {
        this.dataArray = arry;
        dimensionLengthNdxs = new int[arry.length][arry[0].length];
    }

    public int getRootNdxN() {
        return rootNdxN;
    }

    public void setRootNdxN(N val) {
    	this.rootNdxN = this.convertN(val);
    }

    public int getRootNdxD() {
        return rootNdxD;
    }

    public void setRootNdxD(D val) {
    	this.rootNdxD = this.convertD(val);
    }

    public abstract int convertN(N value);
    public abstract int convertD(D value);

	public void incDimensionSize(N n, D d) {
    	int relNdxN = this.convertN(n);
    	int relNdxD = this.convertD(d);
        this.dimensionLengthNdxs[relNdxN][relNdxD]++;
    }

    public void resize() {
    	for (int i = 0; i < dimensionLengthNdxs.length; i++) {
        	for (int j = 0; j < dimensionLengthNdxs[i].length; j++) {
        		dataArray[i][j] = Arrays.copyOf(dataArray[i][j], dimensionLengthNdxs[i][j]);
        		dimensionLengthNdxs[i][j] = 0;
        	}
    	}
    }

    public boolean add(N n, D d, T obj) {
        int relNdxN = this.convertN(n);
        int relNdxD = this.convertD(d);
        if ((relNdxN >= this.dataArray.length) || (relNdxN < 0)) return false;
        if ((relNdxD >= this.dataArray[relNdxN].length) || (relNdxD < 0)) return false;
        if ((this.dataArray[relNdxN][relNdxD].length == 0) || (this.dataArray[relNdxN][relNdxD][this.dataArray[relNdxN][relNdxD].length - 1] != null)) {
	        this.dataArray[relNdxN][relNdxD] = Arrays.copyOf(this.dataArray[relNdxN][relNdxD], this.dataArray[relNdxN][relNdxD].length + 1);
	        this.dataArray[relNdxN][relNdxD][this.dataArray[relNdxN][relNdxD].length - 1] = obj;
        } else {
        	this.dataArray[relNdxN][relNdxD][this.dimensionLengthNdxs[relNdxN][relNdxD]] = obj;
        	this.dimensionLengthNdxs[relNdxN][relNdxD]++;
        }
        indexedElementCount++;
        return true;
    }

    public T[] getArray(N n, D d) {
        int relNdxN = this.convertN(n);
        int relNdxD = this.convertD(d);
    	if ((this.dataArray.length > relNdxN) && (relNdxD >= 0))
    		return this.dataArray[relNdxN][relNdxD];
    	else
    		return null;
    }

    public int getNumOfIndexedElements() {
        return indexedElementCount;
    }
}
