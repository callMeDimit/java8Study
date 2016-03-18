package com.dimit.generic;

import java.util.List;

public class ListGenericFoo<T extends List<String>> {
	private T[] fooArray;
	private List<?> list; 
	public T[] getFooArray() {
		return fooArray;
	}

	public void setFooArray(T[] fooArray) {
		this.fooArray = fooArray;
	}

	public List<?> getList() {
		return list;
	}
}