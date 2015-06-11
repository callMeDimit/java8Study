package com.dimit.defaultMtd;

import org.junit.Test;

public class DefaultMtdTest {

	/**
	 * 默认方法初体验
	 */
	@Test
	public void test() {
		DefaultableImpl impl = new DefaultableImpl();
		System.out.println(impl.notRequired());
	}
	
	/**
	 * 重写默认方法
	 */
	@Test
	public void overrideTest() {
		OverridableImpl impl = new OverridableImpl();
		System.out.println(impl.notRequired());
	}

	/**
	 * 继承测试
	 */
	@Test
	public void inheritanceTest() {
		SonInterfaceImpl sonInterface = new SonInterfaceImpl();
		System.out.println(sonInterface.notRequired());
	}
	
}
