package bank;

/**
 * The Checking Class extends the Account class and is one of the three types of
 * accounts for this software. The Savings account differs in that there is a
 * transfer limit from the savings account to the checking account that needs to
 * be accounted for. Furthermore, there are getter and setter Methods for the
 * newly added transfered member variable, which keeps track of the amount
 * transfered from the account in the day.
 * 
 * @author Andrew Sand
 */
public class Savings extends Account {
	/**
	 * Member variable that tracks the amount of money transfered from this account
	 * in the day
	 */
	private int transfered = 0;

	/**
	 * Method used to transfer money between two accounts. The Method ensures that a
	 * checking accounts exists and that the daily transfer limit will not be
	 * exceeded.
	 * 
	 * @param user - the owner of the account, used to get the savings and checking
	 *             account references
	 * @param n    - the amount to be transfered
	 * @return 0 if successful, 1 if failure
	 */
	@Override
	public int transfer(User user, int n) {
		// Checking if the user has a checking account
		if (user.check_acc == null) {
			System.out.println("Error: User does not have a Checking Account to transfer to.");
			return 0;
		}
		// Checking if the transfer will put the account over the transfer daily limit
		else if (n + transfered > 100) {
			System.out
					.println("Warning: Transfer canceled. Desired transfer amount would exceed daily transfer limit.");
			System.out.println("You have transfered a total of $" + transfered
					+ " from this account today. The daily transfer limit is $100.");
			return 1;
		}
		// Checking if the transfer will result in a negative account balance
		else if (balance() - n < 0) {
			System.out.println("Warning: Transfer canceled. Account balance can not become negative.");
			return 1;
		}
		// Otherwise, the transfer can occur
		else {
			add_balance(-n);
			user.check_acc.add_balance(n);
			transfered += n;
			return 0;
		}
	}

	/**
	 * Getter Method that returns the amount of money transfered from this account
	 * in the day.
	 * 
	 * @return transfered - the amount of money transfered from this account in the
	 *         day
	 */
	public int get_transfered() {
		return transfered;
	}

	/**
	 * Setter Method that sets the amount of money transfered from the account in
	 * the day to the input parameter.
	 * 
	 * @param n - the desired amount to set the day's transfered amount to
	 */
	public void set_transfered(int n) {
		transfered = n;
	}
}
