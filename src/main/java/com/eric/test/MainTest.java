package com.eric.test;

import com.alibaba.fastjson.JSONArray;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuBing
 */
public class MainTest {


    public static void main(String[] args) {

        Integer[] test = new Integer[2];
        Class<Object>[] test01 = new Class[2];

        /*  RestTemplate restTemplate = new RestTemplate();

        String url = "http://ip.qq.com/cgi-bin/searchip?searchip1=";
        String ip = "103.145.184.106";

        String result = restTemplate.postForObject(url + ip, null, String.class);

        JSONObject json = JSONObject.parseObject(result);

        System.out.println(json);*/

        int[] param = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(param));

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
            /// System.out.println(result);
            JSONObject json = JSONObject.parseObject(result.toString());
            /// System.out.println(json);
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

        Method setSerialMethod = aClass.getMethod("setAge", Integer.class);

        setSerialMethod.invoke(o, 123);

        return o;
    }

    @Test
    public void testTemp() {
        Person person = new Person();
        System.out.println(person.toString());

        Person newPerson = leaveMark(person);

        System.out.println(newPerson.toString());

        JSONArray a = new JSONArray();
        ;
        for (Object o : a) {

        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int x = j + 1; x < nums.length; x++) {
                    if (nums[i] + nums[j] + nums[x] == 0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[x]);

                        Collections.sort(temp);

                        boolean flag = true;

                        if (!result.isEmpty()) {
                            for (List<Integer> a : result) {

                                flag = flag && twoListCompareUtil(a,temp);
                            }

                        }

                        if (result.isEmpty() || flag) {
                            result.add(temp);
                        }

                    }
                }
            }
        }
        return result;
    }

    public static boolean twoListCompareUtil(List<Integer> a,List<Integer> b){
        for (int n = 0; n < a.size(); n++) {
            if (!a.get(n).equals(b.get(n))) {
                return true;
            }
        }
        return false;
    }

}
