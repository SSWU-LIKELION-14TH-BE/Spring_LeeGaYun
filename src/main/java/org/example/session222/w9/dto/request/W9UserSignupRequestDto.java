package org.example.session222.w9.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class W9UserSignupRequestDto {

    @NotBlank
    @Size(min = 2)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String name;
}