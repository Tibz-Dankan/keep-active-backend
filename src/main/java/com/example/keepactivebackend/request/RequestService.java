package com.example.keepactivebackend.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    final private RequestRepository requestRepository;

    public Request saveRequest(Request request) {

        Request newRequest = Request.builder()
                .appId(request.getAppId())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

         return requestRepository.save(newRequest);
    }

    public List<Request> getRequestsByApp(Long appId) {

        Optional<List<Request>> requestsOptional = requestRepository.findByAppId(appId);
        // Handle the case when the user has no apps (optional is empty)
        // You can return an empty list or throw an exception, depending on your requirements.
        return requestsOptional.orElseGet(List::of);
    }
}

