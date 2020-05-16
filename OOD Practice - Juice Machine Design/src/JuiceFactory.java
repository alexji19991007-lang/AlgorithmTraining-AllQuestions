public class JuiceFactory {
    public Juice getJuice(String juiceType) {
        if (juiceType == null) {
            return null;
        } else if (juiceType.equalsIgnoreCase("Orange")) {
            return new OrangeJuice();
        } else if (juiceType.equalsIgnoreCase("Apple")) {
            return new AppleJuice();
        } else if (juiceType.equalsIgnoreCase("Peach")) {
            return new PeachJuice();
        } else {
            return null;
        }
    }
}
