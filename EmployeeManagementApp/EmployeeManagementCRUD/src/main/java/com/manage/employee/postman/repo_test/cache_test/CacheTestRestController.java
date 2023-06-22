package com.manage.employee.postman.repo_test.cache_test;

import com.manage.employee.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postman")
public class CacheTestRestController {

    private final CacheService cacheService;

    public CacheTestRestController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("cache_list")
    public ResponseEntity<?> getCacheList(@RequestParam("cacheName") String cacheName) {
        return ResponseEntity.ok(cacheService.getAllCachedValues(cacheName));
    }
}
