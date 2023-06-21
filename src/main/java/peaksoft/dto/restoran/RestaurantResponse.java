package peaksoft.dto.restoran;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class RestaurantResponse {
    private Long id;
    private String name;
    private String location;
    private String restaurantType;
    private int numberOfEmployees;
    private int services;

    public RestaurantResponse(Long id, String name, String location, String restaurantType, int numberOfEmployees, int services) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.restaurantType = restaurantType;
        this.numberOfEmployees = numberOfEmployees;
        this.services = services;
    }
}
