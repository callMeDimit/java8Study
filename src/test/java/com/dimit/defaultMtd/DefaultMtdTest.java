package com.dimit.defaultMtd;

import org.junit.Test;

public class DefaultMtdTest {

	/**
	 * 默认方法初体验
	 */
	@Test
	public void test() {
		Defaulable impl = Defaulable.create(()->{
			return new DefaultableImpl();
		});
		System.out.println(impl.notRequired());
	}
	
	/**
	 * 重写默认方法
	 */
	@Test
	public void overrideTest() {
		Defaulable impl = Defaulable.create(()->{
			return new OverridableImpl();
		});
		System.out.println(impl.notRequired());
	}

	/**
	 * 继承测试
	 */
	@Test
	public void inheritanceTest() {
		Defaulable impl = Defaulable.create(()->{
			return new SonInterfaceImpl();
		});
		System.out.println(impl.notRequired());
	}
	
}
