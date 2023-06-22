package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.enums.Role;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private int experience;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Restaurant restaurant;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Cheque> cheques;

    public User(String firstName, String lastName, LocalDate dateOfBirth, String email, String password, String phoneNumber, Role role, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.experience = experience;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}