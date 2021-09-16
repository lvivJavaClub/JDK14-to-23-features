package features.jep406_pattern_matching_switch.visitor_design_pattern;

import java.util.List;
import java.util.UUID;

abstract class Element {

  private final String uuid;

  public Element() {
    uuid = UUID.randomUUID().toString();
  }

  public String id() {
    return uuid;
  }

  public abstract void accept(Visitor v);
}

class XmlElement extends Element {

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}

class JsonElement extends Element {

  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}

class Document extends Element {

  private final List<Element> elements;

  public Document(List<Element> elements) {
    this.elements = elements;
  }

  @Override
  public void accept(Visitor v) {
    this.elements.forEach(e -> e.accept(v));
  }
}
