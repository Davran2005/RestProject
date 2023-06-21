package peaksoft.dto.menuItmen;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuPaginationResponse {
    private List<MenuItemResponse> menuItemResponses;
    private int currentPage;
    private int pageSize;
}
