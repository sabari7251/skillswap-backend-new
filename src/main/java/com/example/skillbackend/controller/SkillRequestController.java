package com.example.skillbackend.controller;

import com.example.skillbackend.dto.SendRequest;
import com.example.skillbackend.dto.ViewRequest;
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

    @GetMapping("/viewlite")
    public ResponseEntity<List<ViewRequest>> viewLiteRequests(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.viewLiteRequests(authentication.getName()));
    }

    @GetMapping("/viewmyrequest")
    public ResponseEntity<List<ViewRequest>> viewMyRequests(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.viewMyRequests(authentication.getName()));
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<ViewRequest>> viewAllRequests(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.viewAllRequests());
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<SkillRequest> acceptRequest(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.acceptRequest(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<String> rejectRequest(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(skillRequestService.rejectRequest(id));
    }

}
