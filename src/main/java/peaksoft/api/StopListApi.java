package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.stopList.StopListPaginationResponse;
import peaksoft.dto.stopList.StopListRequest;
import peaksoft.dto.stopList.StopListResponse;
import peaksoft.service.StopListService;

@RestController
@RequestMapping("/stopList")
@RequiredArgsConstructor
public class StopListApi {

    private final StopListService stopListServices;

    @PostMapping("/save/{menuId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse save(@PathVariable Long menuId, @RequestBody StopListRequest request) {
        return stopListServices.saveStopList(menuId, request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/getAll")
    public StopListPaginationResponse getAllStopLists(@RequestParam int currentPage, @RequestParam int pageSize) {
        return stopListServices.getAllStopLists(currentPage, pageSize);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id) {
        return stopListServices.deleteStopListById(id);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public StopListResponse getById(@PathVariable Long id) {
        return stopListServices.getStopListById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF' )")
    public SimpleResponse update(@PathVariable Long id, @RequestBody StopListRequest request) {
        return stopListServices.updateStopList(id, request);
    }

}
