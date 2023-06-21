package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.subcategory.SubCategoryPaginationResponse;
import peaksoft.dto.subcategory.SubcategoryRequest;
import peaksoft.dto.subcategory.SubcategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.SubCategoryService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public SimpleResponse saveSubcategory(Long id, SubcategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Category with id: " + id + "is not found")));
        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.getName());
        subCategory.setCategory(category);
        subcategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public SubcategoryResponse getById(Long id) {
        SubCategory subCategory = subcategoryRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Sub category with id: " + id + "is not found")));
        return SubcategoryResponse.builder()
                .name(subCategory.getName())
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        if (subcategoryRepository.existsById(id)) {
            subcategoryRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Sub category with id: " + id + "is not found"));
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public SubCategoryPaginationResponse getAll(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));
        Page<SubcategoryResponse> allSub = subcategoryRepository.getAll(pageable);
        return SubCategoryPaginationResponse.builder()
                .subcategoryResponses(allSub.getContent())
                .currentPage(allSub.getNumber()+1)
                .pageSize(allSub.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse update(Long id, SubcategoryRequest request) {
        SubCategory subcategory = subcategoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format
                        ("Sub category with id: " + id + "is not found")));
        subcategory.setName(request.getName());
        subcategoryRepository.save(subcategory);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public SubcategoryResponse filterSubCategoryByCategory(String categoryName) {
        return subcategoryRepository.SubCategoryByCategory(categoryName);
    }

    @Override
    public List<SubcategoryResponse> sort(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("Asc")) {
            subcategoryRepository.sortAsc();
        }
        if (ascOrDesc.equalsIgnoreCase("Desc")) {
            subcategoryRepository.sortDesc();
        }
        SubCategory subcategory = new SubCategory();
        return Collections.singletonList(SubcategoryResponse.builder()
                .name(subcategory.getName())
                .build());
    }

    @Override
    public SubCategoryPaginationResponse getAllSubCategoryByGroup(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));
        Page<SubcategoryResponse> allSub = subcategoryRepository.groupBySubCategory(pageable);
        return SubCategoryPaginationResponse.builder()
                .subcategoryResponses(allSub.getContent())
                .currentPage(allSub.getNumber()+1)
                .pageSize(allSub.getTotalPages())
                .build();
    }

    @Override
    public List<SubcategoryResponse> searchByName(String word) {
        return subcategoryRepository.searchByName(word);
    }
}
