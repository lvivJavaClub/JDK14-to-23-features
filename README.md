# JDK14-15-16-features

## Prepeare

Install java 14 use `sdk`

```bash
sdk install java 14.0.2-open
sdk use java 14.0.2-open
```

* Link to JDK14 features https://openjdk.java.net/projects/jdk/14/
* Link to JDK15 features https://openjdk.java.net/projects/jdk/15/
* Link to JDK16 features https://openjdk.java.net/projects/jdk/16/
* Link to JDK17 features https://openjdk.java.net/projects/jdk/17/

## JEP 358: Helpful NullPointerExceptions

add VM param `-XX:+ShowCodeDetailsInExceptionMessages`


## JEP 343: Packaging Tool (Incubator)

```bash
mvn clean javafx:run
```

```bash
jpackage --input target --name JPackageDemoApp --main-jar JDK14-features-0.0.1-SNAPSHOT-jar-with-dependencies.jar --main-class n04_jep343_Packaging_Tool_Incubator.App --type dmg --java-options '--enable-preview'
WARNING: Using incubator modules: jdk.incubator.jpackage
```
