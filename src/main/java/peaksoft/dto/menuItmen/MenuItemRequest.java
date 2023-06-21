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
    private Long subcategoryId;

    public MenuItemRequest(String name, String image, int price, String description, Boolean isVegetarian, Long subcategoryId) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.isVegetarian = isVegetarian;
        this.subcategoryId = subcategoryId;
    }
}
