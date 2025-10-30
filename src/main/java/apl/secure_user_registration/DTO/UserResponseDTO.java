package apl.secure_user_registration.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder

public class UserResponseDTO {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    //private String password;

}
