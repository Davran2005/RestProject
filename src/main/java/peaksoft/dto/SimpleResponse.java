package peaksoft.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
public class SimpleResponse {
    private HttpStatus status;
    private String message;

    public SimpleResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
