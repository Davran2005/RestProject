package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.CategoryPaginationResponse;
import peaksoft.dto.category.CategoryRequest;
import peaksoft.dto.category.CategoryResponse;

public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest request);
   CategoryPaginationResponse getAllCategory(int size, int page);
    CategoryResponse getById(Long id);
    SimpleResponse deleteById(Long id);
    SimpleResponse update(Long id,CategoryRequest request);
}
