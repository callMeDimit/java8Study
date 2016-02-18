package com.dimit.generic;

public abstract class Person<T extends Number> {
	private T num;

	public T getNum() {
		return num;
	}

	public void setNum(T num) {
		this.num = num;
	}

}
