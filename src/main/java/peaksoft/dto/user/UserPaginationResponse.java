package peaksoft.dto.user;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPaginationResponse {
    private List<UserResponse> userResponses;
    private int currentPage;
    private int pageSize;
}
