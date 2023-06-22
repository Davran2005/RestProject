package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.menuItmen.MenuItemRequest;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.dto.menuItmen.MenuPaginationResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;

import peaksoft.entity.SubCategory;
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
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(() -> new NotFoundException(String.format("SubCategory with id: " + subCategoryId +"is not found" )));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setRestaurant(restaurant);

        menuItem.setSubCategory(subCategory);
        menuItem.setVegetarian(menuItemRequest.getIsVegetarian());
        repository.save(menuItem);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();

    }

    @Override
    public SimpleResponse update(Long id, MenuItemRequest request) {
        MenuItem menuItem = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Menu whit id: " + id + "is not found")));
        menuItem.setName(request.getName());
        menuItem.setDescription(request.getDescription());
        menuItem.setPrice(request.getPrice());
        menuItem.setImage(request.getImage());
        menuItem.setVegetarian(request.getIsVegetarian());
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
    public MenuPaginationResponse getAllResponse(int size, int page,String ascOrDesc) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<MenuItemResponse> allMenu = repository.getAll(ascOrDesc,pageable);
        return MenuPaginationResponse.builder()
                .menuItemResponses(allMenu.getContent())
                .currentPage(allMenu.getNumber()+1)
                .pageSize(allMenu.getTotalPages())
                .build();
    }


    @Override
    public List<MenuItemResponse> getAllVega(Boolean vegOrNot) {
        return repository.getAllTrue(vegOrNot);
    }
}
