package com.eric.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author liuBing
 */
public class CompletableFutureTest {


    public static void main(String[] args) {


    }

    public void test(){
        List<String> a = new ArrayList<>() {{
            add("123");
            add("222");
        }};

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> a.forEach(b -> {
            print01.apply(b);
            print02(b);
        })).whenComplete((r,e) ->{
            System.out.println(r +e.toString());
        });
    }


    private  Function<String, String> print01 = (param) -> {
        System.out.print(param);
        return param + "return";
    };

    private String print02(String param) {
        System.out.print(param);
        return param + "return";
    }
}
