package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.AuthResponseDTO;
import com.crymuzz.evotingapispring.entity.dto.LoginDTO;
import com.crymuzz.evotingapispring.entity.dto.UserProfileDTO;
import com.crymuzz.evotingapispring.entity.dto.UserRegisterDTO;
import com.crymuzz.evotingapispring.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    // Registrar estudiantes
    @PostMapping("/register/student")
    public ResponseEntity<UserProfileDTO> registerStudent(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        UserProfileDTO userProfile = userService.saveStudent(userRegisterDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = userService.login(loginDTO);
        return ResponseEntity.ok(authResponseDTO);
    }

}
