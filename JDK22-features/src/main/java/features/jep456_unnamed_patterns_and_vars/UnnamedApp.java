package features.jep456_unnamed_patterns_and_vars;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnnamedApp {

  sealed interface Contact permits BeneficialOwner, LegalRep, Signatory, HelpPerson {}

  record BeneficialOwner(String name, String phone) implements Contact {}

  record LegalRep() implements Contact {}

  record Signatory() implements Contact {}

  record HelpPerson() implements Contact {}

  record Company(String legalName, String legalForm, Contact owner) {}

  public static void main(String[] args) {
    Object company = fetchRelatedCompany();

    if (company instanceof Company(_, _, BeneficialOwner(_, var ownerPhone))) {
      System.out.println("Calling... " + ownerPhone);
    }

    if (company instanceof Company(_, _, var contact)) {
      switch (contact) {
        case BeneficialOwner _, LegalRep _, Signatory _ -> System.out.println("OWNER");
        case HelpPerson _ -> System.out.println("helping");
      }
    }

    // An enhanced for loop with side effects:
    var strings = List.of("1", "2");
    var set = new HashSet<>(Set.of("1", "2"));
    int counter = 0;
    for (var _ : strings) {
      counter += 1;
    }
    // An assignment statement, where the result of the expression on the right hand side is not needed:
    var _ = set.add("12312");
    // A catch block:
    try {

    } catch (IllegalArgumentException _) {
      System.out.println("ERROR");
    } catch (Exception _) {
      System.out.println("ERROR");
    }
    // In try-with-resources:
    try(var _ = Executors.newCachedThreadPool()) {

    }
    // A lambda whose parameter is irrelevant:
    strings.stream().peek(_ -> {
      System.out.println("NEXT ITERATION");
    }).count();
  }

  private static Object fetchRelatedCompany() {
    return new Company("Amazon", "LLC", new BeneficialOwner("Jeff Bezos", "+3428979233"));
  }
}
