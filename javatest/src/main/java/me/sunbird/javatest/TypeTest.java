package me.sunbird.javatest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王豪
 * 日期：2021/12/13
 * 描述：<必填>
 */

class TypeTest<SS extends String> {

    //    public static List<String> items = new ArrayList<String>();
    List<TestLong> ss = new ArrayList<TestLong>();

    public static void main(String[] args) {
        List<String> items = new ArrayList<String>();


//        new TypeToken<String>(){};
//        items.add("aaaa");
//        System.out.println(items.get(0));
//        TypeVariable[] typeVariables = items.getClass().getTypeParameters();
//        for (TypeVariable typeVariable : typeVariables) {
//            System.out.println("typeVariable:" + typeVariable.getName());
//        }

//        Method[] methods = items.getClass().getMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//        showActualType(items);

//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        int sum = 0;
//        for (int i : list) {
//            sum += i;
//        }
//        System.out.println(sum);


//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3L;
//
//        boolean isEqual = c == d;
//
//        System.out.println(isEqual);//true
//        System.out.println(e == f);//false
//        System.out.println(c == (a + b));//true
//        System.out.println(c.equals(a + b));//true
//        System.out.println(g == (a + b));//true
//        System.out.println(g.equals(a + b));//false

//        Integer c = 3;
//        Integer d = 3;
//        boolean isEqual = c == d;

        if (true) {
            System.out.println("block 1");
        } else {
            System.out.println("block 2");
        }

    }

    public static void showActualType(Object o) {
        Type clazz = o.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz;
        Type[] types = parameterizedType.getActualTypeArguments();
        for (Type type : types) {
            System.out.println(type.toString());
        }
    }
}
