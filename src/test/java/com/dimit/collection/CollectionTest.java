package com.dimit.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CollectionTest {

	/**
	 * Collection.removeIf方法测试
	 */
	@Test
	public void removeIfTest() {
		List<String> list = new ArrayList<String>( Arrays.asList("a", "b", "c"));
		list.removeIf((u)-> {
			return u.equals("a");
		});
		System.out.println(list);
	}

}
