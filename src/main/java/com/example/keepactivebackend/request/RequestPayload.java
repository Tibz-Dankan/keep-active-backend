package com.example.keepactivebackend.request;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class RequestPayload {
    private String appName;
    private LocalDateTime requestDate;

}
