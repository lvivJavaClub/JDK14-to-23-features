package n04_Inner_Enums;

public class App {

    public static void main(String[] args) {
        class Foo {
            private String bar;
        }

        var foo = new Foo();
        foo.bar = "Bar";

        enum Type {
            SIMPLE, COMPLEX
        }
        var type = Type.SIMPLE;
    }
}
