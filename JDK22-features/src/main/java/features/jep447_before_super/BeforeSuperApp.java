package features.jep447_before_super;

public class BeforeSuperApp {

  public static class ContactPerson {

    private final String firstName;
    private final String lastName;

    public ContactPerson(String fullName) {
      var firstName1 = fullName.split(" ")[0];
      var lastName1 = fullName.split(" ")[1];
      this(firstName1, lastName1);
    }

    public ContactPerson(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    @Override
    public String toString() {
      return STR."My name is \{firstName} \{lastName}";
    }
  }

  public static void main(String[] args) {
    System.out.println(new ContactPerson("John Snow"));
  }
}
