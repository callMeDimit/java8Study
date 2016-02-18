package com.dimit.generic;

public class Foo <T extends Cloneable> {
	
	public void dosomthing(T para) {
//		T copy = new T();  编译不通过
	}
}
