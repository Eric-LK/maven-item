package com.eric.ip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.InetAddress;

/**
 * @author liuBing
 */
@Slf4j
public class IPUtils {


    /**
     * 全局静态变量，DatabaseReader，保证类加载时加载一次
     */
    private static DatabaseReader reader;

    /**
     * 静态代码块，保证项目启动只获取一次文件
     */
    static {

        File database = null;

        try {
            //绝对路径读取文件方式
            // /Users/bssm/yuelanProject/maven-item/GeoLite2-ASN.mmdb
            database = new File("/Users/bssm/yuelanProject/maven-item/Country.mmdb");

            // 通过 InputStream 流式读取文件，目的解决无法通过File方式读取jar包内的文件的问题·1
            // database = getFile("GeoLite2-Country.mmdb","GeoLite2-Country.mmdb");
            log.info("-------加载文件");
            reader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 解析IP
     * @param ip
     * @return
     */
    public static IPEntity getIPMsg(String ip){

        IPEntity msg = new IPEntity();

        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
             CountryResponse response = reader.country(ipAddress);

            // AsnResponse asn = reader.asn(ipAddress);



            //  CityResponse city = reader.city(ipAddress);
            // Country country = city.getCountry();

             Country country = response.getCountry();
            /*Subdivision subdivision = response.getMostSpecificSubdivision();
            City city = response.getCity();
            Postal postal = response.getPostal();
            Location location = response.getLocation();*/

            msg.setCountryName(country.getNames().get("zh-CN"));
            msg.setCountryCode(country.getIsoCode());
            /*msg.setProvinceName(subdivision.getNames().get("zh-CN"));
            msg.setProvinceCode(subdivision.getIsoCode());
            msg.setCityName(city.getNames().get("zh-CN"));
            msg.setPostalCode(postal.getCode());
            //经度
            msg.setLongitude(location.getLongitude());
            //纬度
            msg.setLatitude(location.getLatitude());*/

        } catch (IOException | GeoIp2Exception e) {
            e.printStackTrace();
        }

        return msg;
    }


    /**
     * 读取classpath下的文件
     * @param fileName 原文件全名
     * @param newFileName  缓存的新文件的名称
     * @return
     * @throws IOException
     */
    public static File getFile(String fileName, String newFileName) throws IOException {
        //读取 ClassPath 路径下指定资源的输入流
        ClassPathResource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();

        File file = new File(newFileName);

        inputstreamToFile(inputStream, file);

        return file;
    }

    /**
     * InputStream -> File
     * @param inputStream
     * @param file
     */
    private static void inputstreamToFile(InputStream inputStream,File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
