package org.example.session222.w8.service.user;
import org.example.session222.w8.dto.user.responseDto.UserMeResponseDto;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.session222.w8.dto.user.requestDto.UserLoginRequestDto;
import org.example.session222.w8.dto.user.requestDto.UserSignupRequestDto;
import org.example.session222.w8.dto.user.responseDto.UserLoginResponseDto;
import org.example.session222.w8.entity.user.User;
import org.example.session222.w8.repository.user.UserRepository;
import org.example.session222.w8.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 주입
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    // 🔑 회원가입 (비밀번호 암호화 후 저장)
    @Transactional
    public void signup(UserSignupRequestDto requestDto) {
        // 이미 가입된 이메일인지 확인
        if (userRepository.findByEmail(requestDto.getEmail
                ()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }


        User user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .profileImage(requestDto.getProfileImage())
                .build();
        userRepository.save(user);
    }

    // 🔐 로그인 (이메일, 비밀번호 검증 후 JWT 발급)
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        // 없는 유저일시 오류 출력
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("해당 이메일의 유저를 찾을 수 없습니다."));
        // 비밀번호 불일치시 오류 출력
        if (!passwordEncoder.matches(requestDto.getPassword
                (), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일 치하지 않습니다.");
        }
        // 토큰 생성
        String token = jwtTokenProvider.createToken(user.getEmail());
        return UserLoginResponseDto.builder()
                .email(user.getEmail())
                .token(token)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다: " + email));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER") // 기본 권한 설정
                .build();
    }

    public UserMeResponseDto getMyInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        return UserMeResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .build();
    }
}
