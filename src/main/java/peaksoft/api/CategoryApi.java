package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.CategoryPaginationResponse;
import peaksoft.dto.category.CategoryRequest;
import peaksoft.dto.category.CategoryResponse;
import peaksoft.service.CategoryService;

@RestController
@RequestMapping("/api/restaurant/category")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryServices;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @Operation(summary = "token",description = "save")
    public SimpleResponse save(@RequestBody CategoryRequest request){
        return categoryServices.saveCategory(request);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping
    @Operation(summary = "token",description = "get all ")
    public CategoryPaginationResponse getAllCategories(@RequestParam int currentPage, @RequestParam int pageSize){
        return categoryServices.getAllCategory(currentPage, pageSize);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("delete/{id}")
    @Operation(summary = "token",description = "delete")
    public SimpleResponse delete(@PathVariable Long id){
        return categoryServices.deleteById(id);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @Operation(summary = "token",description = "get by id")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryServices.getById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "token",description = "update")
    public SimpleResponse update(@PathVariable Long id,@RequestBody CategoryRequest request){
        return categoryServices.update(id,request);
    }

}
