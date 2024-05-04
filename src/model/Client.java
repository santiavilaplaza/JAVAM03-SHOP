package model;

import main.Payable;

public class Client extends Person implements Payable{
	private int memberId;
	private double balance;
	
	private static final int MEMBER_ID = 456;
	private static final double INITIAL_BALANCE = 50.00;
	
	public Client(String name, int memberId, double balance) {
		super(name);
		this.memberId = MEMBER_ID;
		this.balance = INITIAL_BALANCE;
	}

	public void setMemberId(int memberId){
		this.memberId = memberId;
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public boolean pay (double amount) {
		balance -= amount;
		
		if (balance	 >= 0) {
			return true;
		} else {
			return false;
		}
	}
}
