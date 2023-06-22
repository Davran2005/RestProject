package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.subcategory.SubCategoryPaginationResponse;
import peaksoft.dto.subcategory.SubcategoryRequest;
import peaksoft.dto.subcategory.SubcategoryResponse;
import peaksoft.service.SubCategoryService;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
@RequiredArgsConstructor
public class SubcategoryApi {
    private final SubCategoryService subcategoryServices;

    @PostMapping("/save/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse save(@PathVariable Long categoryId, @RequestBody SubcategoryRequest request) {
        return subcategoryServices.saveSubcategory(categoryId,request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/getAll")
    public SubCategoryPaginationResponse getAllSubCategories(@RequestParam int currentPage, @RequestParam int pageSize){
        return subcategoryServices.getAll(currentPage, pageSize);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SubcategoryResponse getById(@PathVariable Long id) {
        return subcategoryServices.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id) {
        return subcategoryServices.delete(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,@RequestBody SubcategoryRequest request){
        return subcategoryServices.update(id, request);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/filter/{categoryId}")
public List<SubcategoryResponse> filterAndSort(@PathVariable Long categoryId, @RequestParam(required = false) String ascOrDesc){
        return subcategoryServices.getAllSubCategoryOrderByCategoryName(categoryId,ascOrDesc);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/byGroup")
    public SubCategoryPaginationResponse getSubCategoryByGroup(@RequestParam int currentPage , @RequestParam int pageSize){
        return subcategoryServices.getAllSubCategoryByGroup(currentPage, pageSize);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @GetMapping("/search")
    public List<SubcategoryResponse> search(@RequestParam String word){
        return subcategoryServices.searchByName(word);
    }
}
