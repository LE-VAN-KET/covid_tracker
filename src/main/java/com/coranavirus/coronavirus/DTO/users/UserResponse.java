package com.coranavirus.coronavirus.DTO.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private UUID userId;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
