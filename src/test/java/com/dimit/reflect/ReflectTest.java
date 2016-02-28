package com.dimit.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Before;
import org.junit.Test;

public class ReflectTest {
	private BeanInfo info;
	private Foo foo;

	@Before
	public void beforClz() {
		try {
			info = Introspector.getBeanInfo(Foo.class);
			foo = new Foo("zhangsan", 12, 40, Sex.MAN);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void getAllPropertyDesTest() {
		PropertyDescriptor[] pdArray = info.getPropertyDescriptors();
		List<PropertyDescriptor> pds = Arrays.asList(pdArray);
		pds.stream().forEach(p -> {
			if("class".equals(p.getName())) {
				try {
					Object mtd = p.getReadMethod().invoke(info);
					System.out.println(mtd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("属性名称:" + p.getName() + "_" + p.getDisplayName());
			
			//暂时不知道有什么用
			Enumeration<String> attrEnum = p.attributeNames();
			if(attrEnum.hasMoreElements()){
				System.out.println(attrEnum.nextElement());
			}
			
			System.out.println("取得属性的class对象" + p.getPropertyType());
			
			Method getMtd = p.getReadMethod();
			Method setMtd = p.getWriteMethod();
			try {
				if(getMtd == null) {
					System.out.println(p.getName() + "属性没有get方法");
				} else {
					System.out.println("取得该属性的get方法" + getMtd.getName());
					Object val = getMtd.invoke(foo);
					System.out.println(p.getName() + "的值为:" + val);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("--------------------");
			
		});
	}

}
