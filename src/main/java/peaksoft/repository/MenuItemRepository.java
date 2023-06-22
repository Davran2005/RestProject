package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("select new peaksoft.dto.menuItmen.MenuItemResponse" +
            "(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)" +
            " from MenuItem m order by case when:sortOrder='asc' then m.price end asc ," +
            "case when:sortOrder='desc' then m.price end desc ")
    Page<MenuItemResponse> getAll(@Param("sortOrder") String sortOrder,Pageable pageable);
    @Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)  from MenuItem m where m.isVegetarian = :trueOrFalse ")
    List<MenuItemResponse> getAllTrue(@Param("trueOrFalse") boolean trueOrFalse);

@Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m where m.id =:id")
    Optional<MenuItemResponse> getBYMenuId(Long id);
}