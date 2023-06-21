package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.restoran.RestaurantRequest;
import peaksoft.dto.restoran.RestaurantResponse;
import peaksoft.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantApi {
    private final RestaurantService restaurantServices;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return restaurantServices.saveRestaurant(restaurantRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/getAll")
    public List<RestaurantResponse> getAllMenuItems(){
        return restaurantServices.getAllRestaurant();
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantResponse findByRestaurantId(@PathVariable Long id) {
        return restaurantServices.getRestaurantById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteByRestaurantId(@PathVariable Long id) {
        return restaurantServices.deleteRestaurantById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequest restaurantRequest) {
        return restaurantServices.updateRestaurant(id, restaurantRequest);
    }
}
