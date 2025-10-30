package apl.secure_user_registration.utils;

import org.springframework.stereotype.Component;

@Component
public class UserFormatter {


    public String lowerCase(String input) {
        String lowerC = input.toLowerCase().trim();

        return lowerC;
    }


    public String capitalizeName(String input) {
        String[] parts = input.trim().toLowerCase().split("\\s+");
        StringBuilder formatted = new StringBuilder();

        for (String part : parts) {
            if (part.isBlank()) continue;
            formatted.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1))
                    .append(" ");
        }

        return formatted.toString().trim();
    }
}
