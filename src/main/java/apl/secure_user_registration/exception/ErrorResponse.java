package apl.secure_user_registration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class ErrorResponse {

    private String global_error;
    private String service_message;
    private LocalDateTime timestamp;
    private int status;

}
