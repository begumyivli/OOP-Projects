
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * this class creates a liquid container object
 * @author begum yivli
 *
 */
public class LiquidContainer extends HeavyContainer {
	private final String type = "Liq";
	/**
	 * 
	 * @param ID
	 * @param weight
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		this.consumptionConstant = 4.00;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

