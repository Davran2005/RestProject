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

        int countRestaurant = getAllRestaurant().size();
        if (countRestaurant < 1) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantRequest.getName());
            restaurant.setLocation(restaurantRequest.getLocation());
            restaurant.setRestaurantType(restaurantRequest.getRestaurantType());
            restaurant.setServices(restaurantRequest.getServices());
            restaurantRepository.save(restaurant);
                throw new BadRequestException("When saving the restaurant, one of the columns remained empty");
            } else {
                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email: %s not found".formatted(email)));
                Restaurant restaurant = new Restaurant();
                if (restaurant.getUsers().size()<15) {
                    restaurant.setNumberOfEmployees(restaurant.getUsers().size() + 1);
                }else {
                    throw new BadRequestException("Сотрудник больше 15 нельзя");
                }
                restaurantRepository.save(restaurant);
                user.setRestaurant(restaurant);
                return SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Успешно")
                        .build();
            }
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
                .restaurantType(restaurant.getRestaurantType())
                .services(restaurant.getServices())
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
        restaurant.setRestaurantType(restaurantRequest.getRestaurantType());
        restaurant.setServices(restaurantRequest.getServices());
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }
}
