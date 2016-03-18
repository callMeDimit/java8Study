package com.dimit.reflect.tiger.model;

import java.io.Serializable;
import java.util.List;

import com.dimit.reflect.general.model.Customer;
import com.dimit.reflect.general.model.IBaseOrder;
import com.dimit.reflect.general.model.Product;
import com.dimit.reflect.myannotations.Edu_General;
import com.dimit.reflect.myannotations.Edu_Tiger;
import com.dimit.reflect.myenum.BusinessType;

@SuppressWarnings("unused")
public class BaseOrder<M extends Object & Serializable,N extends Comparable<N>> implements IBaseOrder {
	public String payment = "cash";
	private M manufactory;
	private N[] newsLatters;
	private Comparable<? extends Customer> comparator;
	public BaseOrder(){}

	public BaseOrder(M manufactory){
		this.manufactory = manufactory;
	}
	
	public BaseOrder(String payment){
		this.payment = payment;
	}
	
	private static class Usage{
		private String summary;
	}
	
	public class Type{
		
	}
	
	@Edu_General
	@Edu_Tiger
	public M getManufactory(){
		return manufactory;
	}
	
	public String[] getPayments(String[] payments, List<Product> products){
		return payments;
	}
	
	public M[] getPayments(M[] payments){
		return payments;
	}
	
	
	public N[] getNewsLatters(List<BusinessType> newsLatterList){
		return newsLatters;
	}
	
	public int comparator(Comparable<? extends Number> c){
		return 0;
	}

	public Class<String> getClassOfString() {
		return String.class;
	}
}
