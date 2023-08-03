package com.example.keepactivebackend.request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<List<Request>> findByAppId(Long appId);
}
