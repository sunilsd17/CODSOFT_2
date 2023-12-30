package atminterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private static final int nextAccountNumber = 1001; // Starting account number
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(int accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class ATM {
    private Map<Integer, BankAccount> accounts;
    Scanner scanner;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void createAccount() {
        System.out.print("Enter your name: ");
        String accountHolderName = scanner.next();
        System.out.print("Enter your initial deposit amount: $");
        double initialBalance = scanner.nextDouble();

        int accountNumber = generateAccountNumber();
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, newAccount);

        System.out.println("Account created successfully.");
        System.out.println("Your account number is: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Initial Balance: $" + initialBalance);
    }

    private int generateAccountNumber() {
        int nextAccountNumber = 0;
        return nextAccountNumber++;
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Check Balance");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Exit");
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                createAccount();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                deposit();
                break;
            case 4:
                withdraw();
                break;
            case 5:
                System.out.println("Exiting ATM. Thank you!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void checkBalance() {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        BankAccount userAccount = accounts.get(accountNumber);
        if (userAccount != null) {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Holder: " + userAccount.getAccountHolderName());
            System.out.println("Current Balance: $" + userAccount.getBalance());
        } else {
            System.out.println("Account not found. Please enter a valid account number.");
        }
    }

    private void deposit() {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        BankAccount userAccount = accounts.get(accountNumber);
        if (userAccount != null) {
            System.out.print("Enter deposit amount: $");
            double amount = scanner.nextDouble();
            userAccount.deposit(amount);
        } else {
            System.out.println("Account not found. Please enter a valid account number.");
        }
    }

    private void withdraw() {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        BankAccount userAccount = accounts.get(accountNumber);
        if (userAccount != null) {
            System.out.print("Enter withdrawal amount: $");
            double amount = scanner.nextDouble();
            userAccount.withdraw(amount);
        } else {
            System.out.println("Account not found. Please enter a valid account number.");
        }
    }
}

public class atminterface {
    public static void main(String[] args) {
        ATM atm = new ATM();

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            int option = atm.scanner.nextInt();

            atm.processOption(option);
        }
    }
}
