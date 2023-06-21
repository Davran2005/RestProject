package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.entity.Cheque;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select " +
            "(CONCAT(u.lastName, ' ', u.firstName)," +
            "AVG(m.price)," +
            "r.services," +
            "(SELECT COALESCE(SUM(m.price * r.services / 100 ) + SUM(m.price), 0) FROM MenuItem m JOIN m.cheques c WHERE c.id = ?1)) " +
            "FROM Cheque c " +
            "JOIN c.user u " +
            "LEFT JOIN c.menuItems m " +
            "JOIN u.restaurant r " +
            "WHERE c.id = ?1 " +
            "GROUP BY CONCAT(u.lastName, ' ', u.firstName),r.services")
    Optional<ChequeResponse> sumAv(Long id);
}