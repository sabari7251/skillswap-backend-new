package com.example.skillbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProfileRequest {
    private String bio;
    private String name;
    private String skillsOffered;

    private String skillsWanted;
}
