package com.eater.eater.controller;

import com.eater.eater.service.fakeData.FakeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth/")
@RestController
@RequiredArgsConstructor
public class FakeDataController {
    private final FakeDataService fakeDataService;

    @PostMapping("/fakeData")
    public ResponseEntity<String> generateFakeData() {
        fakeDataService.generateFakeData();
        return ResponseEntity.ok("Successfully added");
    }
}
