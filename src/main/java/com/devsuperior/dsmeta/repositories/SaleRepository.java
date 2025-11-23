package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SummarySalePerSellerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

        @Query(nativeQuery = true, value = "SELECT s.name AS sellerName, SUM(sa.amount) AS saleSum " +
                "FROM TB_SALES sa " +
                "INNER JOIN TB_SELLER s ON sa.seller_id = s.id " +
                "WHERE sa.date BETWEEN (:minDate) AND (:maxDate) " +
                "GROUP BY s.name ")
        Page<SummarySalePerSellerProjection> search1(
                LocalDate minDate,
                LocalDate maxDate,
                Pageable pageable
                 /*
        Obs: É bom lembrar que os apelidos devem ser os mesmo que estão no projection.

        Obs: Usando cameCase nas variaveis que passamos como parametro do search.
        * */
        );


        @Query(nativeQuery = true,
                value = "SELECT sa.id AS id, s.name AS sellerName, sa.date AS date, sa.amount AS amount " +
                    "FROM TB_SALES sa " +
                    "INNER JOIN TB_SELLER s ON sa.seller_id = s.id " +
                    "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
                    "AND sa.date BETWEEN :minDate AND :maxDate " +
                    "ORDER BY sa.date DESC",
                countQuery = "SELECT COUNT(*) " +
                    "FROM TB_SALES sa " +
                    "INNER JOIN TB_SELLER s ON sa.seller_id = s.id " +
                    "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
                    "AND sa.date BETWEEN :minDate AND :maxDate")
        Page<SaleReportProjection> search2(
            LocalDate minDate,
            LocalDate maxDate,
            String name,
            Pageable pageable
        );
}

/*Usando o CONCAT nós implementamos o filtro por nome.*/