package com.dimit.clazzloader;

public class Sample {
	@SuppressWarnings("unused")
	private Sample instance;

	public void setSample(Object instance) {
		this.instance = (Sample) instance;
	}
}
