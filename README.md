# JDK14-15-features

## Prepeare

Install java 14 use `sdk`

```bash
sdk install java 14.0.2-open
sdk use java 14.0.2-open
```

* Link to JDK14 features https://openjdk.java.net/projects/jdk/14/
* Link to JDK15 features https://openjdk.java.net/projects/jdk/15/


# JEP 343: Packaging Tool (Incubator)

```bash
mvn clean javafx:run
```

```bash
jpackage --input target --name JPackageDemoApp --main-jar JDK14-features-0.0.1-SNAPSHOT.jar --main-class n04_jep343_Packaging_Tool_Incubator.App --type dmg --java-options '--enable-preview'
```