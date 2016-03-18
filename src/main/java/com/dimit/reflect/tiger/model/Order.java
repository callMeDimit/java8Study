/**
 * 
 */
package com.dimit.reflect.tiger.model;

import java.io.Serializable;
import java.util.List;

import com.dimit.reflect.general.model.Customer;
import com.dimit.reflect.general.model.IOrder;
import com.dimit.reflect.general.model.Product;
import com.dimit.reflect.myannotations.Edu_General;
import com.dimit.reflect.myannotations.Edu_Normal;
import com.dimit.reflect.myannotations.Edu_Tiger;

/**
 * @author Shane
 * 
 */
@SuppressWarnings({"serial", "unused"})
@Edu_General
@Edu_Normal
@Edu_Tiger
public class Order extends BaseOrder<Customer, Long> implements IOrder, Serializable {

	private Long id;	
	private Customer[] customers;
	@Edu_General
	public List<Product> products;
		
	public Order(){}
	
	private Order(Long id){
		this.id = id;
	}
		
	protected Order(Customer[] customers, List<Product> products){
		this.customers = customers;
		this.products = products;
	}
	
	@Edu_Tiger
	@Edu_General
	public Order(Long id, Customer[] customers, List<Product> products){
		this.id = id;
		this.customers = customers;
		this.products = products;
	}
	
	private static class ShoppingCart {
		private Integer amount;

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}
	}

	public static class Checker {
		private Integer times;

		public Integer getTimes() {
			return times;
		}

		public void setTimes(Integer times) {
			this.times = times;
		}
	}

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
