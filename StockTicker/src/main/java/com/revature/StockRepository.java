package com.revature;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
	
	@Query(value = "select * from stock")
	List<Stock> viewAllStock();
	
	@Query(value = "select * from stock where ticker=?1")
	Stock findByTicker(String ticker);
	
	@Query(value = "select * from stock where market_cap > ?1")
	List<Stock> getStockAboveCap(int cap);
	
	@Query(value = "select * from stock where market_cap < ?1")
	List<Stock> getStockBelowCap(int cap);
	
}