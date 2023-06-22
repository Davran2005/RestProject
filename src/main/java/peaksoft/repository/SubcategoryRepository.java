package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.subcategory.SubcategoryResponse;
import peaksoft.entity.SubCategory;


import java.util.List;

public interface SubcategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name,s.category.name)  from SubCategory s")
    Page<SubcategoryResponse> getAll(Pageable pageable);
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id, s.name) " +
            "from SubCategory s " +
            "where s.category.id = :categoryId " +
            "order by case when :ascOrDesc = 'asc' then s.name end asc, " +
            "case when :ascOrDesc = 'desc' then s.name end desc")
    List<SubcategoryResponse> getAllSubCategoryOrderByCategoryName(@Param("categoryId") Long categoryId, @Param("ascOrDesc") String ascOrDesc);
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name) from SubCategory  s group by s.id,s.name,s.category.name")
    Page<SubcategoryResponse> groupBySubCategory(Pageable pageable);
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name)  from SubCategory s where s.name ilike concat(:word,'%') or " +
            " s.name ilike concat('%',:word,'%')" )
    List<SubcategoryResponse> searchByName(@Param("word") String word);
}