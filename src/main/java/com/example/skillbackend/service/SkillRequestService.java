package com.example.skillbackend.service;

import com.example.skillbackend.dto.SendRequest;
import com.example.skillbackend.model.SkillRequest;
import com.example.skillbackend.model.User;
import com.example.skillbackend.repository.SkillRequestRepository;
import com.example.skillbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillRequestService {
    private final SkillRequestRepository skillRequestRepository;
    private final UserRepository userRepository;

    public SkillRequest sendRequest(SendRequest request,String senderMail){
        User sender = userRepository.findByEmail(senderMail).orElseThrow();
        User receiver = userRepository.findByEmail(request.getReceiverEmail()).orElseThrow();
        SkillRequest newRequest = new SkillRequest();
        newRequest.setSender(sender);
        newRequest.setReceiver(receiver);
        newRequest.setMessage(request.getMessage());
        newRequest.setStatus("PENDING");
        return skillRequestRepository.save(newRequest);
    }


    public List<SkillRequest> viewRequests(String email){
        User receiver = userRepository.findByEmail(email).orElseThrow();
        return skillRequestRepository.findByReceiver(receiver);
    }

    public SkillRequest acceptRequest(Long id){
        SkillRequest request = skillRequestRepository.findById(id).orElseThrow();
        request.setStatus("ACCEPTED");
        return skillRequestRepository.save(request);

    }

    public SkillRequest rejectRequest(Long id){
        SkillRequest request = skillRequestRepository.findById(id).orElseThrow();
        request.setStatus("REJECTED");
        return skillRequestRepository.save(request);

    }
}
