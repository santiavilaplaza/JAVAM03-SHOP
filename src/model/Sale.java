package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;

public class Sale {
	private Client client;
//	Product[] products;
	private ArrayList<Product> products;
	double amount;
	String dateTime;
	
	
	public Sale(Client client, ArrayList<Product> products, double amount, String dateTime) {
		super();
		this.client = client;
		this.products = products;
		this.amount = amount;
		this.dateTime = dateTime;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public void setAmount(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Sale \nClient: " + client + ", \nProducts: " + products.toString() + ", \nAmount: " + amount + " \nDate: " + dateTime;
	}
}
