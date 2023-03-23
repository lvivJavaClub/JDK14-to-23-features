package features.jep438_vector_api;

import java.util.random.RandomGeneratorFactory;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;

// from 64 to 512 bits
// VECTOR shape + type = SPECIES
// mask
// VectorOperators
public class VectorApp {

  static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

  public static void main(String[] args) {

    var randomGenerator = RandomGeneratorFactory.getDefault().create();
    final int[] a = randomGenerator.ints(8).toArray();
    final int[] b = randomGenerator.ints(8).toArray();
    final int[] c = new int[8];


  }

  private static void scalarComputationTest(int[] a, int[] b, int[] c) {
    for (int i = 0; i < a.length; i++) {
      c[i] = (a[i] * a[i] + b[i] * b[i]) * -1;
    }
  }

  private static void vectorComputation(int[] a, int[] b, int[] c) {
    int i = 0;
    var va = IntVector.fromArray(SPECIES, a, i);
    var vb = IntVector.fromArray(SPECIES, b, i);
    var vc = va.mul(va)
        .add(vb.mul(vb))
        .neg();

    vc.intoArray(c, i);
  }
}
