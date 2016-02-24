package com.dimit.unsafe;

public class Player {
	private int age = 12;  
	  
    private Player() {  
        this.age = 50;  
        System.out.println("-----player 构造方法--------");
    }  
  
    public int getAge() {  
        return this.age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
}
