package com.eric.test.transactional;


import com.eric.dao.LookUserMapper;
import com.eric.entity.LookUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuBing
 */
@Component
@SpringBootTest
@RunWith(SpringRunner.class)
public class RequiresNew {


    @Autowired
    private MethodB methodB;
    @Autowired
    private com.eric.dao.LookUserMapper LookUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Test
    public void test() {
        LookUser lookUser = new LookUser();
        lookUser.setUserId(1883L);
        lookUser.setNimId(123321L);

        LookUserMapper.insert(lookUser);
        methodB.addNumber(0);

        throw new RuntimeException("123");

    }
}
