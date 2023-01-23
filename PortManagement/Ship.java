
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;
import java.util.ArrayList;
import java.util.Collections;
import containers.Container;
import interfaces.IShip;
import ports.Port;
/**
 * this class creates ship object and a list of containers, provides travel to other ports, controls loading and unloading operations, and refuels.
 * it contains getter and setter methods for the necesssary variables.
 * it implements comparable interface because it compares ships according to their ID's
 * @author begum yivli
 *
 */
public class Ship implements IShip, Comparable<Ship>{
	/**
	 * ID of the ship,final because it is particular and not changeable
	 */
	final int ID;
	/**
	 * this variable shows the fuel of the ship
	 */
	private double fuel = 0.0;
	/**
	 * this variable shows which port the ship is in
	 */
	private Port currentPort;
	/**
	 * this is ship and its cotaniners' total weight
	 */
	private int currentWeight;
	/**
	 * this is the number of heavy containers on this ship
	 */
	private int numHeavy;
	/**
	 * this is the number of refrigerated containers on this ship
	 */
	private int numRefr;
	/**
	 * this is the number of liquid containers on this ship
	 */
	private int numLiq;
	/**
	 * this is the total weight this ship can carry, final because constant
	 */
	private final int totalWeightCapacity;
	/**
	 * this is the total number of containers this ship can carry, final because constant
	 */
	private final int maxNumberOfAllContainers;
	/**
	 * this is the total number of heavy containers this ship can carry, final because constant
	 */
	private final int maxNumberOfHeavyContainers;
	/**
	 * this is the total number of refrigerated containers this ship can carry, final because constant
	 */
	private final int maxNumberOfRefrigeratedContainers;
	/**
	 * this is the total number of liquid containers this ship can carry, final because constant
	 */
	private final int maxNumberOfLiquidContainers;
	/**
	 * this is the fuel consumed by this ship per KM
	 */
	private final double fuelConsumptionPerKM;
	ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * 
	 * @param ID ship's ID
	 * @param p ship's current port
	 * @param totalWeightCapacity max weight the ship can carry
	 * @param maxNumberOfAllContainers max number of container the ship can carry
	 * @param maxNumberOfHeavyContainers max number of heavy container the ship can carry
	 * @param maxNumberOfRefrigeratedContainers max number of refrigerated container the ship can carry
	 * @param maxNumberOfLiquidContainers max number of liquid container the ship can carry
	 * @param fuelConsumptionPerKM fuel consumed by the ship per KM
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
			int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		p.getCurrent().add(this);
	}
	/**
	 * 
	 * @return ArrayList of the containers currrntly in the ship sorted by id
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(containers);
		return containers;
	}
	/**
	 * this method calculate the necessary fuel amount to sail to the Port p
	 * if ship has enough fuel and if the parameter is not the current port, it returns true
	 */
	@Override
	public boolean sailTo(Port p) {
		double totalConsumption = 0.0;
		for (Container container : containers) {
			totalConsumption += container.consumption();
		}
		totalConsumption += this.fuelConsumptionPerKM;
		totalConsumption = totalConsumption*currentPort.getDistance(p);
		if (this.fuel >= totalConsumption) {
			this.fuel-=totalConsumption;
			this.currentPort.outgoingShip(this);
			this.currentPort = p;
			this.currentPort.incomingShip(this);
			return true;
		}
		else {
			return false;
			}
		}
	/**
	 * this method adds newFuel amount of gasoline to the ship
	 */
	@Override
	public void reFuel(double newFuel) {
		this.fuel += newFuel;
	}
	/**
	 * this method return true if the container to be loaded(cont) is in the same port with the ship, port contains the container to be loaded,
	 * container to be loaded is not already on the ship, and ship has a place for the container
	 */
	@Override
	public boolean load(Container cont) {
		if (cont.getPortID() != this.currentPort.getID()) {
			return false;
		}
		else if (containers.size() + 1 > this.maxNumberOfAllContainers) {
			return false;
		}
		else if (this.currentWeight + cont.getWeight() > this.totalWeightCapacity) {
			return false;
		}
		else if (cont.getType().equals("Heavy") && (this.numHeavy + 1 > this.maxNumberOfHeavyContainers)) {
			return false;
		}
		else if (cont.getType().equals("Refr") && ((this.numRefr + 1 > this.maxNumberOfRefrigeratedContainers) || (this.numHeavy + 1 > this.maxNumberOfHeavyContainers))) {
				return false;
		}
		else if (cont.getType().equals("Liq") && ((this.numLiq + 1 > this.maxNumberOfLiquidContainers) || (this.numHeavy + 1 > this.maxNumberOfHeavyContainers))) {
			return false;
		}
		else if(containers.contains(cont)){
			return false;
		}
		else if (!this.currentPort.getContainers().contains(cont)) {
			return false;
		}
		else {
			this.currentWeight += cont.getWeight();
			this.containers.add(cont);
			this.currentPort.getContainers().remove(cont);
			if (cont.getType().equals("Heavy")) {
				this.numHeavy++;
			}
			else if (cont.getType().equals("Liq")) {
				this.numLiq++;
				this.numHeavy++;
			}
			else if(cont.getType().equals("Refr")) {
				this.numRefr++;
				this.numHeavy++;
			}
			return true;
		}
	}
	/**
	 * this method return true if the ship contains the container to be unloaded(cont)
	 */
	@Override
	public boolean unLoad(Container cont) {
		if (containers.contains(cont)) {
			this.currentWeight -= cont.getWeight();
			this.containers.remove(cont);
			this.currentPort.getContainers().add(cont);
			if (cont.getType().equals("Heavy")) {
				this.numHeavy--;
			}
			else if (cont.getType().equals("Liq")) {
				this.numLiq--;
				this.numHeavy--;
			}
			else if(cont.getType().equals("Refr")) {
				this.numRefr--;
				this.numHeavy--;
			}
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * this is a comparing method which compares ships according to their ID
	 */
	@Override
	public int compareTo(Ship o) {
		if(this.ID==o.ID) {
			return 0; 
		}
		else if(this.ID>o.ID) {
			return 1;  
		}
		else {
			return -1;
		}
	}
	/**
	 * @return getter method for the currentWeight
	 */
	public int getCurrentWeight() {
		return currentWeight;
	}

	/**
	 * @return getter method for the number of heavy containers in the ship
	 */
	public int getNumHeavy() {
		return numHeavy;
	}

	/**
	 * @return getter method for the number of refrigerated containers in the ship
	 */
	public int getNumRefr() {
		return numRefr;
	}

	/**
	 * @return getter method for the number of liquid containers in the ship
	 */
	public int getNumLiq() {
		return numLiq;
	}

	/**
	 * @param currentWeight the currentWeight to set
	 */
	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}
	
	/**
	 * @param numHeavy the numHeavy to set
	 */
	public void setNumHeavy(int numHeavy) {
		this.numHeavy = numHeavy;
	}

	/**
	 * @param numRefr the numRefr to set
	 */
	public void setNumRefr(int numRefr) {
		this.numRefr = numRefr;
	}

	/**
	 * @param numLiq the numLiq to set
	 */
	public void setNumLiq(int numLiq) {
		this.numLiq = numLiq;
	}

	/**
	 * @return getter method for the fuel
	 */
	public double getFuel() {
		return fuel;
	}
	/**
	 * @param fuel the fuel to set
	 */
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}
	/**
	 * @return getter method for the currentPort
	 */
	public Port getCurrentPort() {
		return currentPort;
	}

	/**
	 * @param currentPort the currentPort to set
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}

	/**
	 * @return getter method for the ID
	 */
	public int getID() {
		return ID;
	}	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

