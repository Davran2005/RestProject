package peaksoft.dto.restoran;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.RestaurantType;

@Builder
@Setter
@Getter
public class RestaurantRequest {
    private String name;
    private String location;
    private RestaurantType restaurantType;
    private int numberOfEmployees;
    private int services;

    public RestaurantRequest(String name, String location, RestaurantType restaurantType, int numberOfEmployees, int services) {
        this.name = name;
        this.location = location;
        this.restaurantType = restaurantType;
        this.numberOfEmployees = numberOfEmployees;
        this.services = services;
    }
}
