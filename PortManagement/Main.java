
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import containers.BasicContainer;
import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import ports.Port;
import ships.Ship;
/**
 * This class reads the input file and performs the necessary operations according to the input number,
 * and finally prints an output file according to the data.
 * @author begum yivli
 *
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		/**
		 * arraylist of each created container object
		 */
		ArrayList<Container> containerList = new ArrayList<Container>();
		/**
		 * arraylist of each created ship object
		 */
		ArrayList<Ship> shipList = new ArrayList<Ship>();
		/**
		 * arraylist of each created port object
		 */
		ArrayList<Port> portList = new ArrayList<Port>();
		String first = in.next();
		int forNum=Integer.parseInt(first);
		int cont = 0;
		int ship = 0;
		int port = 0;
		/**
		 * converted the input file from string to list and assigned each element to a variable of its own type.
		 */
		for (int i = 0; i < forNum+1; i++) {
			String str = in.nextLine().trim();
			if (i==0) {
				continue;
			}
			String[] arrOfStr = str.split(" ", 8);
			String s0=arrOfStr[0];  
		    int intFirst=Integer.parseInt(s0);
		    /**
		     *  if input number is 1, creates container object and adds it to the containerList
		     */
		    if (intFirst == 1) {
		    	if (arrOfStr.length == 4) {
		    	String s1=arrOfStr[1];
		    	int portID=Integer.parseInt(s1);
		    	String s2=arrOfStr[2];
		    	int weight=Integer.parseInt(s2);
		    	String RorL=arrOfStr[3];
		        if (RorL.equals("R")) {
		    		RefrigeratedContainer myContainer = new RefrigeratedContainer(cont, weight);
		    		myContainer.setPortID(portID);
		    		portList.get(portID).getContainers().add(myContainer);
		        	containerList.add(myContainer);
		    	}
		    	else if (RorL.equals("L")) {
		    		LiquidContainer myContainer = new LiquidContainer(cont, weight);
		    		myContainer.setPortID(portID);
		    		portList.get(portID).getContainers().add(myContainer);
		        	containerList.add(myContainer);
		    	}
		    	}
		    	else if (arrOfStr.length == 3) {
			    	String s1=arrOfStr[1];
			    	int portID=Integer.parseInt(s1);
			    	String s2=arrOfStr[2];
			    	int weight=Integer.parseInt(s2);
			    	if (weight <= 3000) {
			    		BasicContainer myContainer = new BasicContainer(cont, weight);
			    		myContainer.setPortID(portID);
			    		portList.get(portID).getContainers().add(myContainer);
			    		containerList.add(myContainer);
			    	}
			    	else if (weight > 3000) {
			    		HeavyContainer myContainer = new HeavyContainer(cont, weight);
			    		myContainer.setPortID(portID);
			    		portList.get(portID).getContainers().add(myContainer);
			    		containerList.add(myContainer);
			    	}
			    	}
		    	cont++;
		    }
		    /**
		     *  if input number is 2, creates Ship object and adds it to the shipList
		     */
		    else if (intFirst == 2) {
		    	String s1=arrOfStr[1];
		    	int portID=Integer.parseInt(s1);
		    	String s2=arrOfStr[2];
		    	int maxWeightAll=Integer.parseInt(s2);
		    	String s3=arrOfStr[3];
		    	int maxNumAll=Integer.parseInt(s3);
		    	String s4=arrOfStr[4];
		    	int maxNumHeavy=Integer.parseInt(s4);
		    	String s5=arrOfStr[5];
		    	int maxNumRef=Integer.parseInt(s5);
		    	String s6=arrOfStr[6];
		    	int maxNumLiq=Integer.parseInt(s6);
		    	String s7=arrOfStr[7];
		    	double fuelPerKM=Double.parseDouble(s7);
		    	Ship myShip = new Ship(ship, portList.get(portID), maxWeightAll, maxNumAll, maxNumHeavy, maxNumRef, maxNumLiq, fuelPerKM);
	        	shipList.add(myShip);
	        	ship++;
		    }
		    /**
		     * if input number is 3, creates Port object and adds it to the portList
		     */
		    else if (intFirst == 3) {
		    	String s1=arrOfStr[1];
		    	double x=Double.parseDouble(s1);
		    	String s2=arrOfStr[2];
		    	double y=Double.parseDouble(s2);
		    	Port myPort = new Port(port,x,y);
		    	portList.add(myPort);
		    	port++;
		    }
		    /**
		     * if input number is 4, calls the Load method in Ship class
		     */
		    else if (intFirst == 4) {
		    	String s1=arrOfStr[1];
		    	int shipID=Integer.parseInt(s1);
		    	String s2=arrOfStr[2];
		    	int containerID=Integer.parseInt(s2);
		    	shipList.get(shipID).load(containerList.get(containerID));
		    }
		    /**
		     * if input number is 5, calls the unLoad method in Ship class
		     */
		    else if (intFirst == 5) {
		    	String s1=arrOfStr[1];
		    	int shipID=Integer.parseInt(s1);
		    	String s2=arrOfStr[2];
		    	int containerID=Integer.parseInt(s2);
		    	shipList.get(shipID).unLoad(containerList.get(containerID));
		    }
		    /**
		     * if input is 6, calls the sailTo method in Ship class
		     */
		    else if (intFirst == 6) {
		    	String s1=arrOfStr[1];
		    	int shipID=Integer.parseInt(s1);
		    	String s2=arrOfStr[2];
		    	int portID=Integer.parseInt(s2);
		    	shipList.get(shipID).sailTo(portList.get(portID));
		    }
		    /**
		     * if input is 7, calls the reFuel method in Ship class
		     */
		    else if (intFirst == 7){
		    	String s1=arrOfStr[1];
		    	int shipID=Integer.parseInt(s1);
		    	String s2=arrOfStr[2];
		    	double fuelAdded=Double.parseDouble(s2);
		    	shipList.get(shipID).reFuel(fuelAdded);
		    }
		}
		int mySize = portList.size();
		for (int i = 0; i < mySize; i++) {
			out.println("Port "+i+": ("+ String.format("%.2f",portList.get(i).getX())+", "+ String.format("%.2f",portList.get(i).getY())+")");
			int x1 = 0;
			String basic = "";
			for(Container container: portList.get(i).getContainers()) {
				if (container.getType().equals("Basic")) {
						if (x1==0) {
							out.print("  BasicContainer:");
							x1++;
						}
						basic+=(" "+container.getID());
				}
			}
			if (!basic.equals("")) {
				out.println(basic);
			}
			int x2=0;
			String heavy = "";
			for(Container container: portList.get(i).getContainers()) {
				if (container.getType().equals("Heavy")) {
					if (x2==0) {
						out.print("  HeavyContainer:");
						x2++;
					}
					heavy+=(" "+container.getID());
				}
			}
			if (!heavy.equals("")) {
				out.println(heavy);
			}
			int x3 = 0;
			String refr = "";
			for(Container container: portList.get(i).getContainers()) {
				if (container.getType().equals("Refr")) {
					if (x3==0) {
						out.print("  RefrigeratedContainer:");
						x3++;
					}
					refr+=(" "+container.getID());
				}
			}
			if (!refr.equals("")) {
				out.println(refr);
			}
			int x4 = 0;
			String liq = "";
			for(Container container: portList.get(i).getContainers()) {
				if (container.getType().equals("Liq")) {
					if (x4==0) {
						out.print("  LiquidContainer:");
						x4++;
					}
					liq+=(" "+container.getID());
				}
			}
			if (!liq.equals("")) {
				out.println(liq);
			}
			for(Ship myShip: portList.get(i).getCurrent()) {
				out.println("  Ship "+myShip.getID()+": "+String.format("%.2f",myShip.getFuel()));
				int x5 = 0;
				String basic2 = "";
				for(Container container: myShip.getCurrentContainers()) {
					if (container.getType().equals("Basic")) {
						if (x5==0) {
							out.print("    BasicContainer:");
							x5++;
						}
						basic2+=(" "+container.getID());
					}
				}
				if (!basic2.equals("")) {
					out.println(basic2);
				}
				int x6 = 0;
				String heavy2 = "";
				for(Container container: myShip.getCurrentContainers()) {
					if (container.getType().equals("Heavy")) {
						if (x6==0) {
							out.print("    HeavyContainer:");
							x6++;
						}
						heavy2+=(" "+container.getID());
					}
				}
				if (!heavy2.equals("")) {
					out.println(heavy2);
				}
				int x7 = 0;
				String refr2 = "";
				for(Container container: myShip.getCurrentContainers()) {
					if (container.getType().equals("Refr")) {
						if (x7==0) {
							out.print("    RefrigeratedContainer:");
							x7++;
						}
						refr2+=(" "+container.getID());
					}
				}
				if (!refr2.equals("")) {
					out.println(refr2);
				}
				int x8 = 0;
				String liq2 = "";
				for(Container container: myShip.getCurrentContainers()) {
					if (container.getType().equals("Liq")) {
						if (x8==0) {
							out.print("    LiquidContainer:");
							x8++;
						}
						liq2+=(" "+container.getID());
					}
				}
				if (!liq2.equals("")) {
					out.println(liq2);
				}
				}
		}
		
		in.close();
		out.close();
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

