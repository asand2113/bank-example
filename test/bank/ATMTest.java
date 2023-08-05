package bank;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import bank.User;

/**
 * A test set designed to test the basic functionality of the User, Account,
 * Checking, Savings, and Utility Classes
 * 
 * @author Andrew Sand
 */
public class ATMTest {
	User test;

	@Before
	public void setup() {
		test = new User();
	}

	/**
	 * Testing User Object's construction
	 */
	@Test
	public void construction_test() {
		assertTrue(test.check_acc == null && test.saving_acc == null && test.util_acc == null && test.get_id() == 0);
	}

	/**
	 * Testing the User's ID setting and getting
	 */
	@Test
	public void id_test() {
		test.set_id(500);
		assertTrue(test.get_id() == 500);
	}

	/**
	 * Testing creating a checking account
	 */
	@Test
	public void checking_test() {
		test.create_account(User.accounts.CHECKING);
		assertTrue(test.check_acc != null && test.check_acc.balance() == 0 && test.check_acc.get_deposited() == 0
				&& test.check_acc.get_withdrawn() == 0);
	}

	/**
	 * Testing creating a savings account
	 */
	@Test
	public void savings_test() {
		test.create_account(User.accounts.SAVINGS);
		assertTrue(test.saving_acc != null && test.saving_acc.balance() == 0 && test.saving_acc.get_deposited() == 0
				&& test.saving_acc.get_transfered() == 0);
	}

	/**
	 * Testing creating a utility account
	 */
	@Test
	public void utility_test() {
		test.create_account(User.accounts.UTILITY);
		assertTrue(test.util_acc != null && test.util_acc.balance() == 0 && test.util_acc.get_deposited() == 0
				&& test.util_acc.get_history(1).compareTo("0 0 0 0") == 0
				&& test.util_acc.get_history(2).compareTo("0 0 0 0") == 0
				&& test.util_acc.get_history(3).compareTo("0 0 0 0") == 0 && test.util_acc.get_total() == 0);
	}

	/**
	 * Testing depositing into a checking account
	 */
	@Test
	public void checkDep_test() {
		test.create_account(User.accounts.CHECKING);
		test.check_acc.deposit(100);
		assertTrue(test.check_acc.balance() == 100);
	}

	/**
	 * Testing depositing boundaries for a checking account
	 */
	@Test
	public void checkDepBound_test() {
		test.create_account(User.accounts.CHECKING);
		test.check_acc.deposit(0);
		test.check_acc.deposit(5001);
		test.check_acc.deposit(10000);
		test.check_acc.deposit(4500);
		test.check_acc.deposit(499);
		test.check_acc.deposit(2);
		test.check_acc.deposit(1);
		test.check_acc.deposit(5);
		assertTrue(test.check_acc.balance() == 5000);
	}

	/**
	 * Testing withdrawing from a checking account
	 */
	@Test
	public void withdraw_test() {
		test.create_account(User.accounts.CHECKING);
		test.check_acc.deposit(100);
		test.check_acc.withdraw(50);
		assertTrue(test.check_acc.balance() == 50);
	}

	/**
	 * Testing withdrawing bounds for a checking account
	 */
	@Test
	public void withdrawBound_test() {
		test.create_account(User.accounts.CHECKING);
		test.check_acc.withdraw(5);
		test.check_acc.deposit(100);
		test.check_acc.withdraw(50);
		test.check_acc.withdraw(1000);
		test.check_acc.withdraw(495);
		test.check_acc.deposit(1000);
		test.check_acc.withdraw(495);
		test.check_acc.withdraw(5);
		assertTrue(test.check_acc.balance() == 1045);
	}

	/**
	 * Testing utility payment boundaries
	 */
	@Test
	public void payBound_test() {
		test.create_account(User.accounts.CHECKING);
		test.check_acc.pay(test);
		test.check_acc.deposit(50);
		test.check_acc.pay(test);
		assertTrue(test.check_acc.balance() == 50);
	}

	/**
	 * Testing transfer checking boundaries
	 */
	@Test
	public void checkTransferBound_test() {
		test.create_account(User.accounts.CHECKING);
		test.check_acc.transfer(test, 100);
		test.create_account(User.accounts.SAVINGS);
		test.check_acc.transfer(test, 100);
		assertTrue(test.check_acc.balance() == 0);
	}

	/**
	 * Testing transfer savings boundaries
	 */
	@Test
	public void savingTransferBound_test() {
		test.create_account(User.accounts.SAVINGS);
		test.saving_acc.transfer(test, 100);
		test.create_account(User.accounts.CHECKING);
		test.saving_acc.transfer(test, 100);
		test.saving_acc.deposit(100);
		assertTrue(test.saving_acc.balance() == 100);
	}

	/**
	 * Testing adding one payment to the utility account
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void add1Pay_test() {
		test.create_account(User.accounts.UTILITY);
		Date now = new Date();
		test.util_acc.add_payment(now);
		assertTrue(test.util_acc.get_history(1).compareTo(Integer.toString(now.getDate()) + " "
				+ Integer.toString(now.getMonth()) + " " + Integer.toString(now.getYear()) + " 100") == 0);
	}

	/**
	 * Testing adding two payments to the utility account
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void add2Pay_test() {
		test.create_account(User.accounts.UTILITY);
		Date now = new Date();
		test.util_acc.add_payment(now);
		test.util_acc.add_payment(now);
		assertTrue(test.util_acc.get_history(1)
				.compareTo(Integer.toString(now.getDate()) + " " + Integer.toString(now.getMonth()) + " "
						+ Integer.toString(now.getYear()) + " 100") == 0
				&& test.util_acc.get_history(2).compareTo(Integer.toString(now.getDate()) + " "
						+ Integer.toString(now.getMonth()) + " " + Integer.toString(now.getYear()) + " 100") == 0);
	}

	/**
	 * Testing adding three payments to the utility account
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void add3Pay_test() {
		test.create_account(User.accounts.UTILITY);
		Date now = new Date();
		test.util_acc.add_payment(now);
		test.util_acc.add_payment(now);
		test.util_acc.add_payment(now);
		assertTrue(test.util_acc.get_history(1)
				.compareTo(Integer.toString(now.getDate()) + " " + Integer.toString(now.getMonth()) + " "
						+ Integer.toString(now.getYear()) + " 100") == 0
				&& test.util_acc.get_history(2)
						.compareTo(Integer.toString(now.getDate()) + " " + Integer.toString(now.getMonth()) + " "
								+ Integer.toString(now.getYear()) + " 100") == 0
				&& test.util_acc.get_history(3).compareTo(Integer.toString(now.getDate()) + " "
						+ Integer.toString(now.getMonth()) + " " + Integer.toString(now.getYear()) + " 100") == 0);
	}
	
	
}
