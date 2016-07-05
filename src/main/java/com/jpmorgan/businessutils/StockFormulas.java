package com.jpmorgan.businessutils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.script.Bindings;

import org.apache.log4j.Logger;

import com.jpmorgan.model.Stock;
import com.jpmorgan.model.StockType;
import com.jpmorgan.model.Trade;



/**
 * 
 * @author Adrian.Pascut
 *
 */
public class StockFormulas {
	
	public static final StockFormulas BUSINESS = new StockFormulas();
	
	private StockFormulas(){};
	
	public final static Logger LOGGER = Logger.getLogger(StockFormulas.class);
	
	/**
	 * Compute the Divident Yield for Common or Preferred
	 * @param stock
	 * @return The Bigdecimal divident yield.
	 */
	public BigDecimal getDividentYield(Stock stock){
		if(stock==null){
			return null;
		}
		switch(stock.getStockType()){
		case COMMON:
			return getDividentYield(stock.getLastDividend(), stock.getMarketValue());
		case PREFERRED:
			return getDividentYield(stock.getFixedDividendParValue(), stock.getStockParValue());
		default: 
			return null;	
		}
	}
	/**
	 * Compute the Divident Yield
	 * @param dividentValue
	 * @param tickerPrice
	 * @return The Bigdecimal divident yield.
	 */
	private BigDecimal getDividentYield(BigDecimal dividentValue, BigDecimal tickerPrice){
		if(tickerPrice==null){
			return null;
		}
		BigDecimal result = new BigDecimal(0);
		result = result.add(dividentValue).divide(tickerPrice, 10, RoundingMode.CEILING);
		return result;
	}
	/**
	 * Compute the P/E Ratio = Stock Price / Earnings Per Share
	 * @param dividentValue
	 * @param tickerPrice
	 * @return BigDecimal value of the P/E Ratio
	 */
	public BigDecimal getPERatio(BigDecimal valueEPS, BigDecimal stockPrice){
		if(valueEPS==null || valueEPS.doubleValue()==0 || stockPrice == null){
			return null;
		}
		BigDecimal result = stockPrice.divide(valueEPS, 10, RoundingMode.CEILING);
		return result;
	}
	/**
	 * Get EPS based on formula (Net Income - SUM(dividents preferred)) / SUM(number of common shares)
	 * @param netIncome
	 * @param stocks
	 * @return earnings Per Share
	 */
	public BigDecimal getEarningsPerShare(BigDecimal netIncome, Collection<Stock> stocks){
		BigDecimal result = new BigDecimal(0);
		result.add(netIncome);
		long noCommonShares = 0;
		for (Iterator iterator = stocks.iterator(); iterator.hasNext();) {
			Stock stock = (Stock) iterator.next();
			switch(stock.getStockType()){
			case COMMON:
				noCommonShares += stock.getNumberOfShares();
				break;
			case PREFERRED:
				result = result.add(stock.getFixedDividendParValue().negate());
				break;
			}
		}
		if(noCommonShares==0){
			return null;
		}
		return result.divide(new BigDecimal(noCommonShares), 10, RoundingMode.CEILING);
	}
	/**
	 * The formula is SUM(trade price * No of shares)/Total no of shares
	 * @param trades
	 * @return Stock Price
	 */
	public BigDecimal getStockPrice(List<Trade> trades){
		if(trades==null || trades.size()<1){
			return null;
		}
		
		BigDecimal result = new BigDecimal(0);
		long noTotalShares = 0;
		for (Iterator<Trade> iterator = trades.iterator(); iterator.hasNext();) {
			Trade trade = iterator.next();
			result = result.add(trade.getTradePrice().multiply(new BigDecimal(trade.getNumberOfShares())));
			noTotalShares += trade.getNumberOfShares();
		}
		if(noTotalShares==0){
			return null;
		}
		
		return result.divide(new BigDecimal(noTotalShares), 10, RoundingMode.CEILING);
	}
	/**
	 * Compute the GBCE All Share Index.
	 * Note: I could find a common meaning for GBCE and I believe is an internal definition.
	 * @return
	 */
	public BigDecimal getGBCEAllShareIndex(Collection<Stock> stocks){
		BigDecimal result = new BigDecimal(1);
		double counter = 0;
		for (Iterator iterator = stocks.iterator(); iterator.hasNext();) {
			counter++;
			Stock stock = (Stock) iterator.next();
			switch(stock.getStockType()){
			case PREFERRED:
				result = result.multiply(stock.getStockParValue());
				break;
			case COMMON:
				result = result.multiply(stock.getMarketValue());
				break;
			}
		}
		if(result.signum()<0){
			return null;
		}
		double dResult =  result.doubleValue();
		LOGGER.info(MessageFormat.format("The GBCE is radical of ordin {0} from {1}", counter, dResult));
		return new BigDecimal(Math.pow(dResult, 1d/counter));
	}
}
