public class TestBaseConverter {
    public static void main(String[] args) {
        BaseConverter test = new BaseConverter("10AF", 16);
        System.out.println(test.toDecimal());
        System.out.println(test.toTargetBase(8));
        System.out.println(test.toTargetBase(2));
    }
}
