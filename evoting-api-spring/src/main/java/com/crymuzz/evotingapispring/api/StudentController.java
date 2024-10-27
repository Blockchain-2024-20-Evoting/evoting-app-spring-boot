package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.UserProfileDTO;
import com.crymuzz.evotingapispring.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class StudentController {

    private final IUserService userService;

    // Obtener su perfil por su ID
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Long id, @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updateProfile = userService.update(id, userProfileDTO);
        return ResponseEntity.ok(updateProfile);
    }
    // Actualizar su perfil
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Long id) {
        UserProfileDTO userProfileDTO = userService.findById(id);
        return ResponseEntity.ok(userProfileDTO);
    }
}
