package jpmorgantest;

/**
 *
 * @author Iqbal Jami
 */

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

public class Stock {
	
	String symbol;
        Date tradeTime;
	StockType type;
	Double lastDividend;
	Double fixedDividend;
	Double parValue;
        TreeMap<Date, Trade> all_trades;
        
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
public Double getLastDividend() {
		return lastDividend;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}
public Double getFixedDividend() {
		return fixedDividend;
	}
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getParValue() {
		return parValue;
	}


	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	public TreeMap<Date, Trade> getTrades() {
		return all_trades;
	}

	public Stock(String symbol, StockType type,Double lastDividend, Double fixedDividend, Double parValue) {
		this.setSymbol(symbol);
		this.setType(type);
		this.setLastDividend(lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.all_trades = new TreeMap<Date, Trade>();
	}

	public void recordTrade(Date tradeTime,Integer quantity, Double price) {
		Trade trade = new Trade(tradeTime,TradeType.SELL, quantity, price);
                
		this.all_trades.put(new Date(), trade);
                 
	}

	/*
	 * Calculate the dividend based on the given price depending on the stock type
	 */
	public Double dividend(Double price) {
	if(this.getType()==this.type.COMMON) {
			
	return this.getLastDividend()/price;}
                
	else
        return this.getFixedDividend()*this.getParValue()/price;	
	}
	
	
	// Calculate P/E Ratio 
	public Double PEratio(Double price) {
            if (this.getLastDividend()>0.0){
		return price/this.getLastDividend();}
            else
                return 0.0;
                        
	}

	public Double getPrice() {
		if (this.all_trades.size() > 0) {
			// Uses the First Trade Entry
			return this.all_trades.firstEntry().getValue().getPrice();
		} else {
			return 0.0;}
		}
	
	
	/*
	 * Calculate the Volume Weighted Stock Price 
	 */ 
	
        public Double CalculateVolumeWeightedStockPrice() {
		Date now = new Date();
		// Get Trades Time for Last 15 minutes ago
		Date Start = new Date(now.getTime() - (15*60*1000));
		SortedMap<Date, Trade> trades = this.all_trades.tailMap(Start);
		
		Double volumeWeigthedStockPrice = 0.0;
		Integer totalQuantity = 0;
             
		for (Trade trade: trades.values()) {
                     
			totalQuantity += trade.getQuantity();
			volumeWeigthedStockPrice += trade.getPrice() * trade.getQuantity();
		}
		return volumeWeigthedStockPrice/totalQuantity;
                
	}
	
}
