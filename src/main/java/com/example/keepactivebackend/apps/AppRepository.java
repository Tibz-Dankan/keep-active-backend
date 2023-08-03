package com.example.keepactivebackend.apps;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppRepository extends JpaRepository<App, Long> {
    Optional<App> findByUserId(Integer userId);
//    Optional<Object> findById(Long appId);
    Optional<App> findByAppName(String appName);


}


