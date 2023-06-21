package peaksoft.dto.restoran;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class RestaurantRequest {
    private String name;
    private String location;
    private String restaurantType;
    private int services;

    public RestaurantRequest(String name, String location, String restaurantType, int services) {
        this.name = name;
        this.location = location;
        this.restaurantType = restaurantType;
        this.services = services;
    }
}
