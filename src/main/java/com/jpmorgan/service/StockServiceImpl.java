package com.jpmorgan.service;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.dao.StockDao;
import com.jpmorgan.model.Stock;
import com.jpmorgan.model.Trade;

/**
 * 
 * @author Adrian.Pascut
 *
 */
public class StockServiceImpl implements StockService {
	public final static Logger LOGGER = Logger.getLogger(StockServiceImpl.class);
	private StockDao stockDao; 
	
	public void setStockDao(StockDao tao){
		this.stockDao = tao;
	}
	
	@Override
	public Stock getStockBySymbol(String symbol) {
		return stockDao.getStockBySymbol(symbol);
	}

	@Override
	public Trade saveNewTrade(Trade trade) {
		return stockDao.saveNewTrade(trade);
	}

	@Override
	public void saveOldTrade(Trade trade) {
		stockDao.saveOldTrade(trade);		
	}

	@Override
	public List<Trade> getTradesInLastTimeInterval(long timeInterval) {
		return stockDao.getTradesInLastTimeInterval(timeInterval);
	}

	@Override
	public Collection<Stock> getAllStocks() {
		return stockDao.getAllStocks();
	}

}
