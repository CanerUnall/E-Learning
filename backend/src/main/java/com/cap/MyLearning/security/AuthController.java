package com.cap.MyLearning.security;

import com.cap.MyLearning.model.User;
import com.cap.MyLearning.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/api/login")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final CustomUserServiceImpl customUserService;
    private final JwtUtil jwtUtil;
    @Autowired
    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, CustomUserServiceImpl customUserService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.customUserService = customUserService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
        try {
            // Kullanıcı kimlik doğrulamasını yap.
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
            return null;
        }

        // Kullanıcıyı yükle.
        final UserDetails userDetails = customUserService.loadUserByUsername(loginRequest.getEmail());

        // Kullanıcının rol bilgisine erişim (GrantedAuthority).
        String roleName = null;
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if (!authorities.isEmpty()) {
            roleName = authorities.iterator().next().getAuthority(); // İlk rolü al
        }
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        // JWT tokenini üretirken role bilgisiyle gönder.
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), roleName,user.getUserId());

        // Tokeni dön.
        return new LoginResponse(jwt);
    }


}
