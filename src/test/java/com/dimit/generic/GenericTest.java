package com.dimit.generic;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.dimit.reflect.general.model.Customer;
import com.dimit.reflect.general.model.Product;
import com.dimit.reflect.tiger.model.BaseOrder;
import com.dimit.reflect.tiger.model.Order;

import junit.framework.Assert;

/**
 * 泛型测试
 * @author gzeyu
 */
public class GenericTest {
	@Test
	@SuppressWarnings("unused")
	public void test() {
		// 数组能够协变
		Integer[] is = new Integer[2];
		Number[] nums = is;
		
		//泛型不支持协变,编译报错
		List<Integer> list = new ArrayList<>();
//		List<Number> numList = list; 
		
		// 注:不能构建泛型数组,因为泛型不能协变而数组能协变,会存在编译错误
		// Person<Integer>[] persons =new Person<Integer> [];
		
		//类型擦除造成如下结果
		List<Integer> ilist = new ArrayList<>();
		List<Number> nList =  new ArrayList<>();
		System.out.println(ilist.getClass().equals(nList.getClass()));
	}
	
	/**
	 * TypeVariable 测试
	 * TypeVariable就是用来反映在JVM编译该泛型前的信息
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@Test
	public void typeTest() throws NoSuchFieldException, SecurityException {
		Field manufactoryField = BaseOrder.class.getDeclaredField("manufactory");     
		Type type = manufactoryField.getGenericType();  
		Assert.assertTrue("The type of field manufactory is an instance of TypeVariable", type instanceof TypeVariable);  
		TypeVariable<?> tType = (TypeVariable<?>)type;  
		Assert.assertEquals("The name of this TypeVariable is M", "M", tType.getName());  
		Assert.assertEquals("The TypeVariable bounds two type", 2, tType.getBounds().length);   
		Assert.assertEquals("One type of these bounds is Object", Object.class, tType.getBounds()[0]);   
		Assert.assertEquals("And annother si Serializable", Serializable.class, tType.getBounds()[1]);  
	}

	/**
	 * ParameterizedType
	 * getGenericSuperclass()方法首先会判断是否有泛型信息，有那么返回泛型的Type，没有则返回Class
	 */
	@Test
	public void parameterizedTypeTest() {
		// getGenericSuperclass 获取带泛型的父类信息(编译前还未擦除的class信息)
		Type genericSuperclass = Order.class.getGenericSuperclass();
		System.out.println(genericSuperclass);
		Assert.assertNotSame("this type same with BaseOrder.class", BaseOrder.class, genericSuperclass);
		// 获取不带泛型的父类信息(编译后的class信息)
		Class<?> superclass = Order.class.getSuperclass();
		System.out.println(superclass);
		Assert.assertEquals("this type same with BaseOrder.class", BaseOrder.class, superclass);
		
		Assert.assertTrue("Order's supper class is a type of ParameterizedType.", genericSuperclass instanceof ParameterizedType);
		ParameterizedType pType = (ParameterizedType) genericSuperclass;
		// 通过getRawType方法获取编译后的类型
		Assert.assertEquals("Order's supper class is BaseOrder.", BaseOrder.class, pType.getRawType());
		//获取泛型信息
		Type[] arguments = pType.getActualTypeArguments();
		Assert.assertEquals("getActualTypeArguments() method return 2 arguments.", 2, arguments.length);
		for (Type type : arguments) {
			Class<?> clazz = (Class<?>) type;
			if (!(clazz.equals(Customer.class)) && !(clazz.equals(Long.class))) {
				Assert.assertTrue(false);
			}
		}
	}
	
