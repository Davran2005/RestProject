package peaksoft.dto.restoran;

import lombok.*;

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
