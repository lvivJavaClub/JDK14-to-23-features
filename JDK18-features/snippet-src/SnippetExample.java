package jep413_code_snippets;

import java.util.Optional;

public class SnippetExample {

  void show(Optional<String> v) {
    // @start region="example"
    if (v.isPresent()) {
      System.out.println("v: " + v.get());
    }
    // @end
  }
}
