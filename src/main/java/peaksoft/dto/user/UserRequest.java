package peaksoft.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;


import java.time.LocalDate;
import java.time.ZonedDateTime;

@Builder
@Setter
@Getter

public class UserRequest {
    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
    private String email;

    private String password;

    private String phoneNumber;
    private Role role;

    private int experience;

    public UserRequest(String firstName, String lastName, LocalDate dateOfBirth, String email, String password, String phoneNumber, Role role, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.experience = experience;
    }
}
