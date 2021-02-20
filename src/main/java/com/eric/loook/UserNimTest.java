package com.eric.loook;

import com.eric.dao.LookUserMapper;
import com.eric.dao.PersonMapper;
import com.eric.entity.LookUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author liuBing
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserNimTest {


    @Autowired
    private LookUserMapper lookUserMapper;

    @Autowired
    private PersonMapper personMapper;


    @Test
    public void test() {
        List<LookUser> lookUserList = lookUserMapper.selectList(null);

        System.out.println(" lookUserList size : " + lookUserList.size());

        List<LookUser> disUsers = lookUserList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(LookUser::getNimId))),
                        ArrayList::new
                )
        );
        System.out.println("disUser size " + disUsers.size());

        List<Long> userIdList = disUsers.stream().map(LookUser::getUserId).collect(Collectors.toList());

        Map<Long, Integer> map = new HashMap<>();

        userIdList.forEach(userId -> {

            if (map.containsKey(userId)) {
                map.put(userId, map.get(userId) + 1);
            } else {
                map.put(userId, 0);
            }

        });

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<Long, Integer> longIntegerEntry : map.entrySet()) {
            if (longIntegerEntry.getValue() > 0) {
                stringBuilder.append(longIntegerEntry.getKey()).append(",");
                System.out.println(longIntegerEntry.getKey() + ":" + longIntegerEntry.getValue());
            }
        }

        System.out.println(stringBuilder.toString());

        /*    List<LookUser> disUsers_1 = disUsers.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(LookUser::getUserId))), ArrayList::new)
        );

        System.out.println(disUsers_1.size());*/
    }

    @Test
    public void test02() {
        int a = personMapper.updateUserName(123, "hahha");
        System.out.println(a);
    }
}
