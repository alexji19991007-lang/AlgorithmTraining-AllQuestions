public class Test {
    public static void main(String[] args) {
        StackWithMin s1 = new StackWithMin();
        s1.push(3);
        System.out.println(s1.min());
        s1.push(1);
        System.out.println(s1.top());
        s1.push(2);
        System.out.println(s1.min());
        s1.push(1);
        s1.push(2);
        System.out.println(s1.min());
        s1.push(0);
        System.out.println(s1.min());
        s1.pop();
        System.out.println(s1.min());
        s1.pop();
        s1.pop();
        s1.pop();
        s1.pop();
        System.out.println(s1.min());
    }
}
