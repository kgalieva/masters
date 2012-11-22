package ru.kpfu.itis.oop.example1;

public class AviaTicket extends AbstractTicket {
	
	private String flightNumber;
	private String gate;	
	private Boolean smokingAllowed;
	private String from;
	private String to;
	private String passengerName;	
	private Byte[] barcode;	
	
	public AviaTicket() {
		super();
	}
	
	public AviaTicket(Double price, String flightNumber, String gate,
			String seat, Boolean smokingAllowed, String from, String to,
			String passengerName, String number, Byte[] barcode) {
		this.price = price;
		this.flightNumber = flightNumber;
		this.gate = gate;
		this.seat = seat;
		this.smokingAllowed = smokingAllowed;
		this.from = from;
		this.to = to;
		this.passengerName = passengerName;
		this.number = number;
		this.barcode = barcode;
	}	
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getGate() {
		return gate;
	}
	public void setGate(String gate) {
		this.gate = gate;
	}	
	public Boolean getSmokingAllowed() {
		return smokingAllowed;
	}
	public void setSmokingAllowed(Boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public Byte[] getBarcode() {
		return barcode;
	}
	public void setBarcode(Byte[] barcode) {
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "AviaTicket [price=" + price + ", flightNumber=" + flightNumber
				+ ", gate=" + gate + ", seat=" + seat + ", smokingAllowed="
				+ smokingAllowed + ", from=" + from + ", to=" + to
				+ ", passengerName=" + passengerName + ", number=" + number
				+ "]";
	}
	
	public void print(){
		System.out.println(toString());
	}
	
	
}