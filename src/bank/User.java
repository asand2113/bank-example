package bank;

/**
 * The User Class stores all of the information about the logged in user. In
 * total, this Class tracks the user's ID, checking account Object, savings
 * account Object, and utility account Object. Additionally, it contains methods
 * for editing the ID and creating blank accounts of the three types.
 * Furthermore, there is an internal enumerated type for the account names that
 * makes distinguishing between account easier.
 * 
 * @author Andrew Sand
 */
public class User {
	// Enumeration to make creating accounts later easier
	enum accounts {
		CHECKING, SAVINGS, UTILITY
	}

	/**
	 * Member variable that stores the user's ID number
	 */
	private int ID;

	/**
	 * Member variable that stores the user's checking account
	 */
	public Checking check_acc;

	/**
	 * Member variable that stores the user's savings account
	 */
	public Savings saving_acc;

	/**
	 * Member variable that stores the user's utility account
	 */
	public Utility util_acc;

	/**
	 * Method that creates an empty account of the specified type for the user
	 * 
	 * @param type - what type of account should be created for the user. Options:
	 *             CHECKING, SAVINGS, or UTILITY
	 */
	public void create_account(accounts type) {
		// Testing what type of account should be created
		if (type == accounts.CHECKING) {
			// Checking
			check_acc = new Checking();
		} else if (type == accounts.SAVINGS) {
			// Savings
			saving_acc = new Savings();
		} else {
			// Utility
			util_acc = new Utility();
		}
	}

	/**
	 * Getter method that returns the account's ID number
	 * 
	 * @return ID - the account's ID number
	 */
	public int get_id() {
		return ID;
	}

	/**
	 * Setter method to set the user's ID number
	 * 
	 * @param n - the value the user's ID should be set to
	 */
	public void set_id(int n) {
		ID = n;
	}
}
