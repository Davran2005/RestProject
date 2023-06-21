package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.subcategory.SubCategoryPaginationResponse;
import peaksoft.dto.subcategory.SubcategoryResponse;
import peaksoft.entity.Subcategory;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name)  from Subcategory s")
    Page<SubcategoryResponse> getAll(Pageable pageable);
    @Query("select  new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name) from Subcategory  s where s.category.name=:word")
    SubcategoryResponse SubCategoryByCategory(String word);
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name)  from Subcategory s order by s.name asc ")
    List<SubcategoryResponse> sortAsc();
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name)  from Subcategory s order by s.name desc ")
    List<SubcategoryResponse> sortDesc();
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name) from Subcategory  s group by s.id,s.name,s.category.name")
    Page<SubcategoryResponse> groupBySubCategory(Pageable pageable);
    @Query("select new peaksoft.dto.subcategory.SubcategoryResponse(s.id,s.name)  from Subcategory s where s.name ilike concat(:word,'%') or " +
            " s.name ilike concat('%',:word,'%')" )
    List<SubcategoryResponse> searchByName(@Param("word") String word);
}