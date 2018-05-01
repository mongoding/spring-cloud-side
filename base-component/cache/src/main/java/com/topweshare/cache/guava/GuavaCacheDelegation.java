package com.topweshare.cache.guava;


import com.google.common.base.Optional;
import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableMap;

import com.topweshare.cache.CacheDelegation;
import com.topweshare.cache.util.KeyGenerator;
import com.topweshare.cache.util.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * gauva cache Delegation
 *
 * @author <a href="mailto:mongoding@jiuxian.com">mongoding</a>
 */
public class GuavaCacheDelegation implements CacheDelegation {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Cache<String, Optional<Object>> cache;


    public GuavaCacheDelegation(Cache<String, Optional<Object>> cache) {

        this.cache = cache;

    }


    @Override
    public <V> Optional<V> query(String prefix, Keys k) {
        String key = KeyGenerator.generateStringKey(k, prefix);
        Optional<Object> result = cache.getIfPresent(key);
        Optional<V> value = result==null? Optional.fromNullable(null):(Optional<V>) result;
        logger.debug("guava 获取缓存，key:{},value:{}", key, value);
        return value;
    }


    @Override
    public <V> Map<Keys, Optional<V>> query(String prefix, List<Keys> ks) {
        Map<Keys, Optional<V>> map = new HashMap<>();
        if (ks == null || ks.isEmpty()) {
            return map;
        }
        List<String> collect = ks.stream().map(item -> KeyGenerator.generateStringKey(item.getJoinedKey(), prefix)).collect(Collectors.toList());
        try {
            ImmutableMap<String, Optional<Object>> allPresent = cache.getAllPresent(collect);
            //ImmutableSet<Map.Entry<String, Optional<V>>> entries = allPresent.entrySet();

            if (allPresent != null && !allPresent.isEmpty()) {
                for (Keys keys : ks) {

                    Optional<Object> result = allPresent.get(KeyGenerator.generateStringKey(keys.getJoinedKey(), prefix));

                    if (result != null) {
                        map.put(keys, (Optional<V>)result);
                    }
                }
            }
            logger.debug("guava 获取缓存，key:{},value:{}", collect, map);
            return map;
        } catch (Exception e) {
            logger.error("从guava 批量获取缓存异常：", e);
        }
        return map;
    }

    @Override
    public <V> void save(String prefix, Keys key, Optional<V> v) {
        String stringKey = KeyGenerator.generateStringKey(key, prefix);

        cache.put(stringKey, (Optional<Object>) v);
        logger.debug("guava 设置缓存，key:{},value:{}", stringKey, v);

    }


    @Override
    public <V> void save(String prefix, Map<Keys, Optional<V>> kvs) {
        if (kvs != null && !kvs.isEmpty()) {
            Map<String, Optional<Object>> collect = new HashMap<>();
            for (Map.Entry<Keys, Optional<V>> keysOptionalEntry : kvs.entrySet()) {
                collect.put(KeyGenerator.generateStringKey(keysOptionalEntry.getKey().getJoinedKey(), prefix), (Optional<Object>)(keysOptionalEntry.getValue()));
            }
            cache.putAll(collect);
            logger.debug("guava 设置缓存：{}", collect);
        }

    }

    @Override
    public <V> void save(String prefix, Keys k, Optional<V> v, int expires) {

        this.save(prefix, k, v);
    }

    @Override
    public <V> void save(String prefix, Map<Keys, Optional<V>> kvs, int expires) {

        this.save(prefix, kvs);
    }

    public Cache<String, Optional<Object>> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Optional<Object>> cache) {
        this.cache = cache;
    }


    @Override
    public String toString() {
        return "GuavaCacheDelegation{" +
                "cache=" + cache +
                '}';
    }
}
