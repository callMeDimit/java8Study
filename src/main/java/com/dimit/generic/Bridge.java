package com.dimit.generic;

public abstract class Bridge <T extends Number> {
	@SuppressWarnings("unused")
	private  T str;

	public T getStr(T S) {
		return S;
	}
}
