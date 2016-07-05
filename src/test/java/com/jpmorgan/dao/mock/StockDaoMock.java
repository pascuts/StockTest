package com.jpmorgan.dao.mock;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.jpmorgan.dao.StockDao;
import com.jpmorgan.exception.StockException;
import com.jpmorgan.model.Stock;
import com.jpmorgan.model.Trade;
/**
 * Mock class to hold in memory the test objects
 * @author Adrian.Pascut
 *
 */
public class StockDaoMock implements StockDao {
	private Map<String, Stock> stocks = Collections.synchronizedMap(new HashMap<String, Stock>());
	private Map<Long, Trade> trades = Collections.synchronizedMap(new HashMap<Long, Trade>());

	public static final StockDao DAO = new StockDaoMock();

	private StockDaoMock() {

	}

	@Override
	public Stock getStockBySymbol(String symbol) {
		return stocks.get(symbol);
	}

	@Override
	public Trade saveNewTrade(Trade trade) {
		if (trade==null||trade.getTradeId()==null) {
			throw new StockException("Trade or its ID is Null!");
		}
		if (trades.containsKey(trade.getTradeId())) {
			throw new StockException(MessageFormat.format("Trade with ID {0} alredy exists!", trade.getTradeId()));
		}
		trades.put(trade.getTradeId(), trade);
		return trades.get(trade.getTradeId());
	}

	@Override
	public void saveOldTrade(Trade trade) {
		if (trade==null||trade.getTradeId()==null) {
			throw new StockException("Trade or its ID is Null!");
		}
		if (trades.containsKey(trade.getTradeId())) {
			trades.put(trade.getTradeId(), trade);
		}
	}

	@Override
	public List<Trade> getTradesInLastTimeInterval(long timeInterval) {
		List<Trade> list = new LinkedList<>(); 
		Collection<Trade> collection = trades.values();
		long currentTimeStamp = System.currentTimeMillis();
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Trade trade = (Trade) iterator.next();
			if(trade.getTimestamp() < currentTimeStamp && trade.getTimestamp() >= (currentTimeStamp - timeInterval)){
				list.add(trade);
			}
		}
		return list;
	}

	@Override
	public Collection<Stock> getAllStocks() {
		return stocks.values();
	}

	@Override
	public Stock saveNewStock(Stock stock) {
		if (stock==null||stock.getSymbol()==null) {
			throw new StockException("Stock or symbol are Null!");
		}
		if (stocks.containsKey(stock.getSymbol())) {
			throw new StockException(MessageFormat.format("Stock with symbol {0} alredy exists!", stock.getSymbol()));
		}
		stocks.put(stock.getSymbol(), stock);
		return stocks.get(stock.getSymbol());
	}

	@Override
	public void saveOldStock(Stock stock) {
		if (stock==null||stock.getSymbol()==null) {
			throw new StockException("Stock or symbol is Null!");
		}

		if (stocks.containsKey(stock.getSymbol())) {
			stocks.put(stock.getSymbol(), stock);
		}
	}

}
