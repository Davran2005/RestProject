package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.user.UserResponse;
import peaksoft.entity.User;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);
    @Query("select new peaksoft.dto.user.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.phoneNumber,u.role,u.experience) from User u")
    Page<UserResponse> getAllUser(Pageable pageable);
@Query("select new peaksoft.dto.user.UserResponse(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.phoneNumber,u.role,u.experience) from User u where u.id = :id")
    Optional<UserResponse> findByIdUser(Long id);

    @Query("select u from User u where u.email = :email")
    Optional<User> getUserByEmail(String email);


}