package com.topweshare.cache;

import com.google.common.base.Optional;

public class MultCache<V> extends SimpleCache<V>{

    public MultCache(CacheDelegation level1Cache, String namespace, Integer expireInSeconds){
        super(level1Cache,namespace,expireInSeconds);

    }

    /**
     * 是否要穿透缓存，回源取数据，此处第一级回源
     * @param data
     * @return
     */
    @Override
     public boolean ifGetFromSource(Optional<V> data){

        if (data == null) {
            return true;
        }
        if (!data.isPresent()) {
            return true;
        }

        return false;
    }
}
