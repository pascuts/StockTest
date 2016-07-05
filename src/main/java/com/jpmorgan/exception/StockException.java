package com.jpmorgan.exception;
/**
 * Custom exception
 * @author Adrian.Pascut
 *
 */
public class StockException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 153041599513876925L;

	public StockException(String message, Throwable throwable){
		super(message, throwable);
	}
	
	public StockException(String message){
		super(message);
	}
}