	/**
	 * ParameterizedType 测试
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void parameterizedTypeTypeTest() throws NoSuchMethodException, SecurityException {
		Method getPayments = BaseOrder.class.getMethod("getPayments", String[].class, List.class);  
		Type[] types = getPayments.getGenericParameterTypes();
		Assert.assertTrue("The method getPayments args amount not matcher",types.length == 2);
		Assert.assertTrue("The first parameter of this method isn't GenericArrayType.", types[1] instanceof ParameterizedType);  
		ParameterizedType gType = (ParameterizedType)types[1];  
		Type[] actualTypeArguments = gType.getActualTypeArguments();
		Assert.assertEquals("泛型个数不为1", actualTypeArguments.length, 1);
		Assert.assertEquals("The GenericArrayType's component is String.", Product.class, actualTypeArguments[0]);  
	}
	
	/**
	 * WildcardType 通配符泛型信息类型 通过该类型可以取得统配符的上下界
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws NoSuchFieldException 
	 */
	@Test
	public void wildcardTypeTest() throws NoSuchMethodException, SecurityException, NoSuchFieldException {
		Field declaredField = ListGenericFoo.class.getDeclaredField("fooArray");
		Type genericType = declaredField.getGenericType();
		Assert.assertTrue(GenericArrayType.class.isAssignableFrom(genericType.getClass()));
		GenericArrayType genericaArrType = (GenericArrayType) genericType;
		Type genericComponentType = genericaArrType.getGenericComponentType();
		Assert.assertTrue(TypeVariable.class.isAssignableFrom(genericComponentType.getClass()));
		TypeVariable<?> typeVariable = (TypeVariable<?>) genericComponentType;
		System.out.println(typeVariable.getName());
		for(Type type : typeVariable.getBounds()) {
			System.out.println(type);
		}
		System.out.println(typeVariable.getGenericDeclaration());
		
		//list
		Field field = ListGenericFoo.class.getDeclaredField("list");
		Type genericType2 = field.getGenericType();
		Assert.assertTrue(ParameterizedType.class.isAssignableFrom(genericType2.getClass()));
		ParameterizedType parameterizedType = (ParameterizedType) genericType2;
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		Assert.assertTrue(actualTypeArguments.length == 1);
		Assert.assertTrue(WildcardType.class.isAssignableFrom(actualTypeArguments[0].getClass()));
		WildcardType wildcardType = (WildcardType) actualTypeArguments[0];
		System.out.println(wildcardType.getUpperBounds()[0]);
		System.out.println(wildcardType.getLowerBounds().length);
	}
	
	
	/**
	 * GenericArrayType
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@Test
	public void genericArrayTypeTest() throws NoSuchFieldException, SecurityException {
		printFieldType(ArrayBean.class, "arrayType");
        System.out.println("\n");
        printFieldType(ArrayBean.class, "genericArrayType");
        System.out.println("\n");
        printFieldType(ArrayBean.class, "genericMultiArrayType");
        System.out.println("\n");
        printFieldType(ArrayBean.class, "specialMultiArrayType");
	}
	
	/**
	 * 泛型获取综合测试
	 */
	@Test
	public void genericTest() {
		Class<?> c = BaseOrder.class;
		TypeVariable<?>[] typeParameters = c.getTypeParameters();
		Assert.assertTrue(typeParameters.length == 2);
		//获取M相关信息
		TypeVariable<?> typeVariable = typeParameters[0];
		System.out.println(typeVariable.getName());
		System.out.println(typeVariable.getGenericDeclaration());
		Type[] bounds1 = typeVariable.getBounds();
		Assert.assertTrue(bounds1.length == 2);
		Assert.assertEquals(Object.class, bounds1[0]);
		Assert.assertEquals(Serializable.class, bounds1[1]);
		//获取N相关信息
		TypeVariable<?> typeVariable1 = typeParameters[1];
		System.out.println(typeVariable1);
		Type[] bounds2 = typeVariable1.getBounds();
		Assert.assertTrue(bounds2.length == 1);
		Assert.assertTrue(ParameterizedType.class.isAssignableFrom(bounds2[0].getClass()));
		ParameterizedType type = (ParameterizedType) bounds2[0];
		System.out.println(type.getRawType());
		System.out.println(type.getOwnerType());
		// Comparable
		Type[] actualTypeArguments = type.getActualTypeArguments();
		Assert.assertTrue(actualTypeArguments.length == 1);
		Assert.assertTrue(TypeVariable.class.isAssignableFrom(actualTypeArguments[0].getClass()));
		TypeVariable<?> variable = (TypeVariable<?>) actualTypeArguments[0];
		System.out.println(variable.getName());
		System.out.println("----" + variable.getBounds().length);
		Type type2 = variable.getBounds()[0];
		Assert.assertTrue(ParameterizedType.class.isAssignableFrom(type2.getClass()));
		ParameterizedType t = (ParameterizedType) type2;
		System.out.println(t.getRawType());
	}
	
	
	private void printFieldType(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException {
        System.out.println("Type details of field " + fieldName);
        Field field = clazz.getField(fieldName);
        System.out.println("Declared class: " + field.getDeclaringClass());
        Type genericType = field.getGenericType();
        System.out.println("Generic type: " + genericType.getTypeName());
        Type type = field.getType();
        System.out.println("Type: " + type.getTypeName());
        if (isGenericArrayType(genericType)) {
            printGenericFieldType((GenericArrayType) genericType);
        }
        if (isParameterizedType(genericType)) {
            printParameterizedType((ParameterizedType) genericType);
        }
    }
 
    private void printGenericFieldType(GenericArrayType genericArrayType) {
        Type componentType = genericArrayType.getGenericComponentType();
        System.out.println("Component type of : "
                + genericArrayType.getTypeName() + " is "
                + componentType.getTypeName());
        if (isGenericArrayType(componentType)) {
            printGenericFieldType((GenericArrayType) componentType);
        }
        if (isParameterizedType(componentType)) {
            printParameterizedType((ParameterizedType) componentType);
        }
    }
 
    private boolean isGenericArrayType(Type type) {
        if (GenericArrayType.class.isAssignableFrom(type.getClass())) {
            System.out.println("Is GenericArrayType ? true");
            return true;
        }
        return false;
    }
 
    private boolean isParameterizedType(Type type) {
        return ParameterizedType.class.isAssignableFrom(type.getClass());
    }
 
    private void printParameterizedType(ParameterizedType parmType) {
        System.out.println("Parameterized type details of " + parmType); 
        System.out.println("Type name " + parmType.getTypeName());
        System.out.println("Raw type: " + parmType.getRawType());
        System.out.println("Actual type arguments: "
                + Arrays.asList(parmType.getActualTypeArguments()));
        for (Type type : parmType.getActualTypeArguments()) {
            if (isParameterizedType(type)) {
                printParameterizedType((ParameterizedType) type);
            }
        }
    }
}
