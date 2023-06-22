package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.restoran.RestaurantRequest;
import peaksoft.dto.restoran.RestaurantResponse;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {

        if (!restaurantRepository.findAll().isEmpty()) {
            throw new AlreadyExistException("You mast save only 1 Restaurant");
        }
        if (restaurantRepository.existsByName(restaurantRequest.getName())) {
            return SimpleResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(String.format("Restaurant with name : %s already exists", restaurantRequest.getName()))
                    .build();
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: %s not found".formatted(email)));
        Restaurant restaurant = user.getRestaurant();
        Restaurant restaurant2 = Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .restType(restaurantRequest.getRestaurantType())
                .service(restaurantRequest.getServices())
                .build();
        restaurantRepository.save(restaurant2);
        user.setRestaurant(restaurant);
        userRepository.save(user);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name: %s successfully saved!",
                        restaurantRequest.getName()))
                .build();
        }


    @Override
    public RestaurantResponse getRestaurantById(Long restaurantId) {
        try {
            return restaurantRepository.findByRestaurant(restaurantId).orElseThrow(() ->
                    new NotFoundException(String.format("There is no restaurant with this Id %s", restaurantId)));
        } catch (NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        Restaurant restaurant = new Restaurant();
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .restaurantType(restaurant.getRestType())
                .services(restaurant.getService())
                .build();
    }

    @Override
    public SimpleResponse deleteRestaurantById(Long id) {
        try {
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("There is no restaurant with this Id %s", id));
            restaurantRepository.delete(restaurant);
        }catch (NotFoundException e){
            System.err.println(e.getMessage());
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public List<RestaurantResponse> getAllRestaurant() {
        return restaurantRepository.findAllRestaurant();
    }

    @Override
    public SimpleResponse updateRestaurant(Long id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format
                        ("There is no restaurant with this Id %s", id)));
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestaurantType());
        restaurant.setService(restaurantRequest.getServices());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }
}
