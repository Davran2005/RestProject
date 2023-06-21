package peaksoft.dto.user;

import io.swagger.v3.oas.models.links.Link;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Role;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Setter
@Getter
@Builder

public class UserResponse{
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private Role role;
    private   int experience;

    public UserResponse(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email, String phoneNumber, Role role, int experience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.experience = experience;
    }

    public UserResponse(Long id, String firstName, LocalDate dateOfBirth, String email, String phoneNumber, Role role, int experience) {


    }
}
