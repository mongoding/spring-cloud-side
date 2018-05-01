package com.topweshare.cache.guava;

import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;

import com.topweshare.cache.util.PageQueryParam;
import com.topweshare.cache.util.Pager;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;


/**
 * Guava缓存监视和管理工具
 *
 * @author dingzhenhao
 */
public class GuavaCacheManager {

    protected static final Logger logger = LoggerFactory.getLogger(GuavaCacheManager.class);

    //保存一个Map: cacheName -> cache Object，以便根据cacheName获取Guava cache对象
    //private static Map<String, GuavaCache<? extends Object>> cacheNameToObjectMap = new HashMap<>();

    /**
     * 获取所有GuavaCache子类的实例，即所有的Guava Cache对象
     *
     * @return
     */

    @SuppressWarnings("unchecked")
    private static Map<String, Cache<String, Optional<Object>>> getCacheMap() {
        Map<String, Cache<String, Optional<Object>>> guavaCacheMap = GuavaCacheHolder.getGuavaCacheContext();
        /*if (guavaCacheMap == null) {
            guavaCacheMap = (Map<String, Cache<String, Optional<Object>>>) SpringContextUtil.getBeanOfType(GuavaCache.class);
        }*/
        return guavaCacheMap;

    }

    /**
     * 根据cacheName获取cache对象
     *
     * @param cacheName
     * @return
     */
    private static Cache<String, Optional<Object>> getCacheByName(String cacheName) {
        return (Cache<String, Optional<Object>>) getCacheMap().get(cacheName);
    }

    /**
     * 获取所有缓存的名字（即缓存实现类的名称）
     *
     * @return
     */
    public static Set<String> getCacheNames() {
        return getCacheMap().keySet();
    }

    /**
     * 返回所有缓存的统计数据
     *
     * @return List<Map<统计指标，统计数据>>
     */
    public static ArrayList<Map<String, Object>> getAllCacheStats() {

        Map<String, ? extends Object> cacheMap = getCacheMap();
        List<String> cacheNameList = new ArrayList<>(cacheMap.keySet());
        Collections.sort(cacheNameList);//按照字母排序

        //遍历所有缓存，获取统计数据
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (String cacheName : cacheNameList) {
            list.add(getCacheStatsToMap(cacheName));
        }

        return list;
    }

    /**
     * 返回一个缓存的统计数据
     *
     * @param cacheName
     * @return Map<统计指标，统计数据>
     */
    private static Map<String, Object> getCacheStatsToMap(String cacheName) {
        Map<String, Object> map = new LinkedHashMap<>();
        Cache<String, Optional<Object>> cache = getCacheByName(cacheName);
        //Cache<String, Optional<Object>> cache = guavaCache.getCache();
        CacheStats cs = cache.stats();
        NumberFormat percent = NumberFormat.getPercentInstance(); // 建立百分比格式化用
        percent.setMaximumFractionDigits(1); // 百分比小数点后的位数
        map.put("cacheName", cacheName);
        map.put("size", cache.size());
        //map.put("maximumSize", guavaCache.getMaximumSize());
        //map.put("survivalDuration", guavaCache.getExpire());
        map.put("hitCount", cs.hitCount());
        map.put("hitRate", percent.format(cs.hitRate()));
        map.put("missRate", percent.format(cs.missRate()));
        map.put("loadSuccessCount", cs.loadSuccessCount());
        map.put("loadExceptionCount", cs.loadExceptionCount());
        map.put("totalLoadTime", cs.totalLoadTime() / 1000000);        //ms
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*if (guavaCache.getResetTime() != null) {
            map.put("resetTime", df.format(guavaCache.getResetTime()));
        }
        map.put("highestSize", guavaCache.getHighestSize());
        if (guavaCache.getHighestTime() != null) {
            map.put("highestTime", df.format(guavaCache.getHighestTime()));
        }*/

        return map;
    }

    /**
     * 根据cacheName清空缓存数据
     *
     * @param cacheName
     */
    public static void resetCache(String cacheName) {
        Cache<String, Optional<Object>> cache = getCacheByName(cacheName);
        cache.invalidateAll();
        //cache.setResetTime(new Date());
    }

    /**
     * 分页获得缓存中的数据
     *
     * @param pageParams
     * @return
     */
    public static Pager<Object> queryDataByPage(PageQueryParam pageParams, String cacheName) {
        //PageResult<Object> data = new Pager<>(pageParams);

        Cache<String, Optional<Object>> cache = getCacheByName(cacheName);
        ConcurrentMap<String, Optional<Object>> cacheMap = cache.asMap();
        //ConcurrentMap<String, Optional<Object>> concurrentMap = cache.getCache().asMap();
        //data.setTotalRecord(cacheMap.size());
        Pager<Object> pager = new Pager<>(cacheMap.size(), pageParams.getPageSize());
        pager.setPageNum(pageParams.getPageNum());

        //data.setTotalPage((cacheMap.size()-1)/pageParams.getPageSize()+1);

        //遍历
        Iterator<Entry<String, Optional<Object>>> entries = cacheMap.entrySet().iterator();
        int startPos = pager.getStartRecord();
        int endPos = startPos + pageParams.getPageSize();
        int i = 0;
        Map<Object, Object> resultMap = new LinkedHashMap<>();
        while (entries.hasNext()) {
            Map.Entry<String, Optional<Object>> entry = entries.next();
            if (i > endPos) {
                break;
            }

            if (i >= startPos) {
                resultMap.put(entry.getKey(), entry.getValue());
            }

            i++;
        }
        List<Object> resultList = new ArrayList<>();
        resultList.add(resultMap);
        pager.setRecordList(resultList);
        //data.setResults(resultList);
        return pager;
    }

    public static void logAllInfo() {
        logger.info("=============guava 缓存信息===========");
        logger.info("缓存bean：{}", GuavaCacheManager.getCacheMap());
        Set<String> cacheNames = GuavaCacheManager.getCacheNames();
        logger.info("缓存命名空间：{}", cacheNames);
        if (CollectionUtils.isNotEmpty(cacheNames)) {
            cacheNames.stream().forEach(item -> {
                        logger.info("当前缓存命名空间:{}", item);

                        Pager<Object> pager = GuavaCacheManager.queryDataByPage(new PageQueryParam(), item);
                        logger.info("当前缓存命名空间,第一页数据", pager);

                    }
            );
        }
        ArrayList<Map<String, Object>> allCacheStats = GuavaCacheManager.getAllCacheStats();
        logger.info("所有缓存的状态：{}", allCacheStats);

        logger.info("============guava 获取缓存信息结束===============");

    }

}
