package peaksoft.dto.cheque;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class ChequeRequest{
       private int priceAverage;
       private List<Long>menuItems;

    public ChequeRequest(int priceAverage, List<Long> menuItems) {
        this.priceAverage = priceAverage;
        this.menuItems = menuItems;
    }
}

