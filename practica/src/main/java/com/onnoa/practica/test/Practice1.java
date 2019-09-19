package com.onnoa.practica.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/6 09:15
 */
public class Practice1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("测试");
        String first = list.stream().filter(str -> "测试".equals(str)).findFirst().orElse(null);
        System.out.println(first);

        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < 10; i++) {
            map.put("" + i, "YL");
        }
        //先获取map集合的所有键的Set集合
        Set<String> keySet = map.keySet();
        //System.out.println(keySet);
        //有了Set集合，就可以获取其迭代器。
        Iterator<String> it = keySet.iterator();
        long timeBegin = System.currentTimeMillis();
        while (it.hasNext()) {
            String key = it.next();
            //有了键可以通过map集合的get方法获取其对应的值。
            String value = map.get(key);
            //获得key和value值
           // System.out.println("key: " + key + "-->value: " + value);
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("执行所需时间："+(timeEnd - timeBegin));
        long timeBegin2 = System.currentTimeMillis();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.forEach(a-> System.out.println("for循环遍历："+"key:"+a.getKey()+",value:"+a.getValue()));
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
           // System.out.println("key: " + key + "-->value: " + value);
        }
        System.out.println("执行所需时间："+(System.currentTimeMillis() - timeBegin2));
        //System.out.println(entries);
    }
}
