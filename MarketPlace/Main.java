package executable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import elements.BuyingOrder;
import elements.Market;
import elements.SellingOrder;
import elements.Trader;
/**
 * this class where i read file, take inputs, create objects, call methods, and write output file.
 * @author begum yivli
 *
 */
public class Main {
	public static Random myRandom;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		int seed = in.nextInt();
		myRandom = new Random(seed);
		
		int marketFee = in.nextInt();
		int numberOfUsers = in.nextInt();
		int numberOfQueries = in.nextInt();

		ArrayList<Trader> traders = new ArrayList<Trader>();
		Market myMarket = new Market(marketFee);
		/**
		 * i create trader object here, and add it to the traders arraylist
		 */
		for (int i = 0; i < numberOfUsers; i++) {
			double dollarAmount = in.nextDouble();
			double coinAmount = in.nextDouble();
			Trader myTrader = new Trader(dollarAmount, coinAmount);
			traders.add(myTrader);
		}
		for (int j = 0; j < numberOfQueries; j++) {
			int query = in.nextInt();
			/**
			 * i call buy method in trader class here with the given parameters and i check whether there is a transaction
			 */
			if (query == 10) {
				int traderId = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				traders.get(traderId).buy(amount, price, myMarket);
				myMarket.checkTransactions(traders);
			}
			/**
			 * if sellingOrders PQ is empty, there is no market price for buying so it will an invalid query
			 * if it's not empty, i call buy method in trader class here with the given parameters and i check whether there is a transaction
			 */
			else if (query == 11) {
				int traderId = in.nextInt();
				double amount = in.nextDouble();
				if (!myMarket.getSellingOrders().isEmpty()) {
					traders.get(traderId).buy(amount, myMarket.getSellingOrders().peek().getPrice(), myMarket);
				}
				else {
					myMarket.setInvalidQueries(myMarket.getInvalidQueries()+1);
				}
				myMarket.checkTransactions(traders);
			}
			/**
			 * i call sell method in trader class here with the given parameters and i check whether there is a transaction
			 */
			else if (query == 20) {
				int traderId = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				traders.get(traderId).sell(amount, price, myMarket);
				myMarket.checkTransactions(traders);
			}
			/**
			 * if buyingOrders PQ is empty, there is no market price for selling so it will an invalid query
			 * if it's not empty, i call sell method in trader class here with the given parameters and i check whether there is a transaction
			 */
			else if (query == 21) {
				int traderId = in.nextInt();
				double amount = in.nextDouble();
				if (!myMarket.getBuyingOrders().isEmpty()) {
					traders.get(traderId).sell(amount, myMarket.getBuyingOrders().peek().getPrice(), myMarket);
				}
				else {
					myMarket.setInvalidQueries(myMarket.getInvalidQueries()+1);
				}
				myMarket.checkTransactions(traders);
			}
			/**
			 * i add given amount dollar to the trader's wallet
			 */
			else if (query == 3) {
				int traderId = in.nextInt();
				double amount = in.nextDouble();
				traders.get(traderId).getWallet().setDollars(traders.get(traderId).getWallet().getDollars()+amount);
			}
			/**
			 * if trader hasn't got the given amount, then it will be an invalid query, but if it has i will withdraw it from his/her wallet
			 */
			else if (query == 4) {
				int traderId = in.nextInt();
				double amount = in.nextDouble();
				if (traders.get(traderId).getWallet().getDollars() >= amount) {
					traders.get(traderId).getWallet().setDollars(traders.get(traderId).getWallet().getDollars()-amount);
				}
				else {
					myMarket.setInvalidQueries(myMarket.getInvalidQueries()+1);
				}
			}
			/**
			 * i print the given trader's wallet status
			 */
			else if (query == 5) {
				int traderId = in.nextInt();
				out.println("Trader "+traderId+": "+String.format("%.5f",traders.get(traderId).getWallet().getDollars()+traders.get(traderId).getWallet().getBlockedDollars())+"$ "
				+String.format("%.5f",traders.get(traderId).getWallet().getCoins()+traders.get(traderId).getWallet().getBlockedCoins())+"PQ");
			}
			/**
			 * i distributes random amounts of coins to all traders
			 */
			else if (query == 777) {
				for (Trader t:traders) {
					t.getWallet().setCoins(t.getWallet().getCoins()+myRandom.nextDouble()*10);
				}
			}
			/**
			 * the system compensates buying or selling orders to set the price of coin to the given price
			 */
			else if (query == 666) {
				double price = in.nextDouble();
				myMarket.makeOpenMarketOperation(price, traders);
			}
			/**
			 * i print the current market size
			 */
			else if (query == 500) {
				 Iterator<SellingOrder> itr = myMarket.getSellingOrders().iterator();
				 Iterator<BuyingOrder> itr2 = myMarket.getBuyingOrders().iterator();
				 double sellitr = 0.0;
				 double buyitr = 0.0;
				 BuyingOrder myitr;
				 while (itr.hasNext()){
			            sellitr += itr.next().getAmount();
			        }
				 while (itr2.hasNext()){
					 	myitr = itr2.next();
			            buyitr += myitr.getAmount()*myitr.getPrice();
			        }
				out.println("Current market size: "+ String.format("%.5f",buyitr) +" "+ String.format("%.5f",sellitr));
			}
			/**
			 * i print the number of successful transactions
			 */
			else if (query == 501) {
				out.println("Number of successful transactions: "+myMarket.getTransactions().size());
			}
			/**
			 * i print the number of invalid queries
			 */
			else if (query == 502) {
				out.println("Number of invalid queries: "+myMarket.getInvalidQueries());
			}
			/**
			 * i print the current prices
			 */
			else if (query == 505) {
				double cpBuying;
				double cpSelling;
				double cpAvg = 0;
				if(myMarket.getBuyingOrders().isEmpty()) {
					cpBuying = 0;
				}
				else {
					cpBuying = myMarket.getBuyingOrders().peek().getPrice();
				}
				if(myMarket.getSellingOrders().isEmpty()) {
					cpSelling = 0;
				}
				else {
					cpSelling = myMarket.getSellingOrders().peek().getPrice();
				}
				if(cpBuying != 0 && cpSelling == 0) {
					cpAvg = cpBuying;
				}
				if (cpBuying == 0 && cpSelling != 0) {
					cpAvg = cpSelling;
				}
				if (cpBuying != 0 && cpSelling != 0) {
					cpAvg = (cpSelling+cpBuying)/2;
				}
				out.println("Current prices: "+String.format("%.5f",cpBuying)+" "+String.format("%.5f",cpSelling)+" "+String.format("%.5f",cpAvg));
			}
			/**
			 * i print the wallet of all traders
			 */
			else if (query == 555) {
				for (Trader t:traders) {
					out.println("Trader "+t.getId()+": "+String.format("%.5f",t.getWallet().getDollars()+t.getWallet().getBlockedDollars())+"$ "
				+String.format("%.5f",t.getWallet().getCoins()+t.getWallet().getBlockedCoins())+"PQ");
				}
			}
		}
	}
}
