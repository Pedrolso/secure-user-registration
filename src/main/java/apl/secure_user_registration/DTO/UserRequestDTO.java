package apl.secure_user_registration.DTO;

import apl.secure_user_registration.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole role;

}
