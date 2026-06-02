package org.example.session222.w9.service;

import lombok.RequiredArgsConstructor;
import org.example.session222.w9.apiPayload.code.ErrorStatus;
import org.example.session222.w9.apiPayload.exception.GeneralException;
import org.example.session222.w9.dto.request.W9UserLoginRequestDto;
import org.example.session222.w9.dto.request.W9UserPasswordChangeRequestDto;
import org.example.session222.w9.dto.request.W9UserSignupRequestDto;
import org.example.session222.w9.dto.response.W9UserLoginResponseDto;
import org.example.session222.w9.entity.W9User;
import org.example.session222.w9.repository.W9UserRepository;
import org.example.session222.w9.security.W9JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class W9UserService implements UserDetailsService {

    private final W9UserRepository w9UserRepository;
    private final PasswordEncoder passwordEncoder;
    private final W9JwtTokenProvider w9JwtTokenProvider;

    @Transactional
    public void signup(W9UserSignupRequestDto requestDto) {
        if (w9UserRepository.findByUsername(requestDto.getUsername()).isPresent()) {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        W9User w9User = W9User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .build();

        w9UserRepository.save(w9User);
    }

    public W9UserLoginResponseDto login(W9UserLoginRequestDto requestDto) {
        W9User w9User = w9UserRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), w9User.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_MISMATCH);
        }

        String token = w9JwtTokenProvider.createToken(w9User.getUsername());

        return W9UserLoginResponseDto.builder()
                .username(w9User.getUsername())
                .token(token)
                .build();
    }

    @Transactional
    public void changePassword(String username, W9UserPasswordChangeRequestDto requestDto) {
        W9User w9User = w9UserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(requestDto.getCurrentPassword(), w9User.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_MISMATCH);
        }

        if (!requestDto.getNewPassword().equals(requestDto.getConfirmPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_CONFIRM_MISMATCH);
        }

        w9User.changePassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        W9User w9User = w9UserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        return org.springframework.security.core.userdetails.User
                .withUsername(w9User.getUsername())
                .password(w9User.getPassword())
                .authorities("USER")
                .build();
    }
}
