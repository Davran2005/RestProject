package peaksoft.dto.menuItmen;

import lombok.*;
import peaksoft.dto.cheque.ChequeResponse;

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
