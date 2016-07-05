package com.jpmorgan.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.jpmorgan.model.Stock;
import com.jpmorgan.model.Trade;
/**
 * Class not implemented as is not in the purpose of this demo
 * @author Adrian.Pascut
 *
 */
public class StockDaoImpl implements StockDao {
	public final static Logger LOGGER = Logger.getLogger(StockDaoImpl.class);
	@Override
	public Stock getStockBySymbol(String symbol) {
		
		return null;
	}

	@Override
	public Trade saveNewTrade(Trade trade) {
		
		return null;
	}

	@Override
	public void saveOldTrade(Trade trade) {
		
		
	}

	@Override
	public List<Trade> getTradesInLastTimeInterval(long timeInterval) {
		
		return null;
	}

	@Override
	public Collection<Stock> getAllStocks() {
		
		return null;
	}

	@Override
	public Stock saveNewStock(Stock stock) {
		return null;
	}

	@Override
	public void saveOldStock(Stock stock) {
		
	}

}
