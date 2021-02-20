package com.eric.test;

import com.alibaba.fastjson.JSONObject;

import com.eric.entity.Person;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author liuBing
 */
public class MainTest {
    public static void main(String[] args) {



        RestTemplate restTemplate = new RestTemplate();

        String url = "http://ip.qq.com/cgi-bin/searchip?searchip1=";
        String ip = "103.145.184.106";

        String result = restTemplate.postForObject(url + ip, null, String.class);

        JSONObject json = JSONObject.parseObject(result);

        System.out.println(json);

    }

    public static String ipTest() {
        try {
            String strIP = "103.145.184.106";
            URL url = new URL("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line = null;
            StringBuffer result = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            // System.out.println(result);
            JSONObject json = JSONObject.parseObject(result.toString());
            // System.out.println(json);
            String country = (String) json.get("country");
            String province = (String) json.get("province");
            String city = (String) json.get("city");
            return country + province + city;
        } catch (IOException e) {
            return "读取失败";
        }
    }


    @SneakyThrows
    public <T> T leaveMark(T t) {
        Class<? extends T> aClass = (Class<T>) t.getClass();
        Constructor<? extends T> constructor = aClass.getDeclaredConstructor();
        T o = constructor.newInstance();

        Method setSerialMethod = aClass.getMethod("setAge",Integer.class);

        setSerialMethod.invoke(o,123);

        return o;
    }

    @Test
    public  void testTemp(){
        Person person = new Person();
        System.out.println(person.toString());

        Person newPerson = leaveMark(person);

        System.out.println(newPerson.toString());
    }
}
