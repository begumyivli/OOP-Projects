package elements;
/**
 * this class creates SellingOrder object and compares it.
 * @author begum yivli
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{

	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	/**
	 * This method sorts objects first by lowest price, if equal,
	 * by highest amount, and if equal, by lowest trader id.
	 */
	@Override
	public int compareTo(SellingOrder o) {
		if (this.price > o.price) {
			return 1;
		}
		else if (this.price < o.price) {
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
				if (this.traderID < o.traderID) {
					return -1;
				}
				else if (this.traderID > o.traderID) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}

}
