package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JWTService;
import peaksoft.dto.Authentication.AuthenticationResponse;
import peaksoft.dto.Authentication.SignInRequest;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.BadCredentialException;
import peaksoft.repository.UserRepository;
import peaksoft.service.AuthenticationService;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new EntityNotFoundException("User with email: " + signInRequest.getEmail() + " not found!")
        );

        if (signInRequest.getPassword().isBlank()) {
            throw new BadCredentialException("Password is blank");
        }

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialException("Wrong password!");
        }

        String token = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }



    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Davran");
        user.setLastName("Joldoshbaev");
        user.setDateOfBirth(LocalDate.now());
        user.setEmail("d@gmail.com");
        user.setPassword(passwordEncoder.encode("davran00"));
        user.setPhoneNumber("+996995665528");
        user.setRole(Role.ADMIN);
        user.setExperience(3);
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }
}
