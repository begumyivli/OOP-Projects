
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private double limitingAmount;
	private double currentDebt = 0;
	private double customerPaid = 0.0;
	
	public Bill(double limitingAmount) {
		super();
		this.limitingAmount = limitingAmount;
	}
	/*
	 * i checked whether the desired transaction and current debt exceeded the limit.
	 */
	public boolean check(double amount) {
		if (amount <= this.limitingAmount) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * i added the transaction fee to the current debt amount after each transaction.
	 */
	public void add(double amount) {
		this.currentDebt += amount;
	}
	/*
	 * when the customer pay, i deleted that amount from the current debt and added it to the paid money.
	 * if the amount which costumer wants to pay is more than the current debt, I added current debt value to the paid money and reset the current debt.
	 * there will be no negative debt.
	 */
	public void pay(double amount) {
		if(amount < this.currentDebt) {
			this.customerPaid += amount;
			this.currentDebt -= amount;
		}
		else {
			this.customerPaid += this.currentDebt;
			this.currentDebt = 0;
		}
	}
	/*
	 * if the limit amount to be changed is greater than or equal to the current debt, I allowed the limit to be changed.
	 */
	public void changeTheLimit(double amount) {
		if (amount >= this.currentDebt) {
			this.limitingAmount = amount;
		}
	}

	
	
	/**
	 * @return the limitingAmount
	 */
	public double getLimitingAmount() {
		return limitingAmount;
	}

	/**
	 * @return the currentDebt
	 */
	public double getCurrentDebt() {
		return currentDebt;
	}

	/**
	 * @return the customerPaid
	 */
	public double getCustomerPaid() {
		return customerPaid;
	}

	/**
	 * @param customerPaid the customerPaid to set
	 */
	public void setCustomerPaid(double customerPaid) {
		this.customerPaid = customerPaid;
	}

	
	

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

