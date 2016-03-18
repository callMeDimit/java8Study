/**
 * 
 */
package com.dimit.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;

import com.dimit.reflect.general.model.Customer;
import com.dimit.reflect.general.model.Product;
import com.dimit.reflect.tiger.model.Order;

import junit.framework.Assert;

/**
 * @author Shane
 *
 */
public class FieldTest {
	@Test
	public void testField()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field[] declaredFields = Order.class.getDeclaredFields();
		Assert.assertEquals("declaredFields() return 3 fields.", 3, declaredFields.length);
		for (Field field : declaredFields) {
			String fieldName = field.getName();
			if (!fieldName.equals("id") && !fieldName.equals("customers") && !fieldName.equals("products")) {
				Assert.assertTrue(false);
			}
		}

		Field[] fields = Order.class.getFields();
		Assert.assertEquals("getFields() return 4 fields.", 4, fields.length);
		for (Field field : fields) {
			String fieldName = field.getName();
			if (!fieldName.equals("currency") && !fieldName.equals("businesstype") && !fieldName.equals("payment")
					&& !fieldName.equals("products")) {
				Assert.assertTrue(false);
			}
		}

		Order order = new Order(100L, new Customer[0], new ArrayList<Product>());
		Field longField = null;
		try {
			longField = order.getClass().getField("id");
		} catch (NoSuchFieldException e) {
			Assert.assertTrue("The field id isn't exist.", true);
		}
		try {
			longField = order.getClass().getDeclaredField("id");
		} catch (Exception e) {
			Assert.fail("There is some exceptions occur.");
		}
		Long longValue = null;
		try {
			longValue = (Long) longField.get(order);
		} catch (IllegalAccessException e) {
			Assert.assertTrue("The field id can't access.", true);
		}
		try {
			longField.setAccessible(true);
			longValue = (Long) longField.get(order);
		} catch (Exception e) {
			Assert.fail("There is some exceptions occur.");
		}
		Assert.assertEquals("The value of id field is 100", 100L, longValue.longValue());
	}
}
