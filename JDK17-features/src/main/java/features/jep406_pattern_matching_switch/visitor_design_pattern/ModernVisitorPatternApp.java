package features.jep406_pattern_matching_switch.visitor_design_pattern;

import java.util.Collections;
import java.util.List;

public class ModernVisitorPatternApp {

  public static void main(String[] args) {
    oldSolution();
  }

  private static void oldSolution() {
    Visitor visitor = new ElementVisitor();
    List<Element> elements = List.of(new JsonElement(), new XmlElement(), new JsonElement(), new Document(Collections.emptyList()));
    Document document = new Document(elements);

    document.accept(visitor);
  }
}
