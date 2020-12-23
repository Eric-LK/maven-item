package com.eric.utils;

import java.io.File;
import java.net.InetAddress;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

/**
 * @Description: geoip工具类
 */
public class GeoIpUtils {

    private final static Logger logger = LoggerFactory.getLogger(GeoIpUtils.class);

    private static DatabaseReader reader;

    private static DatabaseReader getReader(){
        try{
            if(reader == null){
                logger.warn("打开ip数据库");
                // 附件下载百度云地址https://pan.baidu.com/s/1ENqTeCoMIWJMbh88nYU5gg
                File database =  new File(Objects.requireNonNull
                        (GeoIpUtils.class.getClassLoader().getResource("geoip/GeoLite2-Country.mmdb")).getFile());
                reader = new DatabaseReader.Builder(database).build();
            }
            return reader;
        }catch(Exception e){
            return reader;
        }

    }

    /**
     * 根据ip获取国家对象,不存在则返回null
     * @param ip ip
     * @return 结果
     */
    public static Country getCountry(String ip){
        try{
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = getReader().country(ipAddress);
            return response.getCountry();
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 根据ip获取国家代码,不存在则返回null
     * @param ip ip地址
     * @return 结果
     */
    public static String getCountryCode(String ip){
        Country country = getCountry(ip);
        return country != null ? country.getIsoCode() : null;
    }

    /**
     * 根据ip获取国家名称,不存在则返回null
     * @param ip ip地址
     * @return 结果
     */
    public static String getCountryName(String ip){
        Country country = getCountry(ip);
        return country != null ? country.getName() : null;
    }
}