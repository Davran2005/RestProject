package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.subcategory.SubCategoryPaginationResponse;
import peaksoft.dto.subcategory.SubcategoryRequest;
import peaksoft.dto.subcategory.SubcategoryResponse;

import java.util.List;

public interface SubCategoryService {
    SimpleResponse saveSubcategory(Long id, SubcategoryRequest request);
    SubcategoryResponse getById(Long id);
    SimpleResponse delete(Long id);
    SubCategoryPaginationResponse getAll(int size, int page);
    SimpleResponse update(Long id,SubcategoryRequest request);
    public List<SubcategoryResponse> getAllSubCategoryOrderByCategoryName(Long categoryId, String ascOrDesc);
    SubCategoryPaginationResponse getAllSubCategoryByGroup(int size, int page);
    List<SubcategoryResponse> searchByName(String word);
}
