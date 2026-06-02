package org.example.session222.w9.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class W9UserLoginResponseDto {
    private String username;
    private String token;
}
