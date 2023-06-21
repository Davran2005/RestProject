package peaksoft.dto.subcategory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SubcategoryRequest {
    private String name;

    public SubcategoryRequest(String name) {
        this.name = name;
    }
}
