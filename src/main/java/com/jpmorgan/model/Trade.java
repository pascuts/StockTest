package com.jpmorgan.model;

import java.math.BigDecimal;

/**
 * 
 * @author Adrian.Pascut
 *
 */
public class Trade {
	Long tradeId;
	Long timestamp;
	int numberOfShares;
	BigDecimal tradePrice;
	TradeIndicator indicator;

	public Trade(){}
	
	public Trade(Long id, Long timestamp, BigDecimal price, TradeIndicator indicator, int noShares){
		setTradeId(id);
		setTimestamp(timestamp);
		setTradePrice(price);
		setIndicator(indicator);
		setNumberOfShares(noShares);
	}
	
	public TradeIndicator getIndicator() {
		return indicator;
	}

	public void setIndicator(TradeIndicator indicator) {
		this.indicator = indicator;
	}
	
	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getNumberOfShares() {
		return numberOfShares;
	}
	
	public void setNumberOfShares(int numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
	
	public BigDecimal getTradePrice() {
		return tradePrice;
	}
	
	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}
}
