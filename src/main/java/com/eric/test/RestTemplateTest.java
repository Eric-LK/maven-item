package com.eric.test;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author liuBing
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateTest {

    // RestTemplate restTemplate = new RestTemplate();
    @Test
    @SneakyThrows
    public void s2sTest() {
        ExceptionTest exceptionTest = new ExceptionTest();
        Integer a = exceptionTest.getValue(0, 0);



    }

    public static void main(String[] args) {

    }
}
