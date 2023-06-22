package com.manage.employee.service;

import java.util.List;

public interface CacheService {
    List<String> getAllCachedValues(String cacheName);
}
