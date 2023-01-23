package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Market {
	private PriorityQueue<SellingOrder> sellingOrders;
	private PriorityQueue<BuyingOrder> buyingOrders;
	private ArrayList <Transaction> transactions;
	private int fee;
	private int invalidQueries;
	public Market(int fee) {
		this.fee = fee;
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.transactions = new ArrayList <Transaction>();
	}
	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}
	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		while(!(buyingOrders.peek().getPrice() < price)){
			SellingOrder o = new SellingOrder(0, buyingOrders.peek().getAmount(), buyingOrders.peek().getPrice());
			giveSellOrder(o);
			checkTransactions(traders);
		}
		while(!(sellingOrders.peek().getPrice() > price)){
			BuyingOrder o = new BuyingOrder(0, sellingOrders.peek().getAmount(), sellingOrders.peek().getPrice());
			giveBuyOrder(o);
			checkTransactions(traders);
		}	
	}
	public void checkTransactions (ArrayList<Trader>traders) {
		if(buyingOrders.isEmpty() || sellingOrders.isEmpty()) {
			return;
		}
		else {
			if(buyingOrders.peek().getPrice() >= sellingOrders.peek().getPrice()) {
				double amountDifference = buyingOrders.peek().getAmount()-sellingOrders.peek().getAmount();
				double priceDifference = buyingOrders.peek().getPrice()-sellingOrders.peek().getPrice();
				double myPrice = sellingOrders.peek().getPrice();
				double myAmount = 0.0;
				if (amountDifference > 0) {
					myAmount = sellingOrders.peek().getAmount();
					BuyingOrder myBuyingOrder = new BuyingOrder(buyingOrders.peek().getTraderID(), amountDifference, buyingOrders.peek().getPrice());
					giveBuyOrder(myBuyingOrder);
				}
				else if (amountDifference < 0) {
					myAmount = buyingOrders.peek().getAmount();
					SellingOrder mySellingOrder = new SellingOrder(sellingOrders.peek().getTraderID(), Math.abs(amountDifference), sellingOrders.peek().getPrice());
					giveSellOrder(mySellingOrder);
				}
				else if(amountDifference == 0) {
					myAmount = sellingOrders.peek().getAmount();
				}
				if (priceDifference > 0) {
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars()-priceDifference*myAmount);
					traders.get(buyingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getDollars()+priceDifference*myAmount);
				}
				traders.get(sellingOrders.peek().getTraderID()).getWallet().setBlockedCoins(traders.get(sellingOrders.peek().getTraderID()).getWallet().getBlockedCoins()-myAmount);
				traders.get(sellingOrders.peek().getTraderID()).getWallet().setDollars(traders.get(sellingOrders.peek().getTraderID()).getWallet().getDollars()+myPrice*myAmount*(1.0-((double)fee/1000)));
				traders.get(buyingOrders.peek().getTraderID()).getWallet().setBlockedDollars(traders.get(buyingOrders.peek().getTraderID()).getWallet().getBlockedDollars()-myPrice*myAmount);
				traders.get(buyingOrders.peek().getTraderID()).getWallet().setCoins(traders.get(buyingOrders.peek().getTraderID()).getWallet().getCoins()+myAmount);
				SellingOrder newSellingOrder = new SellingOrder(sellingOrders.peek().traderID,myAmount,myPrice);
				BuyingOrder newBuyingOrder = new BuyingOrder(buyingOrders.peek().traderID,myAmount,myPrice);
				Transaction transaction = new Transaction(newSellingOrder, newBuyingOrder);
				transactions.add(transaction);
				buyingOrders.poll();
				sellingOrders.poll();
			}
		}
	}
	/**
	 * @return the sellingOrders
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}
	/**
	 * @return the buyingOrders
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	/**
	 * @param sellingOrders the sellingOrders to set
	 */
	public void setSellingOrders(PriorityQueue<SellingOrder> sellingOrders) {
		this.sellingOrders = sellingOrders;
	}
	/**
	 * @param buyingOrders the buyingOrders to set
	 */
	public void setBuyingOrders(PriorityQueue<BuyingOrder> buyingOrders) {
		this.buyingOrders = buyingOrders;
	}
	/**
	 * @return the invalidQueries
	 */
	public int getInvalidQueries() {
		return invalidQueries;
	}
	/**
	 * @param invalidQueries the invalidQueries to set
	 */
	public void setInvalidQueries(int invalidQueries) {
		this.invalidQueries = invalidQueries;
	}
	/**
	 * @return the transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}


}
