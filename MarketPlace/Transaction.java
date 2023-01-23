package elements;

public class Transaction {
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}
}
