package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepo;
	
	public List<Stock> getAllStock() {
		return stockRepo.viewAllStock();
	}
	
	public Optional<Stock> getStockByTicker(String ticker) {
		Optional<Stock> stock = stockRepo.findByTicker(ticker);
		
		if (stock == null) {
			return Optional.empty();
		}
		
		return stock;
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
		
		Optional<Stock> stockResult = stockRepo.findByTicker(ticker);
		
		if (stockResult.isEmpty()) {
			return false;
		}
		
		Stock stock = stockResult.get();
		
		if (stock.getAvailableStocks() < amount) {
			return false;
		}
		
		int newCap = stock.getMarketCap() + amount*(price - stock.getPrice());
		int newPrice = newCap / stock.getAvailableStocks();
		int newAmountAvailable = stock.getAvailableStocks() - amount;
		
		stockRepo.updateStock(ticker, newCap, newPrice, newAmountAvailable);
		
		return true;
	}

}
