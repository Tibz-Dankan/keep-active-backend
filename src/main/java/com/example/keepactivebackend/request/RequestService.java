package com.example.keepactivebackend.request;

import com.example.keepactivebackend.apps.App;
import com.example.keepactivebackend.apps.AppRepository;
import com.example.keepactivebackend.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Component
public class RequestService {

    private final RequestRepository requestRepository;
    private final AppRepository appRepository;
    private final WebClient webClient;

    public void saveRequest(Request request) {

        Request newRequest = Request.builder()
                .appId(request.getAppId())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        requestRepository.save(newRequest);
    }

    public void makeApiRequest(Long appId, String appName, String appUrl) {
        RequestPayload payload = new RequestPayload(appName, LocalDateTime.now());
        try {
            webClient.post()
                    .uri(appUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(payload), RequestPayload.class)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError,
                            clientResponse -> {
                                return Mono.error(new InternalServerErrorException("API call failed with status: " + clientResponse.statusCode()));
                            }
                    )
                    .bodyToMono(String.class)
                    .subscribe(
                            responseJson -> {
                                System.out.println("Response JSON: " + responseJson);
                                Request newRequest = new Request();
                                newRequest.setAppId(appId);
                                newRequest.setStatus("Successfully executed");
                                System.out.println("Request successfully executed");
                                saveRequest(newRequest);
                            },
                            error -> {
                                Request newRequest = new Request();
                                newRequest.setAppId(appId);
                                newRequest.setStatus("Failed with error: " + error.getMessage());
                                System.err.println("Error: " + error.getMessage());
                                saveRequest(newRequest);
                            }
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeApiRequestPeriodically() {
        Optional<App> app = appRepository.findById(5L);

        Schedulers.single().schedulePeriodically(
                () -> makeApiRequest(
                        app.get().getAppId(),
                        app.get().getAppName(),
                        app.get().getAppUrl()
                ),
                0,
                60_000,
                java.util.concurrent.TimeUnit.MILLISECONDS
        );
    }

    public List<Request> getRequestsByApp(Long appId) {

        Optional<List<Request>> requestsOptional = requestRepository.findByAppId(appId);
        // Handle the case when the user has no apps (optional is empty)
        // You can return an empty list or throw an exception, depending on your requirements.
        return requestsOptional.orElseGet(List::of);
    }
}

