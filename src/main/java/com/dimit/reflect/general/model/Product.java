/**
 * 
 */
package com.dimit.reflect.general.model;

/**
 * @author Shane
 *
 */
@SuppressWarnings("unused")
public class Product implements Comparable<Product>{
	private Long id;
	private String name;
	private Float price;
	
	public int compareTo(Product o) {		
		return o.getPrice().compareTo(this.price);
	}

	public Float getPrice() {
		return price;
	}
	
}
