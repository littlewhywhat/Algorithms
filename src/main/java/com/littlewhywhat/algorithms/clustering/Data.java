package com.littlewhywhat.algorithms.clustering;

import java.util.List;
import java.util.Map;

public class Data {
	private List<BinaryString> list;
	private Map<BinaryString, BinaryString> map;

	public void setList(List<BinaryString> list) {
		this.list = list;
	}

	public List<BinaryString> getList() {
		return this.list;
	}

	public void setMap(Map<BinaryString, BinaryString> map) {
		this.map = map;
	}

	public Map<BinaryString, BinaryString> getMap() {
		return this.map;
	}
}