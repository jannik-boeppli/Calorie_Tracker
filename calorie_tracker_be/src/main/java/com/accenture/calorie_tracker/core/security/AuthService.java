package com.accenture.calorie_tracker.core.security;

import com.accenture.calorie_tracker.core.error.UsernameAlreadyExistsException;
import com.accenture.calorie_tracker.domain.user.User;
import com.accenture.calorie_tracker.domain.user.UserRepository;
import com.accenture.calorie_tracker.domain.user.dto.UserMapper;
import com.accenture.calorie_tracker.domain.user.dto.UserSignUpDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final JWTManager jwtManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthService(JWTManager jwtManager, UserRepository userRepository, UserMapper userMapper) {
        this.jwtManager = jwtManager;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Map<String, String> refreshToken(String authHeader) {
        String refreshToken = authHeader.substring("Bearer ".length());
        String username = jwtManager.verifyToken(refreshToken).getSubject();
        String accessToken = jwtManager.createAccessToken(username);
        refreshToken = jwtManager.createRefreshToken(username);
        return Map.of("access_token", accessToken, "refresh_token", refreshToken);
    }

    public User signUp(UserSignUpDTO userSignUpDTO) throws UsernameAlreadyExistsException {
        if (userRepository.findByUsername(userSignUpDTO.getUsername()) != null)
            throw new UsernameAlreadyExistsException();
        return userRepository.save(userRepository.preSave(userMapper.fromSignUpDTO(userSignUpDTO)));
    }
}
