package com.eric.utils;

import java.util.List;

/**
 * @description： 分页工具类
 * @Author: liuBing
 * @DateTime: 2022/5/5 14:45
 */
public class PageUtil {


    /**
     * 开始分页
     *
     * @param list     集合
     * @param pageNum  页码
     * @param pageSize 每页多少条数据
     */
    public static void startPage(List list, Integer pageNum, Integer pageSize) {
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            return;
        }

        // 记录总数
        Integer count = list.size();
        // 页数
        int pageCount = 0;

        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        // 开始索引
        int fromIndex = 0;
        // 结束索引
        int toIndex = 0;

        if( pageNum > pageCount ){
            list.clear();
            return;
        } else if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        list.subList(fromIndex, toIndex);
    }
}
