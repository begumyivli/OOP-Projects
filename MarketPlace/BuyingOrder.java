package elements;
/**
 * this class creates BuyingOrder object and compares it.
 * @author begum yivli
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder>{
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	/**
	 * This method sorts objects first by highest price, if equal,
	 * by highest amount, and if equal, by lowest trader id.
	 */
	@Override
	public int compareTo(BuyingOrder o) {
		if(this.price < o.price) {
			return 1;
		}
		else if(this.price > o.price) {
			return -1;
		}
		else {
			if (this.amount > o.amount) {
				return -1;
			}
			else if (this.amount < o.amount) {
				return 1;
			}
			else {
				if(this.traderID < o.traderID) {
					return -1;
				}
				else if(this.traderID > o.traderID) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}

}

