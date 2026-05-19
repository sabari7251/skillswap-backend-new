package com.example.skillbackend.controller;

import com.example.skillbackend.dto.SendRequest;
import com.example.skillbackend.model.SkillRequest;
import com.example.skillbackend.service.SkillRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class SkillRequestController {
    private final SkillRequestService skillRequestService;
    @PostMapping("/send")
    public ResponseEntity<SkillRequest> sendRequest(Authentication authentication, @RequestBody SendRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(skillRequestService.sendRequest(request, authentication.getName()));
    }

    @GetMapping("/view")
    public ResponseEntity<List<SkillRequest>> viewRequests(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.viewRequests(authentication.getName()));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<SkillRequest> acceptRequest(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.acceptRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<SkillRequest> rejectRequest(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.rejectRequest(id));
    }

}
