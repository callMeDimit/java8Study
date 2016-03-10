package com.dimit.clazzloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

public class ClassLoaderTest {

	@Test
	public void test()
			throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		String path = "file:E:/jar/javassist/ws";
		URI uri = URI.create(path);
		URLClassLoader cl1 = URLClassLoader.newInstance(new URL[] { uri.toURL() });
		Class<?> c1 = cl1.loadClass("com.dimit.clazzloader.Sample");
		Object o1 = c1.newInstance();

		URLClassLoader cl2 = URLClassLoader.newInstance(new URL[] { uri.toURL() });
		Class<?> c2 = cl2.loadClass("com.dimit.clazzloader.Sample");
		Object o2 = c2.newInstance();

		Method method = c1.getMethod("setSample", Object.class);
		method.invoke(o1, o2);
	}

}
