/**
 * 
 */
package com.dimit.reflect.general.model;

import java.io.Serializable;

import com.dimit.reflect.myenum.BusinessType;

/**
 * @author Shane
 * 
 */
@SuppressWarnings({"serial","unused"})
public class Customer implements Comparable<Customer>,Serializable{
	private Long id;
	private BusinessType type;
	public BusinessType getType() {
		return type;
	}
	public void setType(BusinessType type) {
		this.type = type;
	}
	public int compareTo(Customer o) {
		return this.type.compareTo(o.getType());
	}

}
