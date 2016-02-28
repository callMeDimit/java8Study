package com.dimit.reflect;

@SuppressWarnings("unused")
public class Foo {
	private String name;
	private int age;
	private int weight;
	private Sex sex;

	// getter and setter ...
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Foo(String name, int age, int weight, Sex sex) {
		super();
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.sex = sex;
	}
}

enum Sex {
	MAN,
	WOMAN;
}
