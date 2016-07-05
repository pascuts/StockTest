package com.jpmorgan.model;

import java.math.BigDecimal;

/**
 * 
 * @author Rich&Moral
 *
 */
public class Stock {
	private String symbol;
	private StockType stockType;
	private BigDecimal lastDividend;
	private BigDecimal fixedDividendParValue;
	private BigDecimal stockParValue;
	private BigDecimal marketValue;
	private int numberOfShares;

	public Stock(){};
	
	public Stock(String symbol, StockType type, BigDecimal lastDividend,	BigDecimal fixedDividend, BigDecimal marketPrice, BigDecimal parValue, int noShares){
		setSymbol(symbol);
		setStockType(type);
		setLastDividend(lastDividend);
		setFixedDividendParValue(fixedDividend);
		setMarketValue(marketPrice);
		setStockParValue(parValue);
		setNumberOfShares(noShares);
	};
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public StockType getStockType() {
		return stockType;
	}
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}
	public BigDecimal getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(BigDecimal lastDividend) {
		this.lastDividend = lastDividend;
	}
	public BigDecimal getFixedDividendParValue() {
		return fixedDividendParValue;
	}
	public void setFixedDividendParValue(BigDecimal fixedDividendParValue) {
		this.fixedDividendParValue = fixedDividendParValue;
	}
	public BigDecimal getStockParValue() {
		return stockParValue;
	}
	public void setStockParValue(BigDecimal stockParValue) {
		this.stockParValue = stockParValue;
	}
	public BigDecimal getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}
	public int getNumberOfShares() {
		return numberOfShares;
	}
	public void setNumberOfShares(int numberOfShares) {
		this.numberOfShares = numberOfShares;
	}
}
