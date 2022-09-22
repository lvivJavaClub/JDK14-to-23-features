package features.jep427_428_structured_cuncurrency_with_virtual;

import features.jep427_428_structured_cuncurrency_with_virtual.VolunteerRequest.Medicines;
import java.time.LocalDate;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

record VolunteerRequest(int requestId, LocalDate dueDate) implements Callable<Medicines> {

  public VolunteerRequest(int requestId) {
    this(requestId, LocalDate.now().plusDays(1));
  }

  public record Medicines(LocalDate deliveryDate, int price) {}


  @Override
  public Medicines call() throws InterruptedException {
    TimeUnit.SECONDS.sleep(1);
    System.out.println(Thread.currentThread());

    LocalDate deliveryDate = randomDateBetween(LocalDate.now(), LocalDate.now().plusDays(10));
    int possiblePrice = RandomGenerator.getDefault().nextInt(100, 1000);
    var medicines = new Medicines(deliveryDate, possiblePrice);

    if (medicines.deliveryDate.isAfter(dueDate)) {
      String errorMsg = "%s - is too late to deliver request %s. Deadline: %s".formatted(medicines.deliveryDate, requestId, dueDate);
      System.out.println(errorMsg);
      throw new DueDateExceededException(errorMsg);
    }

    System.out.printf("Nice! Request is %d ok!%n", requestId);

    return medicines;
  }

  private static LocalDate randomDateBetween(LocalDate startInclusive, LocalDate endExclusive) {
    long randomDay = RandomGenerator.getDefault().nextLong(
        startInclusive.toEpochDay(), endExclusive.toEpochDay()
    );

    return LocalDate.ofEpochDay(randomDay);
  }

  public static class DueDateExceededException extends RuntimeException {

    public DueDateExceededException(String message) {
      super(message);
    }
  }
}
