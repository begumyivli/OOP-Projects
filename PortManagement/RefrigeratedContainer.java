
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * this class creates a refrigerated container object
 * @author begum yivli
 *
 */
public class RefrigeratedContainer extends HeavyContainer{
	private final String type = "Refr";
	/**
	 * 
	 * @param ID
	 * @param weight
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		this.consumptionConstant = 5.00;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

