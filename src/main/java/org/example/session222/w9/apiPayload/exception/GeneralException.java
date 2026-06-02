package org.example.session222.w9.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.session222.w9.apiPayload.code.BaseErrorCode;
import org.example.session222.w9.apiPayload.dto.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}