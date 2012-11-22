package ru.kpfu.itis.oop.example1;

public abstract class AbstractTicket implements Ticket{
	protected Double price;
	protected String number;
	protected String seat;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}	
}
