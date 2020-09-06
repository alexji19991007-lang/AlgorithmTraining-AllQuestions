import java.util.*;
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Map<Integer, Integer> multipleOfThreeCache = new HashMap<>();
        int candidate = 3 * 17965;
        // "multipleOfThree" is the Function
        int multipleCandidate = multipleOfThreeCache.computeIfAbsent(candidate, FunctionExample::multipleOfThree);
        System.out.println(multipleCandidate);
        candidate = 3 * 1234 + 2;
        int multipleCandidate1 = multipleOfThreeCache.computeIfAbsent(candidate, FunctionExample::multipleOfThree);
        System.out.println(multipleCandidate1);
        System.out.println();

        // Another Example
        List<Thread> threads = Arrays.asList(new Thread(("Larry")), new Thread("Curly"), new Thread("Moe"));
        threads.forEach(System.out::println);
        // "Thread::getName" is passed as a method reference to a Function
        threads.sort(Comparator.comparing(Thread::getName));
        threads.forEach(System.out::println);
        System.out.println();

        // Compose two Function(s)
        Function<String, String> lessThan = HtmlTagMaker::addLessThan;
        Function<String, String> tagger = lessThan.andThen(HtmlTagMaker::addGreaterThan);

        System.out.println(tagger.apply("HTML") + tagger.apply("BODY") + tagger.apply("/BODY") + tagger.apply("/HTML"));
    }

    public static int multipleOfThree(int num) {
        return num % 3 == 0 ? num / 3 : 0;
    }

    static class HtmlTagMaker {
        public static String addLessThan(String t) {
            return "<" + t;
        }

        public static String addGreaterThan(String t) {
            return t + ">";
        }
    }
}
