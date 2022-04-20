//package com.eric.zxl;
//
///**
// * @description：
// * @Author: liuBing
// * @DateTime: 2022/1/25 10:25
// */
//
//import com.benmu.mts.booking.sample.util.ChannelExecutors;
//import com.benmu.mts.booking.sample.util.RedissonDistributedDispatch;
//import com.benmu.root.bom.bundles.config.MValue;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.time.LocalDateTime;
//import java.util.Set;
//
///**
// * @author wulinjiang
// * @since 2018-05-03
// */
//@Component
//public class SampleSyncTask {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Resource
//    private RedissonDistributedDispatch redissonDispatch;
//
//    @Resource
//    private SampleSyncService sampleSyncService;
//
//    @MValue("sample.sync.task.hosCodes")
//    private Set<String> syncHosCodes;
//
//    private final static String SAMPLE_SYNC_KEY = "SAMPLE_SYNC_TASK";
//
//    @Scheduled(cron = "0 0/1 * * * ? ")
//    public void sync() {
//        logger.info("sample_sync_start");
//        LocalDateTime localDateTime = LocalDateTime.now();
//        String key = SAMPLE_SYNC_KEY + localDateTime.getDayOfYear() + localDateTime.getHour() + localDateTime.getMinute() + Math.floor(localDateTime.getSecond() / 10);
//        addLockAndSync(key);
//    }
//
//    public void addLockAndSync(String key) {
//        for (String hosCode : syncHosCodes) {
//            logger.info("sample_sync 检验报告同步医院数据，hosCode:{}",hosCode);
//            ChannelExecutors.runTask(() -> syncExecute(key,hosCode));
//        }
//    }
//
//    private void syncExecute(String key,String hosCode){
//        try {
//            redissonDispatch.addLockAndSync(key,hosCode);
//        } catch (Exception e){
//            logger.error("检验报告同步医院数据异常，hosCode {}，key{},error {}",hosCode,key,e);
//        }
//    }
//}
