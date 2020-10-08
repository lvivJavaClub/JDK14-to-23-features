package n01_jep360_Sealed_Clases;

public abstract sealed class Planet permits Jupiter, Earth {

    abstract int satelliteCount();
}
