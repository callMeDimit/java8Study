package com.dimit.defaultMtd;

public interface SonInterface extends Defaulable {
	/**
	 * 子接口重写默认方法
	 * 
	 * @return
	 */
	default String notRequired() {
		return "我是SonInterface,我重写了Defaulable的默认方法";
	}
}
