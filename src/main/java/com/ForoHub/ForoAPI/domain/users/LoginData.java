package com.ForoHub.ForoAPI.domain.users;

import jakarta.validation.constraints.NotBlank;

public record LoginData(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
