
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * this abstract class contains the constructor and the necessary variables for the subclasses for example type and consumption constant.
 * it calculates the fuel amount consumed by the each container per KM
 * it contains necessary getter and setter methods
 * it implements comparable interface because it compares containers according to their ID's
 * @author begum yivli
 *
 */
public abstract class Container implements Comparable<Container>{
	/**
	 * ID of the container,final because it is particular and not changeable
	 */
	final int ID;
	/**
	 * weight of the container
	 */
	private int weight;
	/**
	 * the id of the port where this container is located
	 */
	private int portID;
	/**
	 * fuel constant per kg*km consumed by each container
	 */
	protected double consumptionConstant;
	/**
	 * each container's type
	 */
	protected String type;
	/**
	 * 
	 * @param ID container's ID
	 * @param weight container's weight
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	/**
	 * 
	 * @return the fuel amount consumed by the container per KM
	 */
	public double consumption() {
		return consumptionConstant*weight;
	}
	/**
	 * 
	 * @param other
	 * @return true if two objects are the equal
	 */
	
	public boolean equals(Container other) {
		if (this.getClass() == other.getClass() && this.weight == other.getWeight() && this.ID == other.getID()) {
			return true;
		}
		else {
			return false;
		}
    }
	
	/**
	 * @return getter for the ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @return getter for the weight
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @return getter for the portID
	 */
	public int getPortID() {
		return portID;
	}
	/**
	 * @param portID the portID to set
	 */
	public void setPortID(int portID) {
		this.portID = portID;
	}
	/**
	 * @return getter for the type
	 */
	public String getType() {
		return type;
	}
 /**
  * it compares the containers by their ID's
  * and returns a integer value
  */
	@Override
	public int compareTo(Container cont){  
		if(this.ID==cont.ID) {
			return 0; 
		}
		else if(this.ID>cont.ID) {
			return 1;  
		}
		else {
			return -1;
		} 
	}
}






//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

