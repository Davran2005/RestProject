package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.restoran.RestaurantRequest;
import peaksoft.dto.restoran.RestaurantResponse;

import java.util.List;

public interface RestaurantService {
    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurantById(Long restaurantId);
    SimpleResponse deleteRestaurantById(Long id);
    List<RestaurantResponse> getAllRestaurant();
    SimpleResponse updateRestaurant(Long id,RestaurantRequest restaurantRequest);
}
