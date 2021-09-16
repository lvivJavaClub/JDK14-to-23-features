package features.jep406_pattern_matching_switch.visitor_design_pattern;

interface Visitor {

  void visit(XmlElement xe);
  void visit(JsonElement je);
}

class ElementVisitor implements Visitor {

  @Override
  public void visit(XmlElement xe) {
    System.out.println("processing xml element with id: " + xe.id());
  }

  @Override
  public void visit(JsonElement je) {
    System.out.println("processing json element with id: " + je.id());
  }
}
