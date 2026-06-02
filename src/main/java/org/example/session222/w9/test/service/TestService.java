package org.example.session222.w9.test.service;

import org.example.session222.w9.apiPayload.code.ErrorStatus;
import org.example.session222.w9.apiPayload.exception.GeneralException;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public void checkFlag(Integer flag) {
        if (flag != null && flag == 1) {
            throw new GeneralException(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}


