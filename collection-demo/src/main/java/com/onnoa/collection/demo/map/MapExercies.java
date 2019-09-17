package com.onnoa.collection.demo.map;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/12 11:06
 */
public class MapExercies {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("msgType", "WXPay.h5Pay");
        map.put("merAppId", "http://www.wangzhenongyao.com");
        map.put("requestTimestamp", "2017-11-14 19:41:09");

        System.out.println(map.keySet()+"===="+map.entrySet()+"========"+map.values());

        System.out.println("拼接后的url地址："+"http://www.baidu.com"+"?"+buildUrlParametersStr(map));
       /* String requestUrl = buildUrlParametersStr(map);
        System.out.println("requestUrl:" + requestUrl);*/


       /* System.out.println(map);
        System.out.println("通过Map.keySet 遍历进行取值");
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());*/

        // =========== 第一种遍历 ================
        /*for (String key : map.keySet()) {
            System.out.println("key:" + key + ",value:" + map.get(key));
        }*/

        // =========== 第二种遍历 ================

        /*for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null || value == null) {
                continue;
            }
            System.out.println(key);
            System.out.println(value);
            //System.out.println(iterator.next());

        }*/

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时</span>
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }


    }

    public static String buildUrlParametersStr(Map<String, String> paramMap) {
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();

        for (Iterator iterator = paramMap.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (Map.Entry) iterator.next();
            if (entry.getKey() == null) {
                continue;
            }
            sb.append(entry.getKey().toString()).append("=");
            try {
                if (entry.getValue() != null && StringUtils.isNotBlank(entry.getValue().toString())) {
                    String encode = URLEncoder.encode(entry.getValue().toString(), "UTF-8");
                    sb.append(encode);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            sb.append(iterator.hasNext() ? "&" : "");
            //sb = iterator.hasNext() ? sb.append("&") : sb.append("");

        }
        return sb.toString();
    }
}
