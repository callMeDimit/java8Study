/**
 * 
 */
package com.dimit.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.dimit.reflect.tiger.model.Order;

import junit.framework.Assert;

/**
 * @author Shane
 *
 */
public class MethodTest {
	@Test
	public void testMethod() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Order order = null;
		Method setId = null;
		try {
			setId = Order.class.getMethod("setId", new Class[] { Long.class });
		} catch (NoSuchMethodException e) {
			Assert.assertTrue("This method is not exist in Order or BaseOrder, or the modifier is not Public.", true);
		}
		try {
			setId = Order.class.getDeclaredMethod("setId", new Class[] { Long.class });
		} catch (Exception e) {
			Assert.fail("There is some exceptions occur.");
		}
		try {
			order = new Order();
			setId.invoke(order, new Object[] { 100L });
		} catch (IllegalArgumentException e) {
			Assert.fail("These arguments aren't suiable for this methond.");
		}
		Method getId = Order.class.getMethod("getId");
		Object o = getId.invoke(order);
		Assert.assertEquals("The method invoke represented by java.lang.reflect.Method retrieve the value of 100.",
				100L, ((Long) o).longValue());
	}
}
