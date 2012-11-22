package ru.kpfu.itis.oop.example1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BookingOffice {
	public static void buyTicket(Ticket ticket){
		Scanner scanner = new Scanner(System.in);
		System.out.printf("Ticket price: %.2f %n", ticket.getPrice());
		System.out.println("Please, enter your card number:");
		scanner.nextInt();
		System.out.println("Thank you.");
		ticket.print();
	}
	
	public static void returnTicket(Ticket ticket) {
		System.out.println("The ticket is canceled");
		ticket.print();
	}

	public static void main(String[] args) throws ParseException{
		Ticket ticket = new AviaTicket(3550.20, "AC 231", "A12", "26B", false, "Kazan", "Moscow", "Ivanov",
				"YYC27670", new Byte[100]);
		
		buyTicket(ticket);
		returnTicket(ticket);
		
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy hh:mm");
		ticket = new MovieTicket(4, "Avatar 3D", 8, "15", format.parse("12.01.2010 19:00"), format.parse("08.01.2010 14:33"),
				260.00, "121020109", "Zaharchenko");	
		
		buyTicket(ticket);
		returnTicket(ticket);	
	}
}
