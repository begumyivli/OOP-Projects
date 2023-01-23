
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	int ID;
	String name;
	private int age;
	private Operator operator;
	private Bill bill;
	private double limitingAmount;
	private int minutesTalked = 0;
	private int messagesSent = 0;
	private double internetUsed = 0;

	
	/*
	 * I set a constructor when a new customer object is created
	 * also i create a bill object because every customer has his/her own bill
	 */
	public Customer(int ID, String name, int age, Operator operator, double limitingAmount) {
		super();
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.operator = operator;
		this.limitingAmount = limitingAmount;
		this.bill = new Bill(this.limitingAmount);
	}
	/*
	 * firstly, i have checked ID's because customers cannot call themselves.
	 * secondly, i have ensured that no calls are made in case the current debt and call cost exceeds the limit.
	 * i have increased caller and other person's minutes each time a call is made.
	 * i added the call cost to the bill of the caller.
	 * i added the minutes served to both customers' operators.
	 */
	public void talk(int minute, Customer other) {
		if(ID != other.ID) {
			if(bill.check(bill.getCurrentDebt()+operator.calculateTalkingCost(minute,this)) == true) {
				this.minutesTalked += minute;
				other.minutesTalked += minute;
				bill.add(operator.calculateTalkingCost(minute, this));
				this.operator.setMinuteServiced(minute+this.operator.getMinuteServiced());
				other.operator.setMinuteServiced(minute+other.operator.getMinuteServiced());
				}
			}
		}
	/* firstly, i have checked ID's because customers cannot text themselves.
	 * secondly, i have ensured that no message is sent in case the current debt and message cost exceeds the limit.
	 * i have increased sender's message number each time a message is sent.
	 * i added the message cost to the bill of the sender.
	 * i added the message served to sender's operator.
	 */
	public void message(int quantity, Customer other) {
		if(ID != other.ID) {
			if(bill.check(bill.getCurrentDebt()+operator.calculateMessageCost(quantity,this,other)) == true) {
				this.messagesSent += quantity;
				bill.add(operator.calculateMessageCost(quantity, this, other));
				this.operator.setMessageServiced(quantity+this.operator.getMessageServiced());
			}
		}
	}
	/*
	 * i have increased Internet usage amount each time a customer connects Internet.
	 * i added the network cost to the bill of the customer.
	 * i added the Internet serviced amount to customer's operator.
	 */
	public void connection(double amount) {
		if ((bill.check(bill.getCurrentDebt()+operator.calculateNetworkCost(amount)) == true)) {
			this.internetUsed += amount;
			bill.add(operator.calculateNetworkCost(amount));
			this.operator.setInternetServiced(amount+operator.getInternetServiced());
		}
	}
	
		
	
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @return the bill
	 */
	public Bill getBill() {
		return bill;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @param bill the bill to set
	 */
	public void setBill(Bill bill) {
		this.bill = bill;
	}

	/**
	 * @return the minutesTalked
	 */
	public int getMinutesTalked() {
		return minutesTalked;
	}

	/**
	 * @return the messagesSent
	 */
	public int getMessagesSent() {
		return messagesSent;
	}

	/**
	 * @return the internetUsed
	 */
	public double getInternetUsed() {
		return internetUsed;
	}

	/**
	 * @param minutesTalked the minutesTalked to set
	 */
	public void setMinutesTalked(int minutesTalked) {
		this.minutesTalked = minutesTalked;
	}

	/**
	 * @param messagesSent the messagesSent to set
	 */
	public void setMessagesSent(int messagesSent) {
		this.messagesSent = messagesSent;
	}

	/**
	 * @param internetUsed the internetUsed to set
	 */
	public void setInternetUsed(double internetUsed) {
		this.internetUsed = internetUsed;
	}
	
	
	
	


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

