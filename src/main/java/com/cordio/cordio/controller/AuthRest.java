package com.cordio.cordio.controller;


import com.cordio.cordio.message.request.LoginForm;
import com.cordio.cordio.message.request.SignUpForm;
import com.cordio.cordio.message.response.JwtResponse;
import com.cordio.cordio.model.security.Role;
import com.cordio.cordio.model.security.RoleName;
import com.cordio.cordio.model.security.User;
import com.cordio.cordio.repository.RoleRepository;
import com.cordio.cordio.repository.UserRepository;
import com.cordio.cordio.security.jwt.JwtProvider;
import com.cordio.cordio.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthRest {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        if (signUpRequest.getPassword() == null) {
            signUpRequest.setPassword(SecurityUtil.generatePassword(6));
        }
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        Set<RoleName> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            Role roleFromDB = roleRepository.findByName(role)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
            roles.add(roleFromDB);
        });
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok().body("User registered successfully! Password - " + signUpRequest.getPassword());
    }
}
