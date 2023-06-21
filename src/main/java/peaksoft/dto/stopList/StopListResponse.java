package peaksoft.dto.stopList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class StopListResponse {
    private Long id;
    private String reason;
    private LocalDate date;

    public StopListResponse(Long id, String reason, LocalDate date) {
        this.id = id;
        this.reason = reason;
        this.date = date;

    }
}
