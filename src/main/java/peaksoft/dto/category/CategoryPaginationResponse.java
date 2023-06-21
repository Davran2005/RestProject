package peaksoft.dto.category;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryPaginationResponse {
    private List<CategoryResponse> categories;
    private int currentPage;
    private int pageSize;
}
