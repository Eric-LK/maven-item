package com.eric.test.transactional;

import com.eric.entity.LookUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liuBing
 */
@Component
@Slf4j
public class MethodB {
    @Autowired
    private com.eric.dao.LookUserMapper LookUserMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addNumber(Integer a) {
        LookUser lookUser = new LookUser();
        lookUser.setUserId(11L);
        lookUser.setNimId(11L);

        LookUserMapper.insert(lookUser);
        if (a == 0) {
            log.info("success");
        }
    }
}
