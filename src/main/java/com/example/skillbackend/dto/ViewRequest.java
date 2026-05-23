package com.example.skillbackend.dto;

import lombok.Data;

@Data
public class ViewRequest {
    private Long id;
    private String name;
    private String message;
    private String status;
    private String skill;
    private String email;
}
