package n02_jep384_Records;

public class App {

    public static void main(String[] args) {
        var person1 = new Person("John", "Doe");
        var person2 = new Person("John", "Doe");

        var personOne = new PersonRecord("John", "Doe");
        var personTwo = new PersonRecord("John", "Doe");
        System.out.println(personOne);
        System.out.println(personOne.hashCode());
        System.out.println(personOne.equals(personTwo));

        System.out.println(personTwo.name());
        System.out.println(personOne.surname());

        var nullablePerson = new PersonRecord(null, null);
        System.out.println(nullablePerson);

        var anonymous = new PersonRecord("Jack");
        System.out.println(anonymous);

        var pr = PersonRecord.builder().build();
        System.out.println(pr);
    }
}
