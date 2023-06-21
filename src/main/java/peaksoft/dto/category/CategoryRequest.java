package peaksoft.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CategoryRequest {
    private String name;

    public CategoryRequest(String name) {
        this.name = name;
    }
}
