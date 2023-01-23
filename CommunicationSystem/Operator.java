
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	private int minuteServiced = 0;
	private int messageServiced = 0;
	private double internetServiced = 0.0;


	
	public Operator(int ID, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
		super();
		this.ID = ID;
		this.talkingCharge = talkingCharge;
		this.messageCost = messageCost;
		this.networkCharge = networkCharge;
		this.discountRate = discountRate;
	}
	/*
	 * i calculated talking cost according to the minute and the operator's talking charge.
	 * if the customer who talk is under age 18 or over age 65, i applied a discount.
	 */
	public double calculateTalkingCost(int minute, Customer customer) {
		if(customer.getAge() < 18) {
			return (double)((this.talkingCharge*minute)*((double)(100-discountRate)/100));
		}
		else if(customer.getAge() > 65) {
			return (double)((this.talkingCharge*minute)*((double)(100-discountRate)/100));
		}
		else {
			return this.talkingCharge*minute;
		}
	}
	/*
	 * i calculated message cost according to the quantity and the operator's message charge.
	 * if the customer who send the message and who take the message have same operator, i applied a discount.
	 */
	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		if(customer.getOperator()==other.getOperator()) {
			return ((this.messageCost*quantity)*((double)(100-discountRate)/100));
			
		}
		else {
			return this.messageCost*quantity;
		}
	}
	/*i calculated network cost according to the amount and the operator's network charge.
	 */
	public double calculateNetworkCost(double amount) {
		return this.networkCharge*amount;
	}
	
	
	
	/**
	 * @return the talkingCharge
	 */
	public double getTalkingCharge() {
		return talkingCharge;
	}

	/**
	 * @return the messageCost
	 */
	public double getMessageCost() {
		return messageCost;
	}

	/**
	 * @return the networkCharge
	 */
	public double getNetworkCharge() {
		return networkCharge;
	}

	/**
	 * @return the discountRate
	 */
	public int getDiscountRate() {
		return discountRate;
	}

	/**
	 * @return the minuteServiced
	 */
	public int getMinuteServiced() {
		return minuteServiced;
	}

	/**
	 * @return the messageServiced
	 */
	public int getMessageServiced() {
		return messageServiced;
	}

	/**
	 * @return the internetUsed
	 */
	public double getInternetServiced() {
		return internetServiced;
	}

	/**
	 * @param talkingCharge the talkingCharge to set
	 */
	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}

	/**
	 * @param messageCost the messageCost to set
	 */
	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}

	/**
	 * @param networkCharge the networkCharge to set
	 */
	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}

	/**
	 * @param discountRate the discountRate to set
	 */
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * @param minuteServiced the minuteServiced to set
	 */
	public void setMinuteServiced(int minuteServiced) {
		this.minuteServiced = minuteServiced;
	}

	/**
	 * @param messageServiced the messageServiced to set
	 */
	public void setMessageServiced(int messageServiced) {
		this.messageServiced = messageServiced;
	}

	/**
	 * @param internetServiced the internetServiced to set
	 */
	public void setInternetServiced(double internetServiced) {
		this.internetServiced = internetServiced;
	}


	
	
	


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

