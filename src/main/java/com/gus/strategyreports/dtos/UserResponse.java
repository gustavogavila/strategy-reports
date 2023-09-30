package com.gus.strategyreports.dtos;

import java.time.OffsetDateTime;

public record UserResponse(
        Long id,
        String fullname,
        String username,
        OffsetDateTime createdAt
) {
}
