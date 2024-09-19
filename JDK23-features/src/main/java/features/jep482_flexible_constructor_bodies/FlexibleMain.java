package features.jep482_flexible_constructor_bodies;

import java.time.LocalDate;

public class FlexibleMain {

  public static void main(String[] args) {
    System.out.println(new BankTransfer("1234", 1000.0, LocalDate.now().minusDays(10), "Someone111", "Somebody22"));

  }

  public static class Transaction {
    private final String transactionId;
    private final double amount;
    private final LocalDate date;

    public Transaction(String transactionId, double amount, LocalDate date) {
      this.transactionId = transactionId;
      this.amount = amount;
      this.date = date;
      validate();
    }

    protected void validate() {
      if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
      if (transactionId == null || transactionId.isEmpty()) throw new IllegalArgumentException("Transaction ID cannot be null or empty.");
      if (date == null || date.isAfter(LocalDate.now())) throw new IllegalArgumentException("Date could not be null or in the future.");
    }
  }

  public static class BankTransfer extends Transaction {
    private final String senderBankAccount;
    private final String receiverBankAccount;

    // Auxiliary method to validate bank accounts
    private static String validateBankAccount(String account) {
      if (account == null || account.length() != 10) {
        throw new IllegalArgumentException("Invalid bank account.");
      }
      return account;
    }

    // Constructor uses auxiliary method to prepare arguments for superclass constructor
    public BankTransfer(String transactionId, double amount, LocalDate date, String sender, String receiver) {
      super(transactionId, amount, date);
      this.senderBankAccount = validateBankAccount(sender);
      this.receiverBankAccount = validateBankAccount(receiver);
    }

   /* @Override
    protected void validate() {
      super.validate();
      if (senderBankAccount.equals(receiverBankAccount)) {
        throw new IllegalArgumentException("Sender and receiver accounts cannot be the same.");
      }
    }*/
  }
}
