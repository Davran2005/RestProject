package peaksoft.dto.restoran;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AvmSumResResponse {
    private int sum;
}
