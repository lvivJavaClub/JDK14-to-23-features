// java --source 21 --enable-preview MultifileApp.java
// javac --release 21 --enable-preview MultifileApp.java

String greeting = "Regular <Hello, World!> SIMPLE";
String greeting2 = "Regular <Hello, World!> SIMPLE2";

void main() {
    sayHello();
}

void sayHello(){
    System.out.println(greeting);
    System.out.println(greeting2);
}
