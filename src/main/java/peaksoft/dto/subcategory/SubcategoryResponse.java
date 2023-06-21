package peaksoft.dto.subcategory;

import lombok.Builder;

@Builder

public record SubcategoryResponse(Long id,String name,String categoryName) {
    public SubcategoryResponse(Long id, String name) {
        this(id, name, null);


    }
}
