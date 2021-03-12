package com.eric.test.sql;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuBing
 */
public class I18n {

    public static void main(String[] args) {

    }

    /**
     * 轮播图导入 sql
     */
    @Test
    public void rotationChartI18n() {
        String key = "rotation.chart.";

        String url = "rotation-chart/";

        String sql = "insert into i18n (`key`,`value`,`language`,create_time,update_time) values (";

        List<String> appList = new ArrayList<>() {{

            add("woo");
            add("locku");
            add("blink");

            add("woo-lite");
            add("locku-lite");
            add("blink-lite");

            add("blink-ios");
        }};

        List<String> languageList = new ArrayList<>() {{
            add("en"); // 英语
            add("hi"); // 印地语
            add("fil"); // 菲律宾语
            add("ms"); // 马来语
            add("vi"); // 越南
            add("zh-Hant"); // 繁体中文
            add("id"); // 印尼语
            add("th"); // 泰语
            add("tr"); // 土耳其语
            add("ar"); // 阿拉伯语

            /// add("zh-Hans"); // 简体中文
            /// add("ur"); // 巴基斯坦 乌尔都语
        }};

        for (String app : appList) {
            for (String language : languageList) {
                for (int i = 1; i <= 5; i++) {
                    String newSql = sql + "'" + key + app + "." + i + "' , " +
                            "'" + url + getApp(app) + "/" + language + "/" + i + "' , " +
                            "'" + language + "'" +
                            " , " + "1615521600000 , " +
                            "1615521600000);";
                    System.out.println(newSql);
                }
            }
        }
    }

    private String getApp(String app) {
        switch (app) {
            case "woo-lite":
                return "woo";
            case "blink-lite":
            case "blink-ios":
                return "blink";
            case "luck-ios":
            case "locku-lite":
                return "locku";
            default:
                return app;
        }
    }
}
