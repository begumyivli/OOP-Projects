
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * this class creates a heavy container object
 * @author begum yivli
 *
 */
public class HeavyContainer extends Container{
	private final String type = "Heavy";
	/**
	 * 
	 * @param ID
	 * @param weight
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		this.consumptionConstant = 3.00;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

