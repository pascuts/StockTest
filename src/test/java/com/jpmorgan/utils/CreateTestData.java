package com.jpmorgan.utils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import com.jpmorgan.dao.mock.StockDaoMock;
import com.jpmorgan.exception.StockException;
import com.jpmorgan.model.Stock;
import com.jpmorgan.model.StockType;
import com.jpmorgan.model.Trade;
import com.jpmorgan.model.TradeIndicator;

public class CreateTestData {
	public final static Logger LOGGER = Logger.getLogger(CreateTestData.class);
	private static final AtomicLong TRADE_SEQUENCE = new AtomicLong();
	private static final Random random = new Random();

	public static void addTestStocks() {
		Stock stock = new Stock("TEA", StockType.COMMON, new BigDecimal(0), new BigDecimal(0), new BigDecimal(100),
				new BigDecimal(100), 108);
		StockDaoMock.DAO.saveNewStock(stock);
		stock = new Stock("POP", StockType.COMMON, new BigDecimal(8), new BigDecimal(0), new BigDecimal(100),
				new BigDecimal(100), 108);
		StockDaoMock.DAO.saveNewStock(stock);
		stock = new Stock("ALE", StockType.COMMON, new BigDecimal(23), new BigDecimal(0), new BigDecimal(60),
				new BigDecimal(60), 57);
		StockDaoMock.DAO.saveNewStock(stock);
		stock = new Stock("GIN", StockType.PREFERRED, new BigDecimal(8), new BigDecimal(2), new BigDecimal(100),
				new BigDecimal(100), 1000);
		StockDaoMock.DAO.saveNewStock(stock);
		stock = new Stock("JOE", StockType.COMMON, new BigDecimal(13), new BigDecimal(0), new BigDecimal(250),
				new BigDecimal(250), 180);
		StockDaoMock.DAO.saveNewStock(stock);
	}

	public static void createTrades() {
		for (int i = 0; i < 1000; i++) {
			long timestamp = System.currentTimeMillis() + (random.nextInt(900000) - 450000);
			int tradeInd = random.nextInt(2) - 1;
			int noShares = random.nextInt(5) + 1;
			Trade trade = new Trade(generateTradeId(), timestamp, new BigDecimal(nextDouble(i + 1000)),
					tradeInd > 0 ? TradeIndicator.BUY : TradeIndicator.SELL, noShares);
			try{
				StockDaoMock.DAO.saveNewTrade(trade);
			} catch(StockException exception){
				//Safely ignore 
			}
		}
		
	}

	public static long generateTradeId() {
		return TRADE_SEQUENCE.incrementAndGet();
	}

	public static double nextDouble(double bound) {
		double r = random.nextDouble();
		r = r * (bound - 1) + 1;
		if (r >= bound) // correct for rounding
			r = Math.nextDown(bound);
		return r;
	}
}
