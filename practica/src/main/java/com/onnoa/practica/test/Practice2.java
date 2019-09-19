package com.onnoa.practica.test;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/10 10:28
 */
public class Practice2 {

    public static void main(String[] args) {
        // 1.要获取一个方法就是获取类的信息，获取类的信息首先要获取类的类类型，要获取print(int ,int )方法
        ReflectTest a1 = new ReflectTest();
        Class c = a1.getClass();

        System.out.println("c:" + c);

        System.out.println("ReflectAdd.class:" + ReflectTest.class);
        // 2.获取方法 名称和参数列表来决定 getMethod获取的是public的方法 getDelcaredMethod自己声明的方法
        try {
            System.out.println("==================调用print(int a, int b)方法");
            // Method m = c.getMethod("print", new Class[]{int.class,int.class});
            Method m = c.getMethod("print", int.class, int.class);
            System.out.println("m:" + m);
            // 方法的反射操作
            // a1.print(10, 20);方法的反射操作是用m对象来进行方法调用 和a1.print调用的效果完全相同
            // 方法如果没有返回值返回null,有返回值返回具体的返回值
            // Object o = m.invoke(a1,new Object[]{10,20});
            Integer o = (Integer) m.invoke(a1, 10, 20);
            System.out.println("a+b:" + o);

            System.out.println("==================调用print(String a, String b)方法");


            // 获取方法print(String,String)
            Method m1 = c.getMethod("print", String.class, String.class);
            System.out.println("m1:" + m1);
            Method print = ReflectTest.class.getMethod("print", String.class, String.class);
            System.out.println("print:" + print);
            // 用方法进行反射操作
            // a1.print("hello", "WORLD");
            String s = (String) m1.invoke(a1, "hello", "WORLD");
            System.out.println("a拼接b:" + s);

            String str = (String) print.invoke(a1, "Hello", "zhang");
            System.out.println("str:" + str);

            System.out.println("===================调用无参的print方法");


            // Method m2 = c.getMethod("print", new Class[]{});
            Method m2 = c.getMethod("print");
            // m2.invoke(a1, new Object[]{});
            m2.invoke(a1);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}
