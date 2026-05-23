package com.example.skillbackend.service;

import com.example.skillbackend.dto.SendRequest;
import com.example.skillbackend.dto.ViewRequest;
import com.example.skillbackend.model.SkillRequest;
import com.example.skillbackend.model.User;
import com.example.skillbackend.repository.SkillRequestRepository;
import com.example.skillbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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
        newRequest.setSkill(request.getSkill());
        return skillRequestRepository.save(newRequest);
    }


    public List<SkillRequest> viewRequests(String email){
        User receiver = userRepository.findByEmail(email).orElseThrow();
        return skillRequestRepository.findByReceiver(receiver);
    }

    public List<ViewRequest> viewLiteRequests(String email){
        User receiver = userRepository.findByEmail(email).orElseThrow();
        List<SkillRequest> requests = skillRequestRepository.findByReceiver(receiver);
        List<ViewRequest> viewRequests = new ArrayList<>();
        for(SkillRequest req:requests){
            ViewRequest viewRequest = new ViewRequest();
            viewRequest.setId(req.getId());
            viewRequest.setMessage(req.getMessage());
            viewRequest.setName(req.getSender().getName());
            viewRequest.setStatus(req.getStatus());
            viewRequest.setSkill(req.getSkill());
            viewRequest.setEmail(req.getSender().getEmail());
            viewRequests.add(viewRequest);
        }
        return viewRequests;

    }

    public List<ViewRequest> viewMyRequests(String email){
        User sender = userRepository.findByEmail(email).orElseThrow();
        List<SkillRequest> requests = skillRequestRepository.findBySender(sender);
        List<ViewRequest> viewRequests = new ArrayList<>();
        for(SkillRequest req:requests){
            ViewRequest viewRequest = new ViewRequest();
            viewRequest.setId(req.getId());
            viewRequest.setMessage(req.getMessage());
            viewRequest.setName(req.getReceiver().getName());
            viewRequest.setStatus(req.getStatus());
            viewRequest.setSkill(req.getSkill());
            viewRequest.setEmail(req.getReceiver().getEmail());
            viewRequests.add(viewRequest);
        }
        return viewRequests;

    }

    public List<ViewRequest> viewAllRequests(){
        List<SkillRequest> requests = skillRequestRepository.findAll();
        List<ViewRequest> viewRequests = new ArrayList<>();
        for(SkillRequest req:requests){
            ViewRequest viewRequest = new ViewRequest();
            viewRequest.setId(req.getId());
            viewRequest.setMessage(req.getMessage());
            viewRequest.setName(req.getReceiver().getName());
            viewRequest.setStatus(req.getStatus());
            viewRequest.setSkill(req.getSkill());
            viewRequests.add(viewRequest);
        }
        return viewRequests;

    }

    public SkillRequest acceptRequest(Long id){
        SkillRequest request = skillRequestRepository.findById(id).orElseThrow();
        request.setStatus("ACCEPTED");
        return skillRequestRepository.save(request);

    }

    public String rejectRequest(Long id){
        SkillRequest request = skillRequestRepository.findById(id).orElseThrow();
        skillRequestRepository.delete(request);
        return "REJECTED";

    }
}
