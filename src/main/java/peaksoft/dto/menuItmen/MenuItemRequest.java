package peaksoft.dto.menuItmen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class MenuItemRequest {
    private String name;
    private String image;
    private int price;
    private String description;
    private Boolean isVegetarian;

    public MenuItemRequest(String name, String image, int price, String description, Boolean isVegetarian) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
    }

    public MenuItemRequest() {
    }
}
