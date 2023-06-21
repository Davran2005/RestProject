package peaksoft.dto.subcategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder

public record SubcategoryResponse(Long id,String name,String categoryName) {
    public SubcategoryResponse(Long id, String name) {
        this(id, name, null);


    }
}
