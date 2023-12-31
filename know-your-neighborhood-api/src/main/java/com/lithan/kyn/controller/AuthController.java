package com.lithan.kyn.controller;

import java.net.URI;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lithan.kyn.entity.EAuthProvider;
import com.lithan.kyn.entity.ERole;
import com.lithan.kyn.entity.Roles;
import com.lithan.kyn.entity.UserAccount;
import com.lithan.kyn.model.ApiResponse;
import com.lithan.kyn.model.AuthResponse;
import com.lithan.kyn.model.LoginDto;
import com.lithan.kyn.model.RegisterDto;
import com.lithan.kyn.repository.RolesRepository;
import com.lithan.kyn.repository.UserAccountRepository;
import com.lithan.kyn.security.JWTGenerator;
import com.lithan.kyn.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserAccountRepository userRepo;

  @Autowired
  private RolesRepository rolesRepo;

  @Autowired
  private JWTGenerator jwtGenerator;

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginDto.getEmail(),
            loginDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtGenerator.generateToken(authentication);
    return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) throws Exception {
    if (userRepo.existsByEmail(registerDto.getEmail())) {
      return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
    }

    UserAccount user = new UserAccount();
    user.setName(registerDto.getName());
    user.setEmail(registerDto.getEmail());
    user.setAddress(registerDto.getAddress());
    user.setPhoneNumber(registerDto.getPhoneNumber());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    user.setProvider(EAuthProvider.facebook);

    Roles role = rolesRepo.findByName(ERole.ROLE_USER);
    if (role == null) {
      role = userService.createRole(ERole.ROLE_USER);
    }
    user.setRoles(Arrays.asList(role));

    UserAccount newUser = userRepo.save(user);

    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/api/users/me")
        .buildAndExpand(newUser.getUserId()).toUri();

    return ResponseEntity.created(location)
        .body(new ApiResponse(true, "User registered successfully"));
  }
}
