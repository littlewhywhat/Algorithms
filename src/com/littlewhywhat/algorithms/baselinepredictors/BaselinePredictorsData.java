package com.littlewhywhat.algorithms.baselinepredictors;

import com.littlewhywhat.algorithms.baselinepredictors.BaselinePredictorsAlgorithm.Data;
import com.littlewhywhat.datastructure.ArrayPart;

public class BaselinePredictorsData {

	MatrixIndex[] learnIndices;
	Data algoData;

	public BaselinePredictorsData(MatrixIndex[] learnIndices,
			Data algoData) {
		super();
		this.learnIndices = learnIndices;
		this.algoData = algoData;
	}

	public ArrayPart<MatrixIndex> getTestIndices() {
		return this.algoData.getTestIndices();
	}

	public int[][] getMatrix() {
		return this.algoData.getMatrix();
	}

	public Data getAlgoData() {
		return algoData;
	}

	public void setAlgoData(Data algoData) {
		this.algoData = algoData;
	}

	public MatrixIndex[] getLearnIndices() {
		return learnIndices;
	}

	public void setLearnIndices(MatrixIndex[] learnIndices) {
		this.learnIndices = learnIndices;
	}

}
