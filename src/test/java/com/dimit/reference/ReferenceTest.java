package com.dimit.reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReferenceTest {
	public static final String NAME = "amine";
	private static List<Car> cars;

	/**
	 * 构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。
	 */
	@BeforeClass
	public static void beforeClz() {
		Car car = Car.create(Car::new);
		car.setName(NAME);
		cars = new ArrayList<Car>(Arrays.asList(car));
	}

	/**
	 * 构造器引用
	 */
	@Test
	public void test() {
		cars.removeIf((u) -> {
			if (u.getName().equals(NAME)) {
				System.out.println("我是amine。我被移除了");
				return true;
			}
			return false;
		});
		System.out.println(cars.size());
	}

	/**
	 * 静态方法引用测试
	 */
	@Test
	public void staticMtdTest() {
		cars.forEach(Car::collide);
		Converter<String, Integer> converter = Integer::valueOf;
		Integer converted = converter.convert("123");
		System.out.println(converted);   // 123
		
		//等价于
		Converter<String, Integer> converter2 = (from) -> Integer.valueOf(from);
		Integer converted2 = converter2.convert("123");
		System.out.println(converted2);    // 123
	}

	/**
	 * 特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
	 */
	@Test
	public void anyMtdTest() {
		cars.forEach(Car::repair);
		Something st = new Something();
		Converter<String, Character> convert = st::startsWith;
		char c = convert.convert("java");
		System.out.println(c);
		
		//等价于
		Converter<String, Character> convert2 = (from) -> from.charAt(0);
		char c2 = convert2.convert("c++");
		System.out.println(c2);
	}

	/**
	 * 特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数
	 */
	@Test
	public void instanceMtdTest() {
		final Car police = Car.create(Car::new);
		cars.forEach(police::follow);
	}
}
