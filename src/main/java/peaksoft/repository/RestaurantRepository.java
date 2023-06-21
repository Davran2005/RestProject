package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.restoran.RestaurantResponse;
import peaksoft.entity.Restaurant;
import peaksoft.enums.RestaurantType;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("select new peaksoft.dto.restoran.RestaurantResponse(r.id,r.name,r.location,r.restaurantType,r.numberOfEmployees,r.services) from Restaurant r")
    List<RestaurantResponse>findAllRestaurant();

@Query("select new peaksoft.dto.restoran.RestaurantResponse(r.id,r.name,r.location,r.restaurantType,r.numberOfEmployees,r.services) from Restaurant r where r.id = :restaurantId")
    Optional<RestaurantResponse> findByRestaurant(Long restaurantId);
}