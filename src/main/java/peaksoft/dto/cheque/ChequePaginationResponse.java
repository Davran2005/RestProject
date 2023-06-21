package peaksoft.dto.cheque;

import lombok.*;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChequePaginationResponse {
    private List<ChequeResponse> chequeResponses;
    private int currentPage;
    private int pageSize;
}
