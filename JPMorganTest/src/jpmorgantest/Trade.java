package jpmorgantest;

import java.util.Date;


public class Trade {
	private Date tradeTime;
	private TradeType type;
	private Integer quantity;
	private Double price;

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}
  public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Trade(Date tradeTime, TradeType type, Integer quantity, Double price) {
                this.setTime(tradeTime);
		this.setType(type);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

    public void setTime(Date tradeTime) {
        this.tradeTime=tradeTime;
    }
}
