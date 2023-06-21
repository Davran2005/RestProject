package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import peaksoft.config.JWTService;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.UserPaginationResponse;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.time.LocalDate;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestaurantRepository restaurantRepository;
    private final JWTService jwtServices;
    private User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.getUserByEmail(name).orElseThrow(() -> new NotFoundException("User with email: " + name + " us bit found!"));
        return user;
    }


    @Override
    public SimpleResponse saveUser(UserRequest userRequest) {
            if (userRequest.getFirstName().isBlank() &&
                    userRequest.getLastName().isBlank() &&
                    userRequest.getDateOfBirth() == null &&
                    userRequest.getEmail().isBlank() &&
                    !userRequest.getEmail().contains("@") &&
                    userRequest.getPassword().isBlank() &&
                    userRequest.getPhoneNumber().isBlank() &&
                    userRequest.getRole() == null &&
                    userRequest.getExperience() < 0) {
                throw new BadRequestException("save Exception");
            }
            if (userRequest.getRole().equals(Role.CHEF)) {
                if (userRequest.getExperience() < 2) {
                    throw new BadRequestException("The chef must have experience pain 2");
                }
                int now = LocalDate.now().getYear();
                int dateOfBirth = userRequest.getDateOfBirth().getYear();
                int num = now - dateOfBirth;
                if (num > 46 || num < 24) {
                    throw new BadRequestException("The chef age > 25 > 45 ");
                }
            }
            if (userRequest.getRole().equals(Role.WAITER)) {
                if (userRequest.getExperience() < 1) {
                    throw new BadRequestException("The waiter must have experience pain 1");
                }
                int now = LocalDate.now().getYear();
                int dateOfBirth = userRequest.getDateOfBirth().getYear();
                int num = now - dateOfBirth;
                if (num > 31 || num < 17) {
                    throw new BadRequestException("The waiter age > 18 > 30");
                }
            }
            if (!userRequest.getPhoneNumber().startsWith("+996")) {
                if (userRequest.getPhoneNumber().length() != 13) {
                    throw new BadRequestException("Phone number exception");
                }
            }
            if (userRepository.findAll().stream().count() != 0) {
                for (User user : userRepository.findAll()) {
                    if (userRequest.getEmail().equals(user.getEmail())) {
                        throw new BadRequestException("Email exception");
                    }
                }
            }
            User user = new User(
                    userRequest.getFirstName(),
                    userRequest.getLastName(),
                    userRequest.getDateOfBirth(),
                    userRequest.getEmail(),
                    passwordEncoder.encode(userRequest.getPassword()),
                    userRequest.getPhoneNumber(),
                    userRequest.getRole(),
                    userRequest.getExperience()
            );
            userRepository.save(user);
            var jwtToken = jwtServices.generateToken(user);
            System.out.println(jwtToken);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно")
                    .build();

    }

    @Override
    public UserResponse getUserById(Long id) {
        return userRepository.findByIdUser(id).orElseThrow(()->new NotFoundException("User with id: " + id + "is not fount"));
    }

    @Override
    public UserPaginationResponse getAllUser(int size, int page) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<UserResponse> allUser = userRepository.getAllUser(pageable);
        return UserPaginationResponse.builder()
                .userResponses(allUser.getContent())
                .currentPage(allUser.getNumber()+1)
                .pageSize(allUser.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse deleteUser(Long id) {
        User user = getAuthentication();
        User user1 = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " is not found!"));
        if (user.getRole().equals(Role.ADMIN)) {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Successfully")
                        .build();
            } else{
                throw new NotFoundException("User with id:" + id + " is not found");
            }
        } else {
            if (user.equals(user1)) {
                if (userRepository.existsById(id)) {
                    userRepository.deleteById(id);
                    return SimpleResponse.builder()
                            .status(HttpStatus.OK)
                            .message("Successfully")
                            .build();
                } else{
                    throw new NotFoundException("User with id:" + id + " is not found.");
                }
            } else throw new BadRequestException("You can not get user with id:" + user1.getId());
        }
    }

    @Override
    public SimpleResponse assign(Long userId, Long resId, String word) {
        Restaurant restaurant = restaurantRepository.findById(resId).orElseThrow(() -> new NotFoundException("Restaurant with id: " + resId + " is not found!"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " is not found!"));
        if (word.equalsIgnoreCase("add")) {
            restaurant.getUsers().add(user);
            restaurantRepository.save(restaurant);
            user.setRestaurant(restaurant);
            userRepository.save(user);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно добавили ")
                    .build();
        }
        if (word.equalsIgnoreCase("do not add")) {
            userRepository.delete(user);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно удалили")
                    .build();
        }
        return null;
    }

    @Override
    public SimpleResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Author with email :%s already exists", id)));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setRole(userRequest.getRole());
        user.setExperience(userRequest.getExperience());
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }
}
