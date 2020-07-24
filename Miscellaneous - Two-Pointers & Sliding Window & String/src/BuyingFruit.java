import java.util.ArrayList;
import java.util.List;

public class BuyingFruit {
    public static void main(String[] args) {
        List<List<String>> codeList = new ArrayList<>();
        List<String> code = new ArrayList<>();
        code.add("apple");
        code.add("apple");
        codeList.add(code);
        code = new ArrayList<>();
        code.add("anything");
        code.add("banana");
        code.add("orange");
        codeList.add(code);

        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("orange");
        shoppingCart.add("apple");
        shoppingCart.add("apple");
        shoppingCart.add("orange");
        shoppingCart.add("banana");
        shoppingCart.add("orange");

        BuyingFruit ft = new BuyingFruit();
        System.out.println(codeList.toString());
        System.out.println(shoppingCart.toString());
        System.out.println(ft.check(codeList, shoppingCart));
    }

    public int check(List<List<String>> codeList, List<String> shoppingCart) {
        List<String> codeListFlattened = new ArrayList<>();
        for (List<String> code : codeList) {
            codeListFlattened.addAll(code);
        }
        if (codeListFlattened.size() > shoppingCart.size()) {
            return 0;
        }
        for (int i = 0; i <= shoppingCart.size() - codeListFlattened.size(); ++i) {
            int j = 0;
            while (j < codeListFlattened.size() && ((codeListFlattened.get(j).equals(shoppingCart.get(i + j)))
                    || codeListFlattened.get(j).equals("anything"))) {
                j++;
            }
            if (j == codeListFlattened.size()) {
                return 1;
            }
        }
        return 0;
    }
}
