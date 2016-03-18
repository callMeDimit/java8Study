/**
 * 
 */
package com.dimit.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;

import com.dimit.reflect.tiger.model.Order;

import junit.framework.Assert;

/**
 * @author Shane
 *
 */
public class ConstructorTest {
	
	@Test
	public void testConstructor() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<?>[] declaredConstructors = Order.class.getDeclaredConstructors();
		Assert.assertEquals("declaredConstructors() return 4 Constructors.", 4, declaredConstructors.length);
		
		//getDeclaringClass 获取声明改方法的类的class对象
		for (Constructor<?> constructor : declaredConstructors) {
			Assert.assertEquals("This constructor is represented by Order", Order.class,constructor.getDeclaringClass());
			if ((constructor.getModifiers() & (Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE)) == 0) {
				Assert.assertTrue(false);
			}
		}

		Constructor<?>[] Constructors = Order.class.getConstructors();
		Assert.assertEquals("getConstructors() return 2 Constructors.", 2, Constructors.length);

		Constructor<?> constructor = Order.class.getConstructor(new Class[0]);
		Object obj = constructor.newInstance();
		Assert.assertTrue("Order object have a no parameter constructor.", obj instanceof Order);
	}
}
