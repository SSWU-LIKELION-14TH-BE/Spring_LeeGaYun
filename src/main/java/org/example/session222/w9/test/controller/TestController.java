package org.example.session222.w9.test.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.session222.w9.apiPayload.code.SuccessStatus;
import org.example.session222.w9.apiPayload.dto.ApiResponse;
import org.example.session222.w9.test.dto.SampleRequestDto;
import org.example.session222.w9.test.dto.TestResponse;
import org.example.session222.w9.test.service.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    // 성공 응답 테스트
    @GetMapping("/hello")
    public ApiResponse<TestResponse> hello() {
        return ApiResponse.of(SuccessStatus._OK, new TestResponse("Hello, API!"));
    }
    // 예외 발생 테스트
    @GetMapping("/error")
    public ApiResponse<TestResponse> error(@RequestParam(required = false) Integer flag) {
        testService.checkFlag(flag); // flag==1일 때 예외 발생
        return ApiResponse.of(SuccessStatus._OK, new TestResponse("정상 처리되었습니다."));
    }
    // 유효성 검사 테스트
    @PostMapping("/validate")
    public ApiResponse<String> validate(@Valid @RequestBody SampleRequestDto dto) {
        return ApiResponse.of(SuccessStatus._OK, "통과되었습니다");
    }
}