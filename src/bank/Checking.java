package bank;

import java.util.Date;

/**
 * The Checking Class extends the Account class and is one of the three types of
 * accounts for this software. The differences between a checking account and a
 * generic account are that the user can pay utility bills, withdraw funds (up
 * to $500 per day), transfer money to a savings account, and the Class contains
 * special Getter and Setter Methods to handle the unique withdrawn variable.
 * 
 * @author Andrew Sand
 */
public class Checking extends Account {
	/**
	 * Member variables that tracks the total amount withdrawn from the account in
	 * that day
	 */
	private int withdrawn = 0;

	/**
	 * Method used to withdraw an appropriate amount of money from the account.
	 * Checks that the account balance will not become negative and the daily
	 * withdraw limit is not reached before doing the withdraw.
	 * 
	 * @param n - the amount to withdraw with the account
	 */
	public int withdraw(int n) {
		// Checking if the balance will go below 0
		if (balance() - n < 0) {
			// Cancel the withdraw, balance would be below 0
			System.out.println("Warning: Withdraw canceled. Account balance can not become negative.");
			return 1;
		} else {
			// Otherwise, check if the daily withdraw limit would be exceeded
			if (withdrawn + n > 500) {
				// Daily withdraw limit exceeded, cancel the withdraw
				System.out.println(
						"Warning: Withdraw canceled. Desired withdraw amount would exceed daily withdraw limit.");
				System.out.println("You have withdrawn a total of $" + withdrawn
						+ " from this account today. The daily withdraw limit is $500.");
				return 1;
			} else {
				// Otherwise, everything it good and the money can be withdrawn
				add_balance(-n);
				withdrawn += n;
				return 0;
			}
		}
	}

	/**
	 * Method used to handle the monthly utility payment.
	 * 
	 * @param user - the user that is paying
	 */
	public void pay(User user) {
		// Checking if the balance will go below 0
		if (balance() - 100 < 0) {
			// Cancel the payment, balance would be below 0
			System.out.println("Warning: Payment canceled. Account balance can not become negative.");
		} else {
			// Otherwise, payment will succeed. Subtract funds and add the new payment
			add_balance(-100);
			user.util_acc.add_payment(new Date());
		}
	}

	/**
	 * Method used to transfer money between two accounts. Checks that there is an
	 * existing savings account and that the transfer will not result in a negative
	 * account balance before doing the transfer.
	 * 
	 * @param user - the owner of the account, used to get the savings and checking
	 *             account references
	 * @param n    - the amount to be transfered
	 * @return 0 if successful, 1 if failure
	 */
	@Override
	public int transfer(User user, int n) {
		// Checking if the user has a savings account
		if (user.saving_acc == null) {
			// No savings account found
			System.out.println("Error: User does not have a Savings Account to transfer to.");
			return 0;
		} else {
			// Checking if the transfer will result in a negative account balance
			if (balance() - n < 0) {
				// Transfer will result in a negative account balance
				System.out.println("Warning: Transfer canceled. Account balance can not become negative.");
				return 1;
			} else {
				// Otherwise, the transfer is set and can occur
				add_balance(-n);
				user.saving_acc.add_balance(n);
				return 0;
			}
		}
	}

	/**
	 * Setter Method that sets the day's withdrawn amount to the input parameter.
	 * 
	 * @param n - the desired amount to set the day's withdrawn amount to
	 */
	public void set_withdrawn(int n) {
		withdrawn = n;
	}

	/**
	 * Getter Method that returns the amount of money withdrawn from this account in
	 * the day.
	 * 
	 * @return withdrawn - the amount of money withdrawn from this account in the
	 *         day
	 */
	public int get_withdrawn() {
		return withdrawn;
	}
}
