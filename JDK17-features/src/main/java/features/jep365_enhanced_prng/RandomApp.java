package features.jep365_enhanced_prng;

import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGenerator.ArbitrarilyJumpableGenerator;
import java.util.random.RandomGenerator.JumpableGenerator;
import java.util.random.RandomGenerator.LeapableGenerator;
import java.util.random.RandomGenerator.SplittableGenerator;
import java.util.random.RandomGenerator.StreamableGenerator;
import java.util.random.RandomGeneratorFactory;
import lombok.RequiredArgsConstructor;

public class RandomApp {

  public static void main(String[] args) {

    RandomGenerator aDefault = RandomGenerator.getDefault();

    long count = RandomGeneratorFactory.all()
        .count();
    System.out.println(count);
  }
}
