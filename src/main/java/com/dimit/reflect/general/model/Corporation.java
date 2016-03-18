/**
 * 
 */
package com.dimit.reflect.general.model;

import java.io.Serializable;

/**
 * @author Shane
 * 
 */
@SuppressWarnings("serial")
public class Corporation extends Customer implements Serializable {
	private Long id;

	private String name;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Corporation o) {
		return this.name.compareTo(o.getName());
	}

}
