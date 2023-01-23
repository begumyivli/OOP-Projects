package elements;

public abstract class Order {
	double amount;
	double price;
	int traderID;
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the traderID
	 */
	public int getTraderID() {
		return traderID;
	}

}

