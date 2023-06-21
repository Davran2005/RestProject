package peaksoft.dto.subcategory;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryPaginationResponse {
    private List<SubcategoryResponse> subcategoryResponses;
    private int currentPage;
    private int pageSize;
}
