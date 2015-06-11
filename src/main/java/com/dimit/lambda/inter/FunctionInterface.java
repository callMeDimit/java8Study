package com.dimit.lambda.inter;
/**
 * 函数式接口
 *规则：
 *<pre>
 *	1、只包含一个抽象方法(注意：Object类的方法、default方法、static方法 也可以作为抽象方法声明在函数式接口内)
 *</pre>
 * @author gzeyu
 *
 */
@FunctionalInterface
public interface FunctionInterface {
	/**
	 * 唯一抽象方法
	 * @param a 
	 * @param b
	 * @return
	 */
	boolean test(int a, int b);
	
	/**
	 * Object 内声明的方法
	 * @return
	 */
	String toString();
	
	/**
	 * 默认方法
	 */
	default void defaultMethod() {            
    } 
	
	/**
	 * 默认方法
	 */
	public static void staticMethod() {
	}
}
