package com.dimit.generic;
import java.util.List;
 
@SuppressWarnings("rawtypes")
public class ArrayBean {
	public List[] arrayType;
    public List<String>[] genericArrayType;
    public List<String>[][] genericMultiArrayType;
    public List<SomeBean<String, Integer>>[][] specialMultiArrayType;
}