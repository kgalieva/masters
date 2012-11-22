package ru.kpfu.itis.oop.example1;

import java.util.Date;

public class MovieTicket extends AbstractTicket{
	
	private Integer cinemaHall;
	private String movieName;
	private Integer row;
	private Date movieDate;
	private Date dateOfSale;
	private String salesManager;
	
	public MovieTicket(){
	}
	
	public MovieTicket(Integer cinemaHall, String movieName, Integer row,
			String seat, Date movieDate, Date dateOfSale, Double price,
			String number, String salesManager) {
		this.cinemaHall = cinemaHall;
		this.movieName = movieName;
		this.row = row;
		this.seat = seat;
		this.movieDate = movieDate;
		this.dateOfSale = dateOfSale;
		this.price = price;
		this.number = number;
		this.salesManager = salesManager;
	}
	public Integer getCinemaHall() {
		return cinemaHall;
	}
	public void setCinemaHall(Integer cinemaHall) {
		this.cinemaHall = cinemaHall;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Date getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(Date movieDate) {
		this.movieDate = movieDate;
	}
	public Date getDateOfSale() {
		return dateOfSale;
	}
	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSalesManager() {
		return salesManager;
	}
	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "MovieTicket [cinemaHall=" + cinemaHall + ", movieName="
				+ movieName + ", row=" + row + ", seat=" + seat
				+ ", movieDate=" + movieDate + ", dateOfSale=" + dateOfSale
				+ ", price=" + price + ", number=" + number + ", salesManager="
				+ salesManager + "]";
	}
	
	public void print(){
		System.out.println(toString());
	}
}