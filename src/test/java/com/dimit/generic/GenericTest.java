package com.dimit.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 泛型测试
 * @author gzeyu
 */
public class GenericTest {
	@Test
	@SuppressWarnings("unused")
	public void test() {
		// 数组能够协变
		Integer[] is = new Integer[2];
		Number[] nums = is;
		
		//泛型不支持协变,编译报错
		List<Integer> list = new ArrayList<>();
//		List<Number> numList = list; 
		
		// 注:不能构建泛型数组,因为泛型不能协变而数组能协变,会存在编译错误
		// Person<Integer>[] persons =new Person<Integer> [];
		
		//类型擦除造成如下结果
		List<Integer> ilist = new ArrayList<>();
		List<Number> nList =  new ArrayList<>();
		System.out.println(ilist.getClass().equals(nList.getClass()));
	}
}
