package com.example.skillbackend.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SendRequest {
    private String receiverEmail;
    private String message;
    private String skill;
}
