import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Class 1: BankAccount
class BankAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private ArrayList<String> history;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public BankAccount(String accNo, String pin, double balance) {
        this.accountNumber = accNo;
        this.pin = pin;
        this.balance = balance;
        this.history = new ArrayList<>();
        addTransaction("Account Opened with Balance: " + balance);
    }

    public boolean validatePin(String inputPin) { return this.pin.equals(inputPin); }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid Amount");
        balance += amount;
        addTransaction("Deposited: " + amount + " | New Balance: " + balance);
        System.out.println("Deposited Successfully!");
    }

    public void withdraw(double amount) throws Exception {
        if (amount <= 0) throw new IllegalArgumentException("Invalid Amount");
        if (amount > balance) throw new Exception("Insufficient Funds! Available: " + balance);
        balance -= amount;
        addTransaction("Withdrawn: " + amount + " | New Balance: " + balance);
        System.out.println("Withdrawn Successfully!");
    }

    private void addTransaction(String msg) {
        String time = LocalDateTime.now().format(formatter);
        history.add(time + " - " + msg);
    }

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public void printHistory() {
        System.out.println("\n--- Transaction History for " + accountNumber + " ---");
        for(String h : history) System.out.println(h);
    }
}

// Class 2: Bank - DSA HashMap
class Bank {
    private HashMap<String, BankAccount> accounts = new HashMap<>();
    public Bank() {
        accounts.put("1001", new BankAccount("1001", "1234", 50000));
        accounts.put("2026001", new BankAccount("2026001", "0000", 5000));
    }
    public BankAccount getAccount(String accNo) { return accounts.get(accNo); }
}

// Class 3: Main ATM - Isko hi run karna hai
public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        BankAccount currentAccount = null;

        System.out.println("=== DecodeLabs Advanced ATM System ===");
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        BankAccount acc = bank.getAccount(accNo);
        if (acc != null && acc.validatePin(pin)) {
            currentAccount = acc;
            System.out.println("\nLogin Successful! Welcome " + accNo);
        } else {
            System.out.println("Invalid Account No or PIN! Exiting...");
            sc.close();
            return;
        }

        int choice;
        do {
            System.out.println("\n---------- MENU ----------");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            try {
                switch (choice) {
                    case 1: System.out.println("Current Balance: Rs. " + currentAccount.getBalance()); break;
                    case 2: System.out.print("Enter Deposit Amount: "); currentAccount.deposit(sc.nextDouble()); break;
                    case 3: System.out.print("Enter Withdraw Amount: "); currentAccount.withdraw(sc.nextDouble()); break;
                    case 4: currentAccount.printHistory(); break;
                    case 5: System.out.println("Thank You for using DecodeLabs ATM!"); break;
                    default: System.out.println("Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);
        sc.close();
    }
}