package com.example.keepactivebackend.request;

import com.example.keepactivebackend.apps.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface RequestRepository extends JpaRepository<App, Long> {
    Optional<List<Request>> findByAppId(Long appId);
}
