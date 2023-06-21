package peaksoft.dto.stopList;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopListPaginationResponse {
    private List<StopListResponse> stopListResponses;
    private int currentPage;
    private int pageSize;
}
