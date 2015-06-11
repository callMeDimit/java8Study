package com.dimit.lambda.clz;

import com.dimit.lambda.inter.FunctionInterface;

public class FunctionInterfaceClz {
	private int a;
	private int b;
	
	public FunctionInterfaceClz(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public boolean excute(FunctionInterface test) {
		return test.test(a, b);
	}
}
