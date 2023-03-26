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
    System.out.println(eval(new MulExpr(new IntExpr(10), new IntExpr(6))));
    System.out.println(eval(new DivExpr(new IntExpr(100), null)));
    System.out.println(eval(new IntExpr(100)));
    System.out.println(eval(null));
  }

  static int eval(Expr n) {
    return switch (n) {
      case IntExpr(int val) -> val;
      case AddExpr(var l, var r) -> eval(l) + eval(r);
      case MulExpr(var l, var r) -> eval(l) * eval(r);
      case DivExpr(var l, var r) when eval(r) != 0 -> eval(l) / eval(r);
      case DivExpr(var l, var r) when eval(r) == 0 -> eval(l);
      case null -> 0;
      case default -> throw new IllegalArgumentException("Wrong expression");
    };
  }
}
