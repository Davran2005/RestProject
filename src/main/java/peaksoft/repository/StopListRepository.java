package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.stopList.StopListResponse;
import peaksoft.entity.StopList;

import java.time.LocalDate;

public interface StopListRepository extends JpaRepository<StopList, Long> {
    @Query("select  new peaksoft.dto.stopList.StopListResponse(s.id,s.reason,s.date)  from StopList s")
    Page<StopListResponse> getAllStopLists(Pageable pageable);





}