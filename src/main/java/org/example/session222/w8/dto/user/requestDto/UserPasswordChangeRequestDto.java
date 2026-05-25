package org.example.session222.w8.dto.user.requestDto;

import lombok.Getter;

@Getter
public class UserPasswordChangeRequestDto {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}