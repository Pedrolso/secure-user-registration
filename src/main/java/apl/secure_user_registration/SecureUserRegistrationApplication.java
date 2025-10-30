package apl.secure_user_registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "apl.secure_user_registration")
public class SecureUserRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureUserRegistrationApplication.class, args);
    }

}