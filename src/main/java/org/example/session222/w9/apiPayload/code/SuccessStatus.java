package org.example.session222.w9.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.session222.w9.apiPayload.dto.ReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."); //예시 응답
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ReasonDTO getReason() { //순수 응답 코드 + 메시지만 반환(httpStatus 제외)
        return ReasonDTO.builder().message(message).code(code).isSuccess
                (true).build();
    }
    @Override
    public ReasonDTO getReasonHttpStatus() { //httpStatus까지 포함해서 반환 (실제 HTTP 응답 만들 때 사용)
        return ReasonDTO.builder().message(message).code(code).isSuccess
                (true).httpStatus(httpStatus).build();
    }
}
