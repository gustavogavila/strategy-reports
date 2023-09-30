package com.gus.strategyreports.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserRequest {

    @NotBlank
    private String fullname;

    @NotBlank
    private String username;

    private OffsetDateTime createdAt = OffsetDateTime.now();
}
