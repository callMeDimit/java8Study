package com.dimit.reflect;

import java.io.Serializable;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.dimit.reflect.general.model.IOrder;
import com.dimit.reflect.tiger.model.BaseOrder;
import com.dimit.reflect.tiger.model.Order;

import junit.framework.Assert;

/**
 * Unit test for simple ClassTest.
 * 
 * @author Shane
 */
public class ClassTest {
	/**
	 * Rigourous Test
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public void testClass() throws ClassNotFoundException {
		Order order = new Order();
		Long longValue = 0L;
		int intArray[] = {};
		Assert.assertEquals("getClass() method.", Order.class, order.getClass());
		Assert.assertEquals("getClass() method.", Long.class, longValue.getClass());
		Assert.assertEquals("getClass() method.", int[].class, intArray.getClass());
		Assert.assertEquals(".class syntax", "int", int.class.getSimpleName());
		Assert.assertEquals(".class syntax", "void", void.class.getSimpleName());
		Assert.assertEquals("Assert.assertEqualsthod.", Integer.class, Class.forName("java.lang.Integer"));
		Assert.assertEquals("forName() method.", Void.class, Class.forName("java.lang.Void"));
		Assert.assertEquals(".TYPE syntax", float.class, Float.TYPE);
		Assert.assertEquals(".TYPE syntax", void.class, Void.TYPE);
		// 获取内部类  getDeclaredClasses可以获取私有的内部类但是不能获取到父类的内部类
		Class<?>[] declaredClasses = Order.class.getDeclaredClasses();
		Assert.assertEquals("declaredClasses() return 2 classes.", 2, declaredClasses.length);
		for (Class<?> clazz : declaredClasses) {
			if (!clazz.equals(Order.Checker.class) && !"ShoppingCart".equals(clazz.getSimpleName())) {
				Assert.assertTrue(false);
			}
		}
		// 可以获取到父类的内部类   但是不能获取到私有的内部类
		Class<?>[] classes = Order.class.getClasses();
		Assert.assertEquals("getClasses() return 2 classes.", 2, classes.length);
		for (Class<?> clazz : classes) {
			if (!clazz.equals(Order.Checker.class) && !clazz.equals(BaseOrder.Type.class)) {
				Assert.assertTrue(false);
			}
		}
		
		// getEnclosingClass 获取让内部类获取到外部类的class对象
		Assert.assertEquals("getEnclosingClass() method.", Order.class, Order.Checker.class.getEnclosingClass());
		Assert.assertNull("getEnclosingClass() method.", Order.class.getEnclosingClass());
		
		// 获取接口
		Class<?>[] interfaces = Order.class.getInterfaces();
		Assert.assertEquals("getInterfaces() method returen 2 interfaces.", 2, interfaces.length);
		for (Class<?> clazz : interfaces) {
			if (!clazz.equals(IOrder.class) && !clazz.equals(Serializable.class)) {
				Assert.assertTrue(false);
			}
		}
		Assert.assertEquals("getSuperclass() method.", BaseOrder.class, Order.class.getSuperclass());
		Assert.assertEquals("getConstructors() method returen 2 constructors.", 2, Order.class.getConstructors().length);
		// 获取访问修饰符
		Assert.assertEquals("getModifiers() method.", Modifier.PUBLIC, Order.class.getModifiers());
		// 获取包路径
		Assert.assertEquals("getPackage() method.", "com.dimit.reflect.tiger.model", Order.class.getPackage().getName());
	}
}