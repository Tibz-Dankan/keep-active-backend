package com.example.keepactivebackend.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apps/requests")
@PreAuthorize("hasRole('ADMIN')")
public class RequestController {

    final private RequestService requestService;


    @GetMapping(path = "/get-requests-by-app/{appId}")
    public ResponseEntity<List<Request>> getRequestsByApp(
            @PathVariable("userId") Long appId
    ) {
        List<Request> requests = requestService.getRequestsByApp(appId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


}
