
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * this class creates a basic container object
 * @author begum yivli
 *
 */
public class BasicContainer extends Container{
	private final String type = "Basic";
	/**
	 * 
	 * @param ID
	 * @param weight
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		this.consumptionConstant = 2.50;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

