//package com.eric.zxl;
//
///**
// * @description：
// * @Author: liuBing
// * @DateTime: 2022/1/25 10:24
// */
//
//
//import com.benmu.api.json.JsonUtil;
//import com.benmu.api.pojo.BizException;
//import com.benmu.mts.booking.api.bean.Pair;
//import com.benmu.mts.booking.sample.sync.SampleSyncService;
//import com.benmu.mts.booking.sample.sync.TaskSyncQuery;
//import com.benmu.util.Safes;
//import de.danielbechler.util.Collections;
//import org.redisson.api.RBucket;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Function;
//
///**
// * @Author: zhangxinling
// * @Date: 2021/6/15 19:11
// * @Description:
// */
//@Component
//public class RedissonDistributedDispatch {
//
//    private final Logger logger = LoggerFactory.getLogger(RedissonDistributedDispatch.class);
//
//    @Resource
//    private RedissonClient redissonClient;
//
//    @Resource
//    private SampleSyncService sampleSyncService;
//
//    /**
//     * 加锁时间
//     */
//    private final static Integer LOCK_EXPIRE = 3;
//    /**
//     * redis缓存时间
//     */
//    private final static Integer EXPIRE_TIME = 10;
//
//    /**
//     * 每次调度的偏移量
//     */
//    public final static Integer OFFSET = 100;
//
//    /**
//     * key前缀
//     */
//    private final static String SAMPLE_INDEX = "SAMPLE_INDEX";
//    private final static String SAMPLE_ID_LIST = "SAMPLE_ID_LIST";
//
//
//    public void addLockAndSync(String key, String hosCode) {
//        TaskSyncQuery query = new TaskSyncQuery();
//        query.setKey(key);
//        query.setHosCode(hosCode);
//        String queryJson = JsonUtil.toJson(query);
//        Pair<Integer, List<Integer>> pair = (Pair<Integer, List<Integer>>) addLock(this::fromIndex, queryJson);
//        // 索引下标
//        Integer fromIndex = pair.getLeft();
//        // 需要处理的数据id
//        List<Integer> idList = pair.getRight();
//        // 任务队列长度
//        Integer sampleShared = idList.size();
//        int toIndex;
//
//        //开始下标小于列表大小，就可以继续分配
//        while (fromIndex < sampleShared) {
//            //结束下标，不能大于列表大小
//            toIndex = fromIndex + OFFSET;
//            if (toIndex >= sampleShared) {
//                toIndex = sampleShared;
//            }
//            // 根据获取的数据执行拉取检验单
//            List<Integer> shardNotifyId = idList.subList(fromIndex, toIndex);
//            sampleSyncService.taskSync(shardNotifyId);
//
//            pair = (Pair<Integer, List<Integer>>) addLock(this::fromIndex, queryJson);
//            fromIndex = pair.getLeft();
//        }
//    }
//
//
//    /**
//     * 获取数据分布式调度的
//     *
//     * @param param
//     * @return
//     */
//    private Pair<Integer, List<Integer>> fromIndex(Object param) {
//        if (param == null) {
//            logger.error("sample_sync 检验报告同步参数异常");
//        }
//        TaskSyncQuery query = JsonUtil.of(param.toString(), TaskSyncQuery.class);
//        String key = query.getKey();
//        String idListKey = SAMPLE_ID_LIST  + query.getHosCode() + key;
//        RBucket<List<Integer>> listRBucket = redissonClient.getBucket(idListKey);
//        List<Integer> idList = new ArrayList<>();
//        if (!listRBucket.isExists()) {
//            idList = Safes.of(sampleSyncService.allNotifyId(query.getHosCode()));
//            listRBucket.set(idList, EXPIRE_TIME, TimeUnit.MINUTES);
//        } else {
//            idList = listRBucket.get();
//        }
//        if (Collections.isEmpty(idList)) {
//            return Pair.of(0, idList);
//        }
//
//        String indexKey = SAMPLE_INDEX + query.getHosCode() + key;
//        RBucket<Integer> indexBucket = redissonClient.getBucket(indexKey);
//        int index = 0;
//        if (!indexBucket.isExists()) {
//            indexBucket.set(OFFSET, EXPIRE_TIME, TimeUnit.MINUTES);
//        } else {
//            index = indexBucket.get();
//            indexBucket.set(index + OFFSET, EXPIRE_TIME, TimeUnit.MINUTES);
//        }
//        logger.info("sample_sync 获取缓存下标{},idList {}", index, JsonUtil.of(idList));
//
//        return Pair.of(index, idList);
//    }
//
//
//    /**
//     * 获取分布式锁
//     *
//     * @param function
//     * @return
//     */
//    private Object addLock(Function<Object, Object> function, String param) {
//        TaskSyncQuery query = JsonUtil.of(param, TaskSyncQuery.class);
//        RLock lock = redissonClient.getLock(query.getHosCode() + query.getKey());
//        boolean res = false;
//        try {
//            //分布式加锁
//            res = lock.tryLock(LOCK_EXPIRE, LOCK_EXPIRE, TimeUnit.SECONDS);
//            if (res) {
//                return function.apply(param);
//            } else {
//                logger.error("sample_sync加锁失败，key=");
//                throw new BizException("sample_sync加锁失败，key=" + query.getKey());
//            }
//        } catch (Exception e) {
//            logger.error("sample_sync获取分布式锁异常, key={}, msg={}", query.getKey(), e.getMessage(), e);
//            throw new BizException(e);
//        } finally {
//            if (res) {
//                lock.unlock();
//            }
//        }
//    }
//}