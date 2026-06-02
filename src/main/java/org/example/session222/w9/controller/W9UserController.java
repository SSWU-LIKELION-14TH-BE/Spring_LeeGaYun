package org.example.session222.w9.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.session222.w9.apiPayload.code.SuccessStatus;
import org.example.session222.w9.apiPayload.dto.ApiResponse;
import org.example.session222.w9.dto.request.W9UserLoginRequestDto;
import org.example.session222.w9.dto.request.W9UserPasswordChangeRequestDto;
import org.example.session222.w9.dto.request.W9UserSignupRequestDto;
import org.example.session222.w9.dto.response.W9UserLoginResponseDto;
import org.example.session222.w9.service.W9UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class W9UserController {

    private final W9UserService w9UserService;

    @PostMapping("/signup")
    public ApiResponse<String> signup(@Valid @RequestBody W9UserSignupRequestDto requestDto) {
        w9UserService.signup(requestDto);
        return ApiResponse.of(SuccessStatus._OK, "회원가입 성공!");
    }

    @PostMapping("/login")
    public ApiResponse<W9UserLoginResponseDto> login(@RequestBody W9UserLoginRequestDto requestDto) {
        return ApiResponse.of(SuccessStatus._OK, w9UserService.login(requestDto));
    }

    @PatchMapping("/password")
    public ApiResponse<String> changePassword(
            Authentication authentication,
            @Valid @RequestBody W9UserPasswordChangeRequestDto requestDto
    ) {
        String username = authentication.getName();
        w9UserService.changePassword(username, requestDto);
        return ApiResponse.of(SuccessStatus._OK, "비밀번호가 변경되었습니다.");
    }
}
