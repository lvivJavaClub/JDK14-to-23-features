package features.jep414_vector_api;

import java.util.random.RandomGeneratorFactory;
import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

public class VectorIncubatorApp {

  static final VectorSpecies<Double> SPECIES = DoubleVector.SPECIES_PREFERRED;

  public static void main(String[] args) {

    var randomGenerator = RandomGeneratorFactory.getDefault().create();
    final double[] a = randomGenerator.doubles(1_000_000).toArray();
    final double[] b = randomGenerator.doubles(1_000_000).toArray();
    final double[] c = new double[1_000_000];

    var t1 = System.currentTimeMillis();
    for (int i = 0; i < 100_000; i++) {
      scalarComputationTest(a, b, c);
    }
    var t2 = System.currentTimeMillis();
    System.out.println(t2 - t1);

    t1 = System.currentTimeMillis();
    for (int i = 0; i < 100_000; i++) {
      vectorComputation(a, b, c);
    }
    t2 = System.currentTimeMillis();
    System.out.println(t2 - t1);
  }

  private static void scalarComputationTest(double[] a, double[] b, double[] c) {
    for (int i = 0; i < a.length; i++) {
      c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0;
    }
  }

  private static void vectorComputation(double[] a, double[] b, double[] c) {
    int i = 0;
    int upperBound = SPECIES.loopBound(a.length);
    for (; i < upperBound; i += SPECIES.length()) {
      // FloatVector va, vb, vc;
      var va = DoubleVector.fromArray(SPECIES, a, i);
      var vb = DoubleVector.fromArray(SPECIES, b, i);
      var vc = va.mul(va)
          .add(vb.mul(vb))
          .neg();
      vc.intoArray(c, i);
    }
    for (; i < a.length; i++) {
      c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0;
    }
  }
}
