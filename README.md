# bank-example
## Overview
An example banking structure made using Java. Originally, this program was designed for a university course for educational purposes. The software simulates a bank's internal account structure and an ATM to provide user interactibility. Each user of the simulated bank has a unique ID number, which is used to access their accounts. There are three different account types that can be attributed to a user: checking, savings, and utilities. Each user can have at most one of each account type open under their ID number. Fictional money can be deposited, withdrawn, and used to pay fictitious utility bills based on different rules that each account type has.

All user data for the software is stored in the "accounts" directory, where a number sub-folder is created for each account ID. All banking data is stored in standard text files. 

Additionally, several unit tests—written in JUnit 4—are included in the "test" directory.

**Important Note:** This software is by no means intended for public usage; it only exists for academic purposes to better understand basic data persistence and how Classes can interact with each other in Java. If this were to ever be used publicly, massive re-writes would need to occur, promoting security, privacy, and usability. 
## Usage
When run, the program prompts the user to enter an ID number. If no account exists using that ID number, a new one will be created. Otherwise, typing in an ID number of an existing account will log the user into that account. Once logged in, the user may select various self-explanatory options provided in the terminal by entering the respective number into the command line. 
## Files
```
- ATMTest.java : Contains the JUnit tests for the software
- Account.java : Superclass of the three account types
- ATM.java : Simulates a bank ATM, which allows the user to interact with their accounts. Houses the core software functionality
- Checking.java : Checking Class that handles Checking Accounts
- Savings.java : Savings Class that handles Savings Accounts
- User.java : User Class that stores all information about the currently logged in user
- Utility.java : Utility Class that handles Utility Accounts
```
