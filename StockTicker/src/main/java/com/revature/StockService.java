package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepo;
	
	public List<Stock> getAllStock() {
		return stockRepo.viewAllStock();
	}
	
	public Optional<Stock> getStockByTicker(String ticker) {
		Stock stock = stockRepo.findByTicker(ticker);
		
		if (stock == null) {
			return Optional.empty();
		}
		
		return Optional.of(stock);
	}
	
	public List<Stock> getAllAboveCap(int cap) {
		
		if (cap < 0) {
			return new ArrayList<Stock>();
		}
		
		return stockRepo.getStockAboveCap(cap);
	}
	
	public List<Stock> getAllBelowCap(int cap) {
		
		if (cap < 0) {
			return new ArrayList<Stock>();
		}
		
		return stockRepo.getStockBelowCap(cap);
	}
	
	public boolean buyStock(String ticker, int price, int amount) {
		return false;
	}

}
