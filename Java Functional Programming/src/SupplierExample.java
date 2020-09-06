import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Map<String, String> beingMap = new HashMap<>();
        beingMap.put("Demon", "Naughty");
        beingMap.put("Angle", "Nice");
        String being = "Demon";
        // Return an optional describing the specified being if non-null, otherwise returns an empty Optional
        Optional<String> disposition = Optional.ofNullable(beingMap.get(being));
        // orElseGet returns value if being is non-null
        System.out.println("Disposition of " + being + " = " + disposition.orElseGet(() -> "Unknown"));

        String being1 = "Elf";
        // Return an optional describing the specified being if non-null, otherwise returns an empty Optional
        Optional<String> disposition1 = Optional.ofNullable(beingMap.get(being1));
        System.out.println("Disposition of " + being1 + " = " + disposition1.orElse("Unknown"));
        System.out.println();

        CrDemo crDemo1 = new CrDemo();
        crDemo1.zeroParamConstructorRef();
        crDemo1.zeroParamConstructorRefEx();
    }

    static class CrDemo implements Runnable {
        String mString = "alex ji";

        public void zeroParamConstructorRef() {
            // Create a supplier that’s initialized with a zero-param constructor reference for CrDemo
            Supplier<CrDemo> factory = CrDemo::new;
            // get() creates a CrDemo object using a constructor reference for the CrDemo “default” constructor
            CrDemo crDemo = factory.get();
            // Call a method in CrDemo to print the result
            crDemo.run();
        }

        public void zeroParamConstructorRefEx() {
            // Assign a constructor reference to a supplier that acts as a factory for a zero param
            // object of CrDemo CrDemoEx
            Supplier<CrDemo> crDemoFactory = CrDemo::new;
            Supplier<CrDemoEx> crDemoFactoryEx = CrDemoEx::new;
            // This helper method invokes the given supplier to create a new object & call its run() method
            runDemo(crDemoFactory);
            runDemo(crDemoFactoryEx);
        }

        @Override
        public void run() {
            System.out.println(mString);
        }

        // Use the given factory to create a new object & call its run() method
        public <T extends Runnable> void runDemo(Supplier<T> factory) {
            // This call encapsulates details of the concrete constructor that’s used to create an object!
            factory.get().run();
        }
    }

    // This class extends CrDemo & overrides its run() method to uppercase the string
    static class CrDemoEx extends CrDemo {
        @Override
        public void run() {
            System.out.println(mString.toUpperCase());
        }
    }
}
