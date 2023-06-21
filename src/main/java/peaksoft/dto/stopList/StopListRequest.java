package peaksoft.dto.stopList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class StopListRequest{
    private String reason;
    private LocalDate date;

    public StopListRequest(String reason, LocalDate date) {
        this.reason = reason;
        this.date = date;
    }
}
