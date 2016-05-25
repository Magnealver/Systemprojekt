package Server;

import java.util.*;

public class Safaribooking {

	private String lake;
	private int date;
	private int customerID;
	private int bookingID;

	public Safaribooking() {

	}

	public Safaribooking(String lake, int date, int customerID, int bookingID) {
		this.lake = lake;
		this.date = date;
		this.customerID = customerID;
		this.bookingID = bookingID;
	}

	public String getLake() {
		return lake;
	}

	public void setLake(String lake) {
		this.lake = lake;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getBookingID() {
		return bookingID;
	}

	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}

}
