package com.eric.test.ip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuBing
 */
public class IpCountryTest {

    public static void main(String[] args) {

        List<String> ipList = new ArrayList<>();
        ipList.add("103.152.100.137");
        ipList.add("103.104.214.230");
        ipList.add("103.140.30.74");
        ipList.add("103.135.8.224");
        ipList.add("123.108.92.132");
        ipList.add("103.102.58.238");
        ipList.add("103.149.240.154");
        ipList.add("103.150.239.48");
        ipList.add("103.150.154.50");
        ipList.add("103.150.239.79");
        ipList.add("103.152.100.44");

        AtomicInteger success = new AtomicInteger();
        AtomicInteger failed = new AtomicInteger();

        ipList.forEach(ip -> {
            try {
                IPEntity ipEntity = IPUtils.getIPMsg(ip);
                success.getAndIncrement();
                System.out.println(ipEntity.toString());
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("failed ip: " + ip);
                failed.getAndIncrement();
            }
        });

        System.out.println("success : " + success + "failed : " + failed);


    }
}
