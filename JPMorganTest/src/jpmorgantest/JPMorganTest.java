/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpmorgantest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Iqbal Jami
 */
public class JPMorganTest {
   
    private static String GBCEallShareIndex;
  
    public static void main(String[] args) throws InterruptedException {
                 
        HashMap<String, Stock> stock_data = new HashMap<String, Stock>();
        stock_data.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        stock_data.put("POP", new Stock("POP", StockType.COMMON, 8.0, 0.0, 100.0));
        stock_data.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
        stock_data.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.2, 100.0));
        stock_data.put("JOE", new Stock("JOE", StockType.COMMON, 13.0, 0.0, 250.0));
       
          
        for (Stock stock: stock_data.values()) {
           System.out.println( stock.getSymbol() + " dividend: " + stock.dividend(stock.parValue));
           System.out.println( stock.getSymbol() + " P/E Ratio: " + stock.PEratio(stock.parValue));
                
                for (int quantity=1; quantity <= 10; quantity++) {
            		Random r = new Random();
            		Date time = new Date();
                        Date trade_time = new Date(time.getTime());
            		double Selling_Price = 10*r.nextDouble();//rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            		stock.recordTrade(trade_time, quantity, Selling_Price);
            		System.out.println( stock.getSymbol() +" Trade Execute "+trade_time + " sold " + quantity + " shares at £" + Selling_Price);
            		Thread.sleep(500);
                        
            	}
                double Weighted_Stock_Price = stock.CalculateVolumeWeightedStockPrice();
            	System.out.println( stock.getSymbol() + " Volume_Weighted_Stock_Price: £" + Weighted_Stock_Price);
                
        }
        double shareIndex = Share_Index(stock_data);
        System.out.println("***Over All Index***");
        System.out.println( "GBCE All Share Index: " + shareIndex);
    }
	
    	public static Double Share_Index(Map<String, Stock> stocks) {
		Double All_ShareIndex = 0.0;
		for(Stock stock: stocks.values()) {
			All_ShareIndex+=stock.getPrice();
		}
		return Math.pow(All_ShareIndex, 1.0 / stocks.size());
	}
   
    }