package com.example.keepactivebackend.request;

import com.example.keepactivebackend.apps.App;
import com.example.keepactivebackend.apps.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
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
                    .body(Mono.just(payload), RequestPayload.class)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError,
                            clientResponse -> {
                                Request newRequest = new Request();
                                newRequest.setAppId(appId);
                                newRequest.setStatus("Failed with status code: " + clientResponse.statusCode());
                                System.out.println("Response Client" + clientResponse);
                                saveRequest(newRequest);
//                            TODO: to add custom exception for these errors
                                return Mono.error(new RuntimeException("API call failed with status: " + clientResponse.statusCode()));
                            }
                    )
                    .bodyToMono(String.class)
                    .subscribe(
                            responseJson -> {
                                // Process the response JSON here.
                                System.out.println("Response JSON: " + responseJson);
                            },
                            error -> {
                                // Handle any errors that occurred during the API call.
                                System.err.println("Error: " + error.getMessage());
                            }
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeApiRequestPeriodically() {
//         Run the API call every minute using the Scheduler.
        Optional<App> app = appRepository.findById(5L);
        System.out.println("app");
        System.out.println(app);

//        {
//            "id":5,
//                "userId":1,
//                "appName":"Abacusug",
//                "createdAt":"2023-08-04T05:19:41.628385",
//                "updatedAt":null,
//                "appUrl":"https://localhost:5000/api/keep-active",
//                "appId":5
//        }


        Schedulers.single().schedulePeriodically(
                () -> makeApiRequest(
                        app.get().getAppId(),
                        app.get().getAppName(),
                        app.get().getAppUrl()
                ),
                0, // Initial delay (0 milliseconds means start immediately)
                60_000, // Period in milliseconds (1 minute)
                java.util.concurrent.TimeUnit.MILLISECONDS // Time unit for the initial delay and period
        );
    }

    public List<Request> getRequestsByApp(Long appId) {

        Optional<List<Request>> requestsOptional = requestRepository.findByAppId(appId);
        // Handle the case when the user has no apps (optional is empty)
        // You can return an empty list or throw an exception, depending on your requirements.
        return requestsOptional.orElseGet(List::of);
    }
}

