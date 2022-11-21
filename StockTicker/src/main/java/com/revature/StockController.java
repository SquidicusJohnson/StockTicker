package com.revature;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/api/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/viewall")
	public ResponseEntity<List<Stock>> getAllStock() {
		return ResponseEntity.ok(stockService.getAllStock());
	}

	@GetMapping("/getStock")
	public ResponseEntity<Stock> getCompanyStock(@RequestParam String ticker) {
		Optional<Stock> stock = stockService.getStockByTicker(ticker);
		
		if (stock.isPresent()) {
			return ResponseEntity.ok(stock.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/aboveCap")
	public ResponseEntity<List<Stock>> getAboveCap(@RequestParam int cap) {
		List<Stock> stocks = stockService.getAllAboveCap(cap);
		return ResponseEntity.ok(stocks);
	}
	
	@GetMapping("/belowCap")
	public ResponseEntity<List<Stock>> getBelowCap(@RequestParam int cap) {
		List<Stock> stocks = stockService.getAllBelowCap(cap);
		return ResponseEntity.ok(stocks);
	}
	
	@PostMapping("/buy")
	public void buyStock(@RequestParam String ticker, @RequestParam int amount, @RequestParam int price) {
		stockService.buyStock(ticker, price, amount);
	}
	
}