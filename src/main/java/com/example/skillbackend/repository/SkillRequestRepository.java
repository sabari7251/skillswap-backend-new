package com.example.skillbackend.repository;

import com.example.skillbackend.model.SkillRequest;
import com.example.skillbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRequestRepository extends JpaRepository<SkillRequest,Long> {
    public List<SkillRequest> findByReceiver(User receiver);
    public List<SkillRequest> findBySender(User sender);
}
