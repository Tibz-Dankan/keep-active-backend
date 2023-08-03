package com.example.keepactivebackend.apps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAppRequest {
    private Integer userId;
    private String appName;
    private String appUrl;
}

