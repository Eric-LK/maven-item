package com.eric.test.interfacee;

/**
 * @author liuBing
 */
public interface Test01 {

    default String getName(String age){
        return age + "1";
    }
}
