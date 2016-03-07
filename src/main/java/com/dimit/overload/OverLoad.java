package com.dimit.overload;

import java.io.Serializable;

public class OverLoad {
	/*public void sayHi(char a) {
		System.out.println("char");
	}*/

	/*public void sayHi(int a) {
		System.out.println("int");
	}*/
	/*public void sayHi(Integer a) {
		System.out.println("Integer");
	}*/

	/*public void sayHi(long a) {
		System.out.println("long");
	}*/

	/*public static void sayHi(Character a) {
		System.out.println("Character");
	}*/

	/*public static void sayHi(Comparable<Character> a) {
		System.out.println("Comparable");
	}*/
	
	public void sayHi(Serializable a) {
		System.out.println("Serializable");
	}

	public void sayHi(Object a) {
		System.out.println("Object");
	}
	public void sayHi(char... a) {
		System.out.println("char ...");
	}
	
	/**
	 * 方法执行顺序:char->int->long->Character->serializable(Comparable)->Object->char[]
	 * 因为Character类将serializable(Comparable)都实现了，并且这两个借口处于同一层继承图谱中，所以当只有这两个参数方法时会要求进行强转
	 * @param args
	 */
	public static void main(String[] args) {
		new OverLoad().sayHi('a');
	}
}
