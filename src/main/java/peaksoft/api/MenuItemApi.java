package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.menuItmen.MenuItemRequest;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.dto.menuItmen.MenuPaginationResponse;
import peaksoft.service.MenuItemServices;

import java.util.List;


@RestController
@RequestMapping("/api/menuItem")
@RequiredArgsConstructor
public class MenuItemApi {
    private final MenuItemServices menuItemServices;
    @PostMapping("/save/{restaurantId}/{subCategoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@PathVariable("restaurantId") Long restaurantId, @PathVariable("subCategoryId") Long subCategoryId,@RequestBody MenuItemRequest request){
        return menuItemServices.save(restaurantId,subCategoryId,request);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    public MenuItemResponse getById(@PathVariable Long id){
        return menuItemServices.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return menuItemServices.delete(id);
    }

    @GetMapping("/getAllOrder/{descOrAsc}")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    public List<MenuItemResponse> getAllOrder(@PathVariable String descOrAsc){
        return menuItemServices.getAllOrder(descOrAsc);
    }

    @GetMapping("/getAllVega/{descOrAsc}")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    public List<MenuItemResponse> getAllVega(@PathVariable Boolean descOrAsc){
        return menuItemServices.getAllVega(descOrAsc);
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse update(@PathVariable Long id,@RequestBody MenuItemRequest request) {
        return menuItemServices.update(id, request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/getAll")
    public MenuPaginationResponse getAllMenuItems(@RequestParam int currentPage, @RequestParam  int pageSize){
        return menuItemServices.getAllResponse( currentPage, pageSize);
    }
}
