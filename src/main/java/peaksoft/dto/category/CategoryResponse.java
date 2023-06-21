package peaksoft.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CategoryResponse {
    private Long id;
    private String name;

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
