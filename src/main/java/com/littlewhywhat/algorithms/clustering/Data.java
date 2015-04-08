package com.littlewhywhat.algorithms.clustering;

import java.util.List;
import java.util.Set;

public class Data {
	private List<BinaryString> list;
	private Set<BinaryString> set;

	public void setList(List<BinaryString> list) {
		this.list = list;
	}

	public List<BinaryString> getList() {
		return this.list;
	}

	public void setSet(Set<BinaryString> set) {
		this.set = set;
	}

	public Set<BinaryString> getSet() {
		return this.set;
	}
}