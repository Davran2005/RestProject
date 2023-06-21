package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.menuItmen.MenuItemResponse;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

@Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m")
Page<MenuItemResponse> getAllResponse(Pageable pageable);
@Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)  from MenuItem m order by m.id asc ")
List<MenuItemResponse> getAsc();
    @Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)  from MenuItem m order by m.id desc ")
    List<MenuItemResponse> getDesc();
    @Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)  from MenuItem m where m.isVegetarian = true ")
    List<MenuItemResponse> getAllTrue();


    @Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)  from MenuItem m where m.isVegetarian = false")
    List<MenuItemResponse> getAllFalse();
@Query("select new peaksoft.dto.menuItmen.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from MenuItem m where m.id =:id")
    Optional<MenuItemResponse> getBYMenuId(Long id);
}