/**
 * 
 */
package com.dimit.reflect.tiger.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.dimit.reflect.general.model.Customer;
import com.dimit.reflect.myenum.BusinessType;


/**
 * @author Shane
 *
 */
@SuppressWarnings({"rawtypes","unused"})
public class SalePolicy<T extends Serializable & Comparable<? super T>> {
//	private static T customer;
	private T customer;
	private Set<T> customerHolder;
	private double price;
	
//	static {
//		customerHolder = new HashSet<T>();
//	}

//	public static T getCustomer(){
//		return customer;
//	}

//	public static <E extends T> Integer getCustomerNumber(E number){
//		return 0;
//	}

	public static <E> Integer getCustomerNumber(E number){
		return 0;
	}
	
	{
		customerHolder = new HashSet<T>();
	}
	
	public SalePolicy(){
	}
	
	public SalePolicy(T customer){
		customerHolder.add(customer);
	}
	

	public <A extends Account > SalePolicy(A account){
	
	}
	
	public static <B extends Enum, S extends Serializable> Customer getSpcialCustomer(List<B> types, S serial){
		return new Customer();
	}
	
	public T getCustomer(){		
		customer = null;
		if(!customerHolder.isEmpty()){
			Iterator<T> iterator = customerHolder.iterator();
			if(iterator.hasNext()){
				customer = iterator.next();
				customerHolder.remove(customer);
			}
		}
		return customer;
	}
	
	public void addCustomer(T customer){
		customerHolder.add(customer);
	}

}
