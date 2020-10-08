package n02_jep384_Records;

import lombok.Builder;

@Builder
public record PersonRecord(String name, String surname) {

    public static final PersonRecord UNKNOWN = new PersonRecord("Unknown");
//
//    public PersonRecord {
//        Objects.requireNonNull(name);
//        Objects.requireNonNull(surname);
//    }

    public PersonRecord(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public PersonRecord(String name) {
        this(name, "Anonymous");
    }

    public String foo() {
        return "Blah-blah";
    }
}
