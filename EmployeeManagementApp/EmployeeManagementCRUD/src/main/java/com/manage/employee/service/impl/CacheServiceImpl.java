package com.manage.employee.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.manage.employee.service.CacheService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    public CacheServiceImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Override
    public List<String> getAllCachedValues(String cacheName) {
        List<String> list = new ArrayList<>();
        CaffeineCache cache = (CaffeineCache) cacheManager.getCache(cacheName);
        if (cache != null) {
            Cache<Object, Object> caffeine = cache.getNativeCache();
            caffeine.asMap().forEach((key, value) -> {
                list.add(key + " " + value);
            });

        }
        return list;
    }
}
