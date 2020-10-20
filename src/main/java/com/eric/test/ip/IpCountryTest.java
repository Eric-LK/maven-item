package com.eric.test.ip;

import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import org.apache.http.client.params.HttpClientParams;

import java.net.InetAddress;

/**
 * @author liuBing
 */
public class IpCountryTest {

    public static void main(String[] args) {
        try{

            /*WebServiceClient client = new WebServiceClient.Builder(42, "license_key").build();
            InetAddress ipAddress = InetAddress.getByName("128.101.101.101");

            CountryResponse response = client.country(ipAddress);
            Country country = response.getCountry();
            System.out.println("ip:" + country.toString());*/

            IPEntity ipEntity = IPUtils.getIPMsg("103.23.28.16");
            System.out.println(ipEntity.toString());



        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
