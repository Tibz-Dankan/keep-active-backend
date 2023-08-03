package com.example.keepactivebackend.apps;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/apps")
@PreAuthorize("hasRole('ADMIN')")
public class AppController {

    final private AppService appService;

    @PostMapping("/add-app")
    public ResponseEntity<App> addApp(
            @RequestBody AddAppRequest request
    ) {
        App newApp = appService.addNewApp(
                request.getUserId(),
                request.getAppName(),
                request.getAppUrl());
        return new ResponseEntity<>(newApp, HttpStatus.CREATED);
    }

    @PutMapping(path="/update-app/{appId}")
    public ResponseEntity<App> updateApp(
            @PathVariable("appId") Long appId,
            @RequestBody UpdateAppRequest request
    ) {
        App newApp = appService.updateApp(appId, request.getAppName(), request.getAppUrl());
        return new ResponseEntity<>(newApp, HttpStatus.OK);
    }

    @GetMapping(path="/get-apps-by-userid/{userId}")
//    public ResponseEntity<List<App>> getAppsByUserId(
    public Optional<App> getAppsByUserId(
            @PathVariable("appId") Integer userId){

        Optional<App> apps= appService.getAppsByUserId(userId);

        System.out.println("apps");
        System.out.println(apps);

        return apps;
//        return new ResponseEntity<>(apps, HttpStatus.OK);
    }

    @DeleteMapping(path="/delete-app/{appId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable("appId") Long appId
    ){
        appService.deleteApp(appId);
        return new ResponseEntity<>("App deleted successfully", HttpStatus.OK);
    }

}
