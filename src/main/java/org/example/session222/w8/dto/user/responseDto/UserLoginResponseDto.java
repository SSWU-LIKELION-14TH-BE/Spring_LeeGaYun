package org.example.session222.w8.dto.user.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserLoginResponseDto {
    private String email;
    private String token;
}
