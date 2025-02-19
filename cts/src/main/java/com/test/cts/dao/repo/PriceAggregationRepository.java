package com.test.cts.dao.repo;

import com.test.cts.dao.model.crypto.PriceAggregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceAggregationRepository extends JpaRepository<PriceAggregation, String> {

    PriceAggregation findEntityBySymbol(String symbol);

    @Query("SELECT a FROM PriceAggregation a WHERE a.symbol LIKE CONCAT('%', :key, '%')")
    List<PriceAggregation> findAllBySymbol(String key);
}
