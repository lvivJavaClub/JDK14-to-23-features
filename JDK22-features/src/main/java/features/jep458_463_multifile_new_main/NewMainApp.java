import features.jep458_463_multifile_new_main.Greeting;
import features.jep458_463_multifile_new_main.Printer;
import features.jep458_463_multifile_new_main.org.example.Audience;

// java --source 22 --enable-preview NewMainApp.java
// javac --release 22 --enable-preview NewMainApp.java
// java --source 22 --enable-preview -cp "libs/*" NewMainApp.java


void main() {
  var greeting = Greeting.get();
  if(greeting != null){
    Printer.sayFollowing(greeting);
    Printer.sayFollowing(STR."Predicted audience: \{new Audience().predict()}");
  }
}


