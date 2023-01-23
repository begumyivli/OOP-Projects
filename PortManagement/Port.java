
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import containers.Container;
import interfaces.IPort;
import ships.Ship;
/**
 * this class creates the port object, calculates the distance between two ports,
 * when a ship comes it adds the ship to the current list with incomingShip method and when a ship leaves it removes from the current list and adds to history list with outgoingShip
 * it contains getter methods for the necessary variables
 * @author begum yivli
 * @date 12/06/2021
 */
public class Port implements IPort {
	/**
	 * ID of the port,final because it is particular and not changeable
	 */
	final int ID;
	/**
	 * port's location on the x axis, final because it is fixed
	 */
	private final double X;
	/**
	 * port's location on the y axis, final because it is fixed
	 */
	private final double Y;
	/**
	 * this arraylist includes the containers in the port
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * this arraylist keeps track of the ships currently in the port
	 */
	private ArrayList<Ship> current = new ArrayList<Ship>();
	/**
	 * this arraylist keeps track of the every ship that has visited
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * creates a port object with 3 parameter:
	 * @param ID port's ID
	 * @param X  port's position on the x-axis
	 * @param Y  port's position on the y-axis
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
	}
	/**
	 * 
	 * @param other port of destination
	 * @return return the distance between 2 ports
	 */
	public double getDistance(Port other) {
		return Math.sqrt((Math.pow((this.X-other.X),2))+(Math.pow((this.Y-other.Y),2)));
	}
	/**
	 * when a ship comes to a port, this method adds the ship in port's current list
	 * 
	 */
	@Override
	public void incomingShip(Ship s) {
		current.add(s);
	}
	/**
	 * when a ship goes from a port, this method removes the ship in port's current list
	 * and adds the ship in port's history list
	 */
	@Override
	public void outgoingShip(Ship s) {
		if (!history.contains(s)) {
			history.add(s);
		}
		current.remove(s);
	}
	/**
	 * this method sorts the containers according to their ID
	 * @return the current containers
	 */
	public ArrayList<Container> getContainers() {
		Collections.sort(containers);
		return containers;
	}
	/**
	 * this method sorts the ships according to their ID
	 * @return the current ships
	 */
	public ArrayList<Ship> getCurrent() {
		Collections.sort(current);
		return current;
	}
	/**
	 * @return getter for the ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @return getter for the x coordinate
	 */
	public double getX() {
		return X;
	}
	/**
	 * @return getter for the y coordinate
	 */
	public double getY() {
		return Y;
	}
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

