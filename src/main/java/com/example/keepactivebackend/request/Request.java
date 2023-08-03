package com.example.keepactivebackend.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_apps")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "app_id") // userId as a foreign key column
    private Integer appId;
    private String status;   //request status

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;


    // Getter methods
    public Long getRequestId() {
        return id;
    }

    public Integer getAppId() {  return appId; }

    public String getStatus() {  return status; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}

