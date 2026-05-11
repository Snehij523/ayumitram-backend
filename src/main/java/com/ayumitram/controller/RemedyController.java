package com.ayumitram.controller;

import com.ayumitram.dto.response.ApiResponse;
import com.ayumitram.entity.Remedy;
import com.ayumitram.service.RemedyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/remedies")
@RequiredArgsConstructor
public class RemedyController {

    private final RemedyService remedyService;

    public RemedyController(RemedyService remedyService) {
        this.remedyService = remedyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Remedy>>> getAllRemedies() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Remedies fetched", remedyService.getAllRemedies()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Remedy>> addRemedy(@RequestBody Remedy remedy) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Remedy created", remedyService.addRemedy(remedy)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Remedy>> updateRemedy(@PathVariable Long id, @RequestBody Remedy remedy) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Remedy updated", remedyService.updateRemedy(id, remedy)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRemedy(@PathVariable Long id) {
        remedyService.deleteRemedy(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Remedy deleted", null));
    }
}