package org.example.session222.w9.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.session222.w9.apiPayload.dto.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청 입니다."), //응답 예시
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP401", "테스트용 예외입니다.");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }
    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).httpStatus(httpStatus).build();
    }
}

