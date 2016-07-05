package com.jpmorgan.businessutils;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jpmorgan.dao.mock.StockDaoMock;
import com.jpmorgan.model.Stock;
import com.jpmorgan.model.Trade;
import com.jpmorgan.service.StockService;
import com.jpmorgan.service.StockServiceImpl;
import com.jpmorgan.utils.CreateTestData;

public class StockFormulasTest {
	public final static Logger LOGGER = Logger.getLogger(StockFormulasTest.class);
	StockService stockService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		CreateTestData.addTestStocks();
		CreateTestData.createTrades();
	}

	@Before
	public void setUp() throws Exception {
		stockService = new StockServiceImpl();
		((StockServiceImpl)stockService).setStockDao(StockDaoMock.DAO);
	}

	@Test
	public final void testGetDividentYield() {
		Collection<Stock> stocks = stockService.getAllStocks();
		for (Iterator iterator = stocks.iterator(); iterator.hasNext();) {
			Stock stock = (Stock) iterator.next();			
			BigDecimal divY = StockFormulas.BUSINESS.getDividentYield(stock);
			LOGGER.info(MessageFormat.format(" Stock with symbol {0} has Div. Yield {1}", stock.getSymbol(), divY));
		}
		
	}

	@Test
	public final void testGetPERatio() {
		List<Trade> trades = stockService.getTradesInLastTimeInterval(15*60*1000);
		BigDecimal eps = StockFormulas.BUSINESS.getEarningsPerShare(new BigDecimal(10000), stockService.getAllStocks());
		BigDecimal stockPrice = StockFormulas.BUSINESS.getStockPrice(trades);
		BigDecimal peRatio = StockFormulas.BUSINESS.getPERatio(eps, stockPrice);
		LOGGER.info(MessageFormat.format("The P/E Ratio is {0}", peRatio));
	}

	@Test
	public final void testGetEarningsPerShare() {
		BigDecimal eps = StockFormulas.BUSINESS.getEarningsPerShare(new BigDecimal(10000), stockService.getAllStocks());
		LOGGER.info(MessageFormat.format("The EPS is {0}", eps));
	}

	@Test
	public final void testGetStockPrice() {
		List<Trade> trades = stockService.getTradesInLastTimeInterval(15*60*1000);
		BigDecimal stockPrice = StockFormulas.BUSINESS.getStockPrice(trades);
		LOGGER.info(MessageFormat.format("The Stock Price is {0}", stockPrice));
	}

	@Test
	public final void testGetGBCEAllShareIndex() {
		Collection<Stock> stocks = stockService.getAllStocks();
		BigDecimal gbce = StockFormulas.BUSINESS.getGBCEAllShareIndex(stocks);
		LOGGER.info(MessageFormat.format("The GBCE All Shares is {0}", gbce));
	}
}
