package com.example.skillbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String bio;

    private String skillsOffered;

    private String skillsWanted;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<SkillRequest> sentRequests;

    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    private List<SkillRequest> receivedRequests;

}
