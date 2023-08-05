package com.example.keepactivebackend.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class RequestPayload {
    @JsonProperty("appName")
    private String appName;
    @JsonProperty("requestDate")
    private LocalDateTime requestDate;
//    @JsonProperty("apiKey")
//    private LocalDateTime apiKey;
}
