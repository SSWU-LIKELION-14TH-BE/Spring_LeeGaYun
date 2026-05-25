package org.example.session222.w8.dto.user.requestDto;

import lombok.Data;

@Data
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private String profileImage;
}
