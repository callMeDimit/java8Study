package com.dimit.unsafe;

public class Guard {
	private int ACCESS_ALLOWED = 1;

	public boolean giveAccess() {
		return 42 == ACCESS_ALLOWED;
	}
}