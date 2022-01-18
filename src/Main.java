import clothing.HelloWorld;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        HelloWorld hello = new HelloWorld();

        System.out.println(hello.helloWorld());

        Instant inst = Instant.now();

        System.out.println(inst);
    }
}
