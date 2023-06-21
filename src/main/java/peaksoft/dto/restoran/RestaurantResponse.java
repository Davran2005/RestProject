package peaksoft.dto.restoran;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.RestaurantType;

@Builder
@Setter
@Getter
public class RestaurantResponse {
    private Long id;
    private String name;
    private String location;
    private RestaurantType restaurantType;
    private int services;

    public RestaurantResponse(Long id, String name, String location, RestaurantType restaurantType,  int services) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.restaurantType = restaurantType;
        this.services = services;
    }
}
