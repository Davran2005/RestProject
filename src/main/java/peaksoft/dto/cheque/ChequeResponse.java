package peaksoft.dto.cheque;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.entity.MenuItem;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class ChequeResponse {
    private Long id;
    private String fullName;
    private List<MenuItem> itemResponseList;
    private int avaPrice;
    private int services;
    private int grandTotal;

    public ChequeResponse(Long id, String fullName, List<MenuItem> itemResponseList, int avaPrice, int services, int grandTotal) {
        this.id = id;
        this.fullName = fullName;
        this.itemResponseList = itemResponseList;
        this.avaPrice = avaPrice;
        this.services = services;
        this.grandTotal = grandTotal;
    }
}
