package com.dimit.defaultMtd;

import java.util.function.Supplier;

public interface Defaulable {
	/**
	 * 接口的一个默认方法、他能够被实现类、接口继承或重写
	 * 
	 * @return
	 */
	default String notRequired() {
		return "我是默认方法";
	}

	/**
	 * 静态方法
	 * @param supplier
	 * @return
	 */
	static Defaulable create(Supplier<Defaulable> supplier) {
		return supplier.get();
	}
}
