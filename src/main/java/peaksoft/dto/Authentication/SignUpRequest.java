package peaksoft.dto.Authentication;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private int experience;

}
