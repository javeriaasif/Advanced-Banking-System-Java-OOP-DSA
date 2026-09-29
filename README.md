🏧 DecodeLabs Project 3: Advanced ATM Interface

Objective:
To develop a secure Java-based ATM system that goes beyond basic transactions and simulates real-world banking architecture.

What Makes It Advanced?

1.  Object-Oriented Architecture: Implemented two-layer separation of concerns - BankAccount (data & logic) and Bank (data management) and ATM (user interface). All sensitive fields like balance and PIN are private (Encapsulation).

2.  DSA Integration: Used HashMap<String, BankAccount> for storing multiple accounts, providing O(1) time complexity for account search. Used ArrayList<String> with LocalDateTime API for maintaining a timestamped audit trail of all transactions.

3.  Real-World Validation: Added PIN-based authentication, input validation for negative amounts, and custom Exception handling for Insufficient Funds.

Key Operations: Balance Check, Cash Deposit, Cash Withdrawal, Transaction History View.

Tech Stack: Java, OOP Principles, Collections Framework (HashMap, ArrayList), Exception Handling, Date/Time API.
