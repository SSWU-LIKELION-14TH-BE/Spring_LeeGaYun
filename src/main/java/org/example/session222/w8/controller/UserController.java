package org.example.session222.w8.controller;
import org.example.session222.w8.dto.user.requestDto.UserPasswordChangeRequestDto;

import lombok.AllArgsConstructor;
import org.example.session222.w8.dto.user.requestDto.UserLoginRequestDto;
import org.example.session222.w8.dto.user.requestDto.UserSignupRequestDto;
import org.example.session222.w8.dto.user.responseDto.UserLoginResponseDto;
import org.example.session222.w8.dto.user.responseDto.UserMeResponseDto;
import org.example.session222.w8.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserMeResponseDto> getMyInfo(Authentication authentication) {
        String email = authentication.getName();

        UserMeResponseDto response = userService.getMyInfo(email);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(
            Authentication authentication,
            @RequestBody UserPasswordChangeRequestDto requestDto
    ) {
        String email = authentication.getName();
        userService.changePassword(email, requestDto);

        return ResponseEntity.ok("비밀번호 변경 성공!");
    }
}