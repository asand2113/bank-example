package bank;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * The Checking Class extends the Account class and is one of the three types of
 * accounts for this software. The utility account stores data of up to three
 * prior utility payments and the total number of utility payments made over the
 * account's lifetime. Additionally, this class also has bespoke Methods for
 * getting and setting each of the stored past payments, calculating the date of
 * the next due payment, and getting and setting the total number of payments
 * made over the account's lifetime.
 * 
 * @author Andrew Sand
 */
public class Utility extends Account {
	/**
	 * Member variable that stores the first prior payment's date
	 */
	private Date history1;

	/**
	 * Member variable that stores the second prior payment's date
	 */
	private Date history2;

	/**
	 * Member variable that stores the third prior payment's date
	 */
	private Date history3;

	/**
	 * Member variable that stores the first prior payment's amount
	 */
	private int bill1;

	/**
	 * Member variable that stores the second prior payment's amount
	 */
	private int bill2;

	/**
	 * Member variable that stores the third prior payment's amount
	 */
	private int bill3;

	/**
	 * Member variable that stores the total number of utility payments made over
	 * the account's lifetime
	 */
	private int totalPaid;

	/**
	 * Override Method to deposit money into the account. However, this override is
	 * more of a fail safe so as a user can not deposit into a utility account.
	 */
	@Override
	public int deposit(int n) {
		return 0;
	}

	/**
	 * Method that prints out up to the last three utility payments the user has
	 * made (if there are that many to show).
	 */
	public void history() {
		// Printing header
		System.out.println("\nYour Account History (Last 3 Payments):");
		// Testing if there is a history1 to show
		if (history1 != null) {
			System.out.println("$" + bill1 + " paid on " + history1);
		}
		// Testing if there is a history2 to show
		if (history2 != null) {
			System.out.println("$" + bill2 + " paid on " + history2);
		}
		// Testing if there is a history3 to show
		if (history3 != null) {
			System.out.println("$" + bill3 + " paid on " + history3);
		}
	}

	/**
	 * Method that prints out the date and cost of the next due utility payment
	 * based on how many payments have already been made
	 */
	public void due() {
		System.out.println("Your next Utility Bill is due on " + get_next_date() + " for a total of $100");
	}

	/**
	 * Helper method for due() which calculates the next payment due date based on
	 * how many payments have already been made.
	 * 
	 * @return today - the date of the next payment's due date. It will be the first
	 *         of a month based on how many payments have already been made.
	 */
	private LocalDate get_next_date() {
		// Get today's date
		LocalDate today = LocalDate.now();

		// Skip the correct amount of months based on payments
		for (int i = 0; i < totalPaid + 1; i++) {
			// Adjust the date to the first of the next month
			today = today.with(TemporalAdjusters.firstDayOfNextMonth());
		}

		// Return the date
		return today;
	}

	/**
	 * Method that adds a payment to the account's payment history. It shifts over
	 * prior payments back in the list if necessary.
	 * 
	 * @param date - date of the payment to be added
	 */
	public void add_payment(Date date) {
		// Checking if there has been first, second, and/or third payments
		if (history1 == null) {
			// Set the first payment
			history1 = date;
			bill1 = 100;
		} else if (history2 == null) {
			// Move everything done by 1
			history2 = history1;
			bill2 = bill1;
			history1 = date;
			bill1 = 100;
		} else {
			// Move everything down by one
			history3 = history2;
			bill3 = bill2;
			history2 = history1;
			bill2 = bill1;
			history1 = date;
			bill1 = 100;
		}

		// Increment the number of payments paid
		totalPaid++;
	}

	/**
	 * Getter Method that returns the desired payment history in string format
	 * 
	 * @param n - the payment history to retrieve (will be 1, 2, or 3)
	 * @return history - the payment history in string format
	 */
	@SuppressWarnings("deprecation")
	public String get_history(int n) {
		// Variable declaration and initialisation
		String history = null;

		// Checking which history should be returned
		switch (n) {
		case 1:
			// If there is no history, return an empty history
			if (history1 == null) {
				history = "0 0 0 0";
			} else {
				// Otherwise, get its day, month, year, and bill
				history = Integer.toString(history1.getDate()) + " " + Integer.toString(history1.getMonth()) + " "
						+ Integer.toString(history1.getYear()) + " " + Integer.toString(bill1);
			}
			break;
		case 2:
			// If there is no history, return an empty history
			if (history2 == null) {
				history = "0 0 0 0";
			} else {
				// Otherwise, get its day, month, year, and bill
				history = Integer.toString(history2.getDate()) + " " + Integer.toString(history2.getMonth()) + " "
						+ Integer.toString(history2.getYear()) + " " + Integer.toString(bill2);
			}
			break;
		case 3:
			// If there is no history, return an empty history
			if (history3 == null) {
				history = "0 0 0 0";
			} else {
				// Otherwise, get its day, month, year, and bill
				history = Integer.toString(history3.getDate()) + " " + Integer.toString(history3.getMonth()) + " "
						+ Integer.toString(history3.getYear()) + " " + Integer.toString(bill3);
			}
			break;
		}

		// Return the history
		return history;
	}

	/**
	 * Setter Method that sets a payment's history to the input parameters. There is
	 * an additional input parameter that is used to determine which of the three
	 * payment histories should be set.
	 * 
	 * @param n     - which payment history should be set (Will be 1, 2, or 3)
	 * @param year  - the year of the payment
	 * @param month - the month of the payment
	 * @param day   - the day of the payment
	 * @param bill  - how much the payment was
	 */
	@SuppressWarnings("deprecation")
	public void set_history(int n, int year, int month, int day, int bill) {
		// Checking which payment history should be set
		switch (n) {
		case 1:
			// Set history 1
			history1 = new Date(year, month, day);
			bill1 = bill;
			break;
		case 2:
			// Set history 1
			history2 = new Date(year, month, day);
			bill2 = bill;
			break;
		case 3:
			// Set history 3
			history3 = new Date(year, month, day);
			bill3 = bill;
			break;
		}
	}

	/**
	 * Getter Method that returns the total number of utility payments made over the
	 * account's lifetime
	 * 
	 * @return totalPaid - the total number of utility payments made over the
	 *         account's lifetime
	 */
	public int get_total() {
		return totalPaid;
	}

	/**
	 * Setter Method that sets the total number of utility payments made over the
	 * account's lifetime to the input parameter
	 * 
	 * @param n - what the total utility payments member variable should be set to
	 */
	public void set_total(int n) {
		totalPaid = n;
	}
}
