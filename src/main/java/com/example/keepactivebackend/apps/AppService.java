package com.example.keepactivebackend.apps;

import com.example.keepactivebackend.exception.BadRequestException;
import com.example.keepactivebackend.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppService {


    private final AppRepository appRepository;

    public App addNewApp(Integer userId, String appName, String appUrl) {
        Optional<App> app = appRepository.findByAppName(appName);

        if (app.isPresent()) {
            throw new BadRequestException("App name already taken");
        }
        App newApp = App.builder()
                .userId(userId)
                .appName(appName)
                .appUrl(appUrl)
                .createdAt(LocalDateTime.now())
                .build();
        return appRepository.save(newApp);
    }

    @Transactional
    public App updateApp(Long appId, String appName, String appUrl) {
        App app = appRepository.findById(appId).orElseThrow(
                () -> new ResourceNotFoundException("App with id " + appId + " does not exists")
        );

        if (appName != null && appName.length() > 0 && !Objects.equals(app.getAppName(), appName)) {
            app.setAppName(appName);
            app.setAppUrl(appUrl);
            app.setUpdatedAt(LocalDateTime.now());
        }

        if (appName != null && appName.length() > 0 && !Objects.equals(app.getAppName(), appName)) {
            Optional<App> AppOptional = appRepository.findByAppName(app.getAppName());
            if (AppOptional.isPresent()) {
                throw new BadRequestException("Can't Update to already taken name");
            }
            app.setAppName(appName);
            app.setAppUrl(appUrl);
            app.setUpdatedAt(LocalDateTime.now());
        }

        return app;
    }

    public List<App> getAppsByUserId(Integer userId) {
        Optional<List<App>> appsOptional = appRepository.findByUserId(userId);
        // Handle the case when the user has no apps (optional is empty)
        // You can return an empty list or throw an exception, depending on your requirements.
        return appsOptional.orElseGet(List::of);
    }

    public void deleteApp(Long appId) {
        Optional<App> optionalApp = appRepository.findById(appId);
        if (optionalApp.isPresent()) {
            App app = optionalApp.get();
            appRepository.delete(app);
        } else {
            throw new ResourceNotFoundException("App with id " + appId + " not found");
        }
    }

}

