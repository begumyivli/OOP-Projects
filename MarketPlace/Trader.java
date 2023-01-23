package elements;

public class Trader {
	private int id;
	private Wallet wallet;
	public static int numberOfUsers = 0;
	public Trader(double dollars, double coins) {
		this.wallet = new Wallet(dollars, coins);
		this.id = numberOfUsers;
		numberOfUsers++;
	}
	
	public int sell(double amount, double price, Market market) {
		if(this.wallet.getCoins() >= amount) {
			SellingOrder mySellingOrder = new SellingOrder(this.id, amount, price);	
			this.wallet.setBlockedCoins(this.wallet.getBlockedCoins()+amount);
			this.wallet.setCoins(this.wallet.getCoins()-amount);
			market.giveSellOrder(mySellingOrder);
			return 1;
		}
		else {
			market.setInvalidQueries(market.getInvalidQueries()+1);
			return 0;
		}
	}
	public int buy(double amount, double price, Market market) {
		if(this.wallet.getDollars() >= amount*price) {
			BuyingOrder myBuyingOrder = new BuyingOrder(this.id, amount, price);
			this.wallet.setBlockedDollars(this.wallet.getBlockedDollars()+price*amount);
			this.wallet.setDollars(this.getWallet().getDollars()-price*amount);
			market.giveBuyOrder(myBuyingOrder);
			return 1;
		}
		else {
			market.setInvalidQueries(market.getInvalidQueries()+1);
			return 0;
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}
	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}


	
	
}
