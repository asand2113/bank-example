package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The ATM Class is the code that simulates the physical ATM and drives the
 * software. This Class contains all of the code for file I/O, user input,
 * managing user accounts, user interactivity, and console output. Furthermore,
 * it contains the main Method for the software, meaning that this is where
 * execution will begin.
 * 
 * @author Andrew Sand
 */
public class ATM {
	public static void main(String[] args) {
		// Variable declaration and initialisation
		int id = -1;
		int input;
		File userDir, checkDir, savingDir, utilDir;
		Scanner scnr = new Scanner(System.in);
		Scanner filescnr;
		User user = new User();
		List<String> write_line;
		Path write_path;

		// Prompting user for an ID number
		System.out.println("Welcome to the Fake Inc. Bank ATM.");
		System.out.println("Please enter your ID number to log in: ");

		// Reading in the ID number from the user
		do {
			// Attempting to read an ID from the user
			try {
				id = scnr.nextInt();
				// Testing if the ID was positive (or zero)
				if (id <= 0) {
					throw new InputMismatchException("ID was either negative or zero");
				}
				userDir = new File(System.getProperty("user.dir") + "/accounts/" + Integer.toString(id));
				// Attempting to create a directory for that user ID
				if (!userDir.mkdir()) {
					throw new FileNotFoundException("User Directory already created");
				}
				// Create a new user for that ID
				else {
					System.out.println("User data created successfully!");
					System.out.println("Welcome new user!");
				}
				break;
			}
			// Catching invalid ID input
			catch (InputMismatchException e) {
				// Handling the exception and re-prompting the user
				scnr.nextLine();
				System.out.println("Error: Input was not a valid ID number, please try again.");
				System.out.println("Please enter an ID number to log in or create an account: ");
			}
			// Catching that the user already exists
			catch (FileNotFoundException e) {
				scnr.nextLine();
				System.out.println("User already in database");
				System.out.println("Welcome back, user " + id);
				break;
			}
		} while (true);

		// Setting up the internal user data
		System.out.println("Loading User Data...");
		user.set_id(id);
		checkDir = new File(System.getProperty("user.dir") + "/accounts/" + Integer.toString(id) + "/checking.txt");
		savingDir = new File(System.getProperty("user.dir") + "/accounts/" + Integer.toString(id) + "/savings.txt");
		utilDir = new File(System.getProperty("user.dir") + "/accounts/" + Integer.toString(id) + "/utility.txt");

		// Check if the file exists
		if (checkDir.exists()) {
			// Prepare the internal account structure
			user.create_account(User.accounts.CHECKING);
			// Attempt to read from the user's data
			try {
				filescnr = new Scanner(checkDir);
				user.check_acc.add_balance(filescnr.nextInt());
				user.check_acc.set_deposited(filescnr.nextInt());
				user.check_acc.set_withdrawn(filescnr.nextInt());
			} catch (Exception e) {
				// Failed to find the file
				System.out.println("Error: Failed to read Checking Account Data.");
			}
		}
		// Check if the file exists
		if (savingDir.exists()) {
			// Prepare the internal account structure
			user.create_account(User.accounts.SAVINGS);
			// Attempt to read from the user's data
			try {
				filescnr = new Scanner(savingDir);
				user.saving_acc.add_balance(filescnr.nextInt());
				user.saving_acc.set_deposited(filescnr.nextInt());
				user.saving_acc.set_transfered(filescnr.nextInt());
			} catch (Exception e) {
				// Failed to find the file
				System.out.println("Error: Failed to read Savings Account Data.");
			}

		}
		// Check if the file exists
		if (utilDir.exists()) {
			// Prepare and read in data
			user.create_account(User.accounts.UTILITY);
			// Attempt to read from the user's data
			try {
				//Read in all of the transaction history
				filescnr = new Scanner(utilDir);
				int day1 = filescnr.nextInt();
				int month1 = filescnr.nextInt(); 
				int year1 = filescnr.nextInt();
				int bill1 = filescnr.nextInt();
				int day2 = filescnr.nextInt();
				int month2 = filescnr.nextInt(); 
				int year2 = filescnr.nextInt();
				int bill2 = filescnr.nextInt();
				int day3 = filescnr.nextInt();
				int month3 = filescnr.nextInt(); 
				int year3 = filescnr.nextInt();
				int bill3 = filescnr.nextInt();
				
				//Test if the first transaction is valid
				if(year1 != 0) {
					//If so, set it
					user.util_acc.set_history(1, year1, month1, day1, bill1);
				}
				
				//Test if the second transaction is valid
				if(year2 != 0) {
					//If so, set it
					user.util_acc.set_history(2, year2, month2, day2, bill2);
				}
				
				//Test if the third transaction is valid
				if(year3 != 0) {
					//If so, set it
					user.util_acc.set_history(3, year3, month3, day3, bill3);
				}
				//Read in and set the total number of payments
				user.util_acc.set_total(filescnr.nextInt());
			} catch (Exception e) {
				// Failed to find the file
				System.out.println("Error: Failed to read Utility Account Data.");
			}
		}

		//Print that the user data finished being read in
		System.out.println("User Data Successfully Loaded!");

		// Main operations loop
		do {
			// Prompting the user
			System.out.println("\nPlease enter the number of the option you would like to select");
			// Checking
			if (user.check_acc == null) {
				System.out.println("1. Create a new Checking Account");
			} else {
				System.out.println("1. Access Checking Account");
			}
			// Savings
			if (user.saving_acc == null) {
				System.out.println("2. Create a new Savings Account");
			} else {
				System.out.println("2. Access Savings Account");
			}
			// Utility
			if (user.util_acc == null) {
				System.out.println("3. Create a new Utility Account");
			} else {
				System.out.println("3. Access Utility Account");
			}
			// Exit
			System.out.println("4. Logout and Exit");

			// Attempting to get user input
			try {
				input = scnr.nextInt();
			}
			// Catching and re-prompting if the user input was bad
			catch (InputMismatchException e) {
				scnr.nextLine();
				System.out.println("Error: Input was not a valid option, please try again");
				continue;
			}

			// Checking which option the user selected
			switch (input) {
			// Checking
			case 1:
				// Checking if the user already has a checking account
				if (user.check_acc == null) {
					// Create a new account
					user.create_account(User.accounts.CHECKING);
				} else {
					// Access the account
					do {
						// Prompting the user
						System.out.println("\nChecking Account:");
						System.out.println("\nPlease enter the number of the option you would like to select");
						System.out.println("1. Deposit");
						System.out.println("2. Withdraw");
						System.out.println("3. Transfer to Savings Account");
						System.out.println("4. Pay Utility Bills");
						System.out.println("5. Check Balance");
						System.out.println("6. Back");

						// Attempting to get user input
						try {
							input = scnr.nextInt();
						}
						// Catching and re-prompting if the user input was bad
						catch (InputMismatchException e) {
							scnr.nextLine();
							System.out.println("Error: Input was not a valid option, please try again");
							continue;
						}

						// Checking which option the user selected
						switch (input) {
						// Deposit
						case 1:
							do {
								// Prompting the user
								System.out.println("Please input how much you would like to deposit: ");

								// Attempting to get user input
								try {
									input = scnr.nextInt();
								}
								// Catching and re-prompting if the user input was bad
								catch (InputMismatchException e) {
									scnr.nextLine();
									System.out.println("Error: Input was not a valid option, please try again");
									continue;
								}
							} while (user.check_acc.deposit(input) == 1);
							break;
						// Withdraw
						case 2:
							do {
								// Prompting the user
								System.out.println("Please input how much you would like to withdraw: ");

								// Attempting to get user input
								try {
									input = scnr.nextInt();
								}
								// Catching and re-prompting if the user input was bad
								catch (InputMismatchException e) {
									scnr.nextLine();
									System.out.println("Error: Input was not a valid option, please try again");
									continue;
								}
							} while (user.check_acc.withdraw(input) == 1);
							break;
						// Transfer
						case 3:
							do {
								// Prompting the user
								System.out.println("Please input how much you would like to transfer: ");

								// Attempting to get user input
								try {
									input = scnr.nextInt();
								}
								// Catching and re-prompting if the user input was bad
								catch (InputMismatchException e) {
									scnr.nextLine();
									System.out.println("Error: Input was not a valid option, please try again");
									continue;
								}
							} while (user.check_acc.transfer(user, input) == 1);
							break;
						// Pay
						case 4:
							// Checking if the user has a utility account
							if (user.util_acc == null) {
								//No Utility account found
								System.out.println("Error: User does not have a Utility Account to pay to.");
							} else {
								//Prompting if the user wishes to go through with the payment
								do {
									//User Prompt
									System.out.println("Do you wish to pay your utility bill of $100 now?");
									System.out.println("1. Yes");
									System.out.println("2. No");
									
									// Attempting to get user input
									try {
										input = scnr.nextInt();
									}
									// Catching and re-prompting if the user input was bad
									catch (InputMismatchException e) {
										scnr.nextLine();
										System.out.println("Error: Input was not a valid option, please try again");
										continue;
									}
								}while(input != 1 && input != 2);
								
								//Testing if the user wishes to pay their utility bill
								if(input == 1) {
									user.check_acc.pay(user);
								}
							}
							break;
						// Balance
						case 5:
							System.out.println("Your account's balance is: $" + user.check_acc.balance());
							break;
						// Back
						case 6:
							break;
						// Input was out of bounds
						default:
							// Print error message
							System.out.println("Error: Input was not a valid option, please try again");
							break;
						}
					} while (input != 6);
				}
				break;
			// Savings
			case 2:
				// Checking if the user already has a savings account
				if (user.saving_acc == null) {
					// Create a new account
					user.create_account(User.accounts.SAVINGS);
				} else {
					// Access the account
					do {
						// Prompting the user
						System.out.println("\nSavings Account:");
						System.out.println("\nPlease enter the number of the option you would like to select");
						System.out.println("1. Deposit");
						System.out.println("2. Transfer to Checking Account");
						System.out.println("3. Check Balance");
						System.out.println("4. Back");

						// Attempting to get user input
						try {
							input = scnr.nextInt();
						}
						// Catching and re-prompting if the user input was bad
						catch (InputMismatchException e) {
							scnr.nextLine();
							System.out.println("Error: Input was not a valid option, please try again");
							continue;
						}

						// Checking user input
						switch (input) {
						// Deposit
						case 1:
							do {
								// Prompting the user
								System.out.println("Please input how much you would like to deposit: ");

								// Attempting to get user input
								try {
									input = scnr.nextInt();
								}
								// Catching and re-prompting if the user input was bad
								catch (InputMismatchException e) {
									scnr.nextLine();
									System.out.println("Error: Input was not a valid option, please try again");
									continue;
								}
							} while (user.saving_acc.deposit(input) == 1);
							break;
						// Transfer
						case 2:
							do {
								// Prompting the user
								System.out.println("Please input how much you would like to transfer: ");

								// Attempting to get user input
								try {
									input = scnr.nextInt();
								}
								// Catching and re-prompting if the user input was bad
								catch (InputMismatchException e) {
									scnr.nextLine();
									System.out.println("Error: Input was not a valid option, please try again");
									continue;
								}
							} while (user.saving_acc.transfer(user, input) == 1);
							break;
						// Balance
						case 3:
							System.out.println("Your account's balance is: $" + user.saving_acc.balance());
							break;
						// Back
						case 4:
							break;
						// Input was out of bounds
						default:
							// Print error message
							System.out.println("Error: Input was not a valid option, please try again");
							break;
						}
					} while (input != 4);
				}
				break;
			// Utility
			case 3:
				// Checking if the user already has a utility account
				if (user.util_acc == null) {
					// Create a new account
					user.create_account(User.accounts.UTILITY);
				} else {
					// Access the account
					do {
						// Prompting the user
						System.out.println("\nUtility Account:");
						System.out.println("\nPlease enter the number of the option you would like to select");
						System.out.println("1. View Payment History");
						System.out.println("2. View Next Payment Details");
						System.out.println("3. Back");

						// Attempting to get user input
						try {
							input = scnr.nextInt();
						}
						// Catching and re-prompting if the user input was bad
						catch (InputMismatchException e) {
							scnr.nextLine();
							System.out.println("Error: Input was not a valid option, please try again");
							continue;
						}

						// Checking user input
						switch (input) {
						// History
						case 1:
							user.util_acc.history();
							break;
						// Next Payment
						case 2:
							user.util_acc.due();
							break;
						// Back
						case 3:
							break;
						// Input was out of bounds
						default:
							// Print error message
							System.out.println("Error: Input was not a valid option, please try again");
							break;
						}
					} while (input != 3);
				}
				break;
			// Exit
			case 4:
				// Write the data to the checking file
				// Prep Variables
				if(user.check_acc != null) {
					write_line = Arrays.asList(Integer.toString(user.check_acc.balance()) + " "
							+ Integer.toString(user.check_acc.get_deposited()) + " "
							+ Integer.toString(user.check_acc.get_withdrawn()));
					write_path = Paths.get(checkDir.toString());
					// Attempt to write to the file
					try {
						Files.write(write_path, write_line, StandardCharsets.UTF_8);
					} catch (Exception e) {
						// Something failed with writing to the file
						System.out
								.println("Error: Failed to write to the user data file. Please contact customer support.");
					}
				}

				// Write the data to the savings file
				// Prep Variables
				if(user.saving_acc != null) {
					write_line = Arrays.asList(Integer.toString(user.saving_acc.balance()) + " "
							+ Integer.toString(user.saving_acc.get_deposited()) + " "
							+ Integer.toString(user.saving_acc.get_transfered()));
					write_path = Paths.get(savingDir.toString());
					// Attempt to write to the file
					try {
						Files.write(write_path, write_line, StandardCharsets.UTF_8);
					} catch (Exception e) {
						// Something failed with writing to the file
						System.out
								.println("Error: Failed to write to the user data file. Please contact customer support.");
					}
				}
				
				// Write the data to the utility file
				// Prep Variables
				if(user.util_acc != null) {
					write_line = Arrays.asList(user.util_acc.get_history(1) + " "
							+ user.util_acc.get_history(2) + " "
							+ user.util_acc.get_history(3) + " " + Integer.toString(user.util_acc.get_total()));
					write_path = Paths.get(utilDir.toString());
					// Attempt to write to the file
					try {
						Files.write(write_path, write_line, StandardCharsets.UTF_8);
					} catch (Exception e) {
						// Something failed with writing to the file
						System.out
								.println("Error: Failed to write to the user data file. Please contact customer support.");
					}
				}
				
				// Printing exit message
				System.out.println("Logging out...");
				System.out.println("Thank you for using the Fake Inc. Bank ATM today!");
				// Close resources
				scnr.close();
				return;
			// Input was out of bounds
			default:
				// Print error message
				System.out.println("Error: Input was not a valid option, please try again");
			}

		} while (true);
	}
}
