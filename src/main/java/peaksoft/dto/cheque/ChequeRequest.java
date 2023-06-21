package peaksoft.dto.cheque;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.MenuItem;

import java.util.List;

@Builder
@Getter
@Setter
public class ChequeRequest{
       private int priceAverage;
       private List<Long>menuItems;

    public ChequeRequest(int priceAverage, List<Long> menuItems) {
        this.priceAverage = priceAverage;
        this.menuItems = menuItems;
    }
}

