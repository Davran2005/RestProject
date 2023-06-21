package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.menuItmen.MenuItemRequest;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.dto.menuItmen.MenuPaginationResponse;


import java.util.List;

public interface MenuItemServices {
    SimpleResponse save(Long restaurantId, Long subCategoryId, MenuItemRequest menuItemRequest);

    SimpleResponse update(Long id, MenuItemRequest request);

    MenuItemResponse getById(Long id);

    SimpleResponse delete(Long id);

    MenuPaginationResponse getAllResponse(int size, int page);

    List<MenuItemResponse> getAllOrder(String descOrAsc);

    List<MenuItemResponse> getAllVega(Boolean vegOrNot);



}
