package org.example.session222.w8.dto.user.responseDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMeResponseDto {
    private String email;
    private String name;
    private String profileImage;
}