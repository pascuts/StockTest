package com.jpmorgan.dao;

import java.util.Collection;
import java.util.List;

import com.jpmorgan.model.Stock;
import com.jpmorgan.model.Trade;
/**
 * 
 * @author Adrian.Pascut
 *
 */
public interface StockDao {
	public Stock getStockBySymbol(String symbol);
	public Trade saveNewTrade(Trade trade);
	public void saveOldTrade(Trade trade);
	public List<Trade> getTradesInLastTimeInterval(long timeInterval);
	public Collection<Stock> getAllStocks();
	public Stock saveNewStock(Stock trade);
	public void saveOldStock(Stock trade);
}
