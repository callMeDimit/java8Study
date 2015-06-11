package com.dimit.defaultMtd;

public interface Defaulable {
	/**
	 * 接口的一个默认方法、他能够被实现类、接口继承或重写
	 * @return
	 */
    default String notRequired() { 
        return "我是默认方法"; 
    } 
}
