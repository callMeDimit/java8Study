package com.dimit.lambda;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

import com.dimit.lambda.clz.FunctionInterfaceClz;

public class LambdaTest {

	@Test
	public void test() {
		Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
		Arrays.asList("a", "b", "d").forEach(
				(String e) -> System.out.println(e));

		Arrays.asList("a", "b", "d").forEach(e -> {
			System.out.print(e + "-");
			System.out.print(e + "+");
		});
	}

	/**
	 * Lambda可以引用类的成员变量与局部变量（如果这些变量不是final的话，它们会被隐含的转为final，这样效率更高）
	 */
	@Test
	public void localVariableTest() {
		String separator = ",";
		Arrays.asList("a", "b", "d").forEach(e -> {
			System.out.print(e + separator);
		});
	}

	@Test
	public void lambdaReturnTest() {
		Arrays.asList("a", "b", "d").sort((e1, e2) -> {
			int result = e1.compareTo(e2);
			return result;
		});
		// 如果lambda的函数体只有一行的话，那么没有必要显式使用return语句。等价于
		Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));

		// 等价于匿名类
		Arrays.asList("a", "b", "d").sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
	}

	/**
	 * 函数式接口测试
	 */
	@Test
	public void functionIntefaceTest() {
		int aa = 6;
		int bb = 6;
		FunctionInterfaceClz clz = new FunctionInterfaceClz(aa, bb);
		boolean result = clz.excute((a, b) -> a > b ? true : false);
		System.out.println(result);
	}

}
