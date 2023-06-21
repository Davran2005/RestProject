package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.ChequePaginationResponse;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.dto.menuItmen.MenuItemRequest;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.dto.menuItmen.MenuPaginationResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.Subcategory;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.MenuItemServices;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MenuItemServicesImpl implements MenuItemServices {
    private final MenuItemRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final SubcategoryRepository subCategoryRepository;


    public SimpleResponse save(Long restaurantId, Long subCategoryId, MenuItemRequest menuItemRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException(String.format("Restaurant with id: " + restaurantId + "is not found")));
        Subcategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new NotFoundException(String.format("SubCategory with id: " + subCategoryId +"is not found" )));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setRestaurant(restaurant);
        menuItem.setSubCategory(subCategory);
        menuItem.setIsVegetarian(menuItemRequest.getIsVegetarian());
        repository.save(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();

    }

    @Override
    public SimpleResponse update(Long id, MenuItemRequest request) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.getName());
        menuItem.setImage(request.getImage());
        menuItem.setPrice(request.getPrice());
        menuItem.setDescription(request.getDescription());
        menuItem.setIsVegetarian(request .getIsVegetarian());
        repository.save(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public MenuItemResponse getById(Long id) {
        return repository.getBYMenuId(id).orElseThrow(() ->
                new NotFoundException(String.format("MenuItem with id :%s already exists", id)));    }

    @Override
    public SimpleResponse delete(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return  SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Successfully deleted...")
                    .build();
        }else{
            throw new NotFoundException(String.format("MenuItem with id:%s does not exist", id));
        }
    }

    @Override
    public MenuPaginationResponse getAllResponse(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));
        Page<MenuItemResponse> allMenu = repository.getAllResponse(pageable);
        return MenuPaginationResponse.builder()
                .menuItemResponses(allMenu.getContent())
                .currentPage(allMenu.getNumber()+1)
                .pageSize(allMenu.getTotalPages())
                .build();
    }
    @Override
    public List<MenuItemResponse> getAllOrder(String descOrAsc) {
        if (descOrAsc.equals("Asc")) {
            repository.getAsc();
        }
        if (descOrAsc.equals("Desc")){
            repository.getDesc();
        }
        MenuItem  menuItem = new MenuItem();
        return Collections.singletonList(MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .isVegetarian(menuItem.getIsVegetarian())
                .build());
    }

    @Override
    public List<MenuItemResponse> getAllVega(Boolean vegOrNot) {
        if (vegOrNot.equals(true)){
            repository.getAllTrue();
        }
        if (vegOrNot.equals(false)) {
            repository.getAllFalse();
        }
        MenuItem  menuItem = new MenuItem();
        return Collections.singletonList(MenuItemResponse.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .isVegetarian(menuItem.getIsVegetarian())
                .build());
    }
}
