package apl.secure_user_registration.service;

import apl.secure_user_registration.DTO.UserRequestDTO;
import apl.secure_user_registration.entity.User;
import apl.secure_user_registration.exception.ConflictException;
import apl.secure_user_registration.exception.NotFound;
import apl.secure_user_registration.utils.UserFormatter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import apl.secure_user_registration.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFormatter userFormatter;


    //POST
    public User createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new ConflictException("Email already exists.");
        }

        User user = User.builder()
                .name(userFormatter.capitalizeName(userRequestDTO.getName()))
                .surname(userFormatter.capitalizeName(userRequestDTO.getSurname()))
                .email(userFormatter.lowerCase(userRequestDTO.getEmail()))
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .role(userRequestDTO.getRole())
                .build();

        return userRepository.saveAndFlush(user);
    }

    //GET ALL
    public List<User> findByAll() {

        return userRepository.findAll();
    }

    //UPDATE
    public User updateById(UUID id, User user) {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User ID not found."));

        User userUpdate = User.builder()
                .id(userEntity.getId())
                .name(user.getName() != null ?
                        user.getName() : userEntity.getName())
                .surname(user.getSurname() != null ?
                        user.getSurname() : userEntity.getSurname())
                .email(user.getEmail() != null ?
                        user.getEmail() : userEntity.getEmail())
                .password(user.getPassword() != null ?
                        passwordEncoder.encode(user.getPassword()) : userEntity.getPassword())
                .role(user.getRole() != null ?
                        user.getRole() : userEntity.getRole())
                .build();

        return userRepository.saveAndFlush(userUpdate);
    }

    //DELETE
    public User deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User ID not found."));

        userRepository.deleteById(id);
        return user;
    }

}

