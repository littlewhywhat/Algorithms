package com.littlewhywhat.algorithms.io;

public abstract class AbstractInputReader<ConfigType, DataType> implements
		InputReader<ConfigType, DataType> {
	private ConfigType config;
	private DataType data;

	protected void setConfig(ConfigType config) {
		this.config = config;
	}
	protected void setData(DataType data) {
		this.data = data;
	}
	
	@Override
	public ConfigType getConfig() {
		return this.config;
	}

	@Override
	public DataType getData() {
		return this.data;
	}

}
