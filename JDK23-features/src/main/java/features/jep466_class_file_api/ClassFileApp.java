package features.jep466_class_file_api;

import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.lang.reflect.AccessFlag;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("ALL")
public class ClassFileApp {
  public static void main(String[] args) throws IOException {
    Path classFilePath = Path.of("src/main/java/features/jep466_class_file_api/TestClass.class");
    byte[] classBytes = Files.readAllBytes(classFilePath);

    ClassFile cf = ClassFile.of();
    ClassModel classModel = cf.parse(classBytes);

    long publicMethodCount = classModel.methods().stream()
        .filter(method -> method.flags().has(AccessFlag.PUBLIC))
        .count();

    System.out.println("Number of public methods in class " + classModel.thisClass().name() + ": " + publicMethodCount);
  }
}
