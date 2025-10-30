package apl.secure_user_registration.controller;

import apl.secure_user_registration.DTO.UserRequestDTO;
import apl.secure_user_registration.DTO.UserResponseDTO;
import apl.secure_user_registration.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import apl.secure_user_registration.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Validated @RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.createUser(userRequestDTO);
        UserResponseDTO response = UserResponseDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
               // .password(user.getPassword())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllAll() {
        List<User> users = userService.findByAll();

        List<UserResponseDTO> responseDTOS = users.stream()
                .map(user -> UserResponseDTO.builder()
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .build())
                .toList();

        return ResponseEntity.ok(responseDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateById(@PathVariable UUID id, @RequestBody User user) {
        User update = userService.updateById(id, user);

        UserResponseDTO responseDTO = UserResponseDTO.builder()
                .id(user.getId())
                .name(update.getName())
                .surname(update.getSurname())
                .email(update.getEmail())
               // .password(update.getPassword())
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteById(@PathVariable UUID id) {
        User user = userService.deleteUser(id);

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
               // .password(user.getPassword())
                .build();

        return ResponseEntity.ok(userResponseDTO);
    }
}
