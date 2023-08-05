package bank;

/**
 * The Account Class is the highest point of the Class hierarchy for this
 * software. The Checking, Savings, and Utility Classes all extend this one.
 * This Class contains the common code for running an account, such as
 * depositing money, tracking how much has been deposited in one day, and
 * editing the member variables associated with those actions.
 * 
 * @author Andrew Sand
 */
public class Account {
	/**
	 * Member variable that stores how much money is currently in the account
	 */
	private int funds = 0;

	/**
	 * Member variable that stores how much money has been deposited in the day
	 */
	private int deposited = 0;

	/**
	 * Method that handles depositing money into an account. It adds to the funding
	 * and deposited member variables only if the user is not trying to deposit more
	 * that $5000 in one day.
	 * 
	 * @param n - the amount desired to be deposited
	 * @return 1 if the deposit failed, 0 if it succeeded
	 */
	public int deposit(int n) {
		// Checking if the desired deposit amount will cause the account to exceed its
		// daily deposit limit
		if (n + deposited > 5000) {
			// Daily deposit limit exceeded
			System.out
					.println("Warning: Transaction canceled. Desired deposit amount would exceed daily deposit limit.");
			System.out.println("You have deposited a total of $" + deposited
					+ " to this account today. The daily deposit limit is $5000.");
			return 1;
		} else {
			// Daily deposit limit not exceeded, deposit can occur
			funds += n;
			deposited += n;
			return 0;
		}
	}

	/**
	 * Getter Method that returns the account's current balance
	 * 
	 * @return funds - how much money is currently in the account
	 */
	public int balance() {
		return funds;
	}

	/**
	 * Method that adds the input parameter to the account's current funds
	 * 
	 * @param n - the amount to be added to the funds
	 */
	public void add_balance(int n) {
		funds += n;
	}

	/**
	 * Method used to transfer money between two accounts. This is a method
	 * prototype, overwritten in subclasses.
	 * 
	 * @param user - the owner of the account, used to get the savings and checking
	 *             account references
	 * @param n    - the amount to be transfered
	 * @return 0 if successful, 1 if failure
	 */
	public int transfer(User user, int n) {
		return 0;
	}

	/**
	 * Setter Method that sets the amount deposited into the account in that day to
	 * the input parameter.
	 * 
	 * @param n - the amount to set the deposited member variable to
	 */
	public void set_deposited(int n) {
		deposited = n;
	}

	/**
	 * Getter Method that returns the amount of money that has been deposited into
	 * the account thus far in the day.
	 * 
	 * @return deposited - the amount of money deposited into the account in that
	 *         day
	 */
	public int get_deposited() {
		return deposited;
	}
}