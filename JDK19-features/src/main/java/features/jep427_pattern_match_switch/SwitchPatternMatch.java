package features.jep427_pattern_match_switch;

import features.jep427_pattern_match_switch.Expr.AddExpr;
import features.jep427_pattern_match_switch.Expr.DivExpr;
import features.jep427_pattern_match_switch.Expr.IntExpr;
import features.jep427_pattern_match_switch.Expr.MulExpr;

sealed interface Expr permits IntExpr, AddExpr, DivExpr, MulExpr {

  record IntExpr(int val) implements Expr {}

  record AddExpr(Expr left, Expr right) implements Expr {}

  record DivExpr(Expr left, Expr right) implements Expr {}

  record MulExpr(Expr left, Expr right) implements Expr {}
}

public class SwitchPatternMatch {

  public static void main(String[] args) {
    System.out.println(eval(new AddExpr(new IntExpr(10), new IntExpr(6))));
  }

  static int eval(Expr n) {
    return switch (n) {
      case IntExpr intExpr -> intExpr.val();
      case AddExpr addExpr -> eval(addExpr.left()) + eval(addExpr.right());
      case MulExpr mulExpr -> eval(mulExpr.left()) * eval(mulExpr.right());
      case DivExpr divExpr-> eval(divExpr.left()) / eval(divExpr.right());
      case null -> throw new IllegalArgumentException("Wrong expression");
    };
  }
}
