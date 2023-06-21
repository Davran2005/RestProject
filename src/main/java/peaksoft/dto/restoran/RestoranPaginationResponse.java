package peaksoft.dto.restoran;

import lombok.*;
import peaksoft.dto.menuItmen.MenuItemResponse;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestoranPaginationResponse {
    private List<RestaurantResponse> restaurantResponses;
    private int currentPage;
    private int pageSize;
}
