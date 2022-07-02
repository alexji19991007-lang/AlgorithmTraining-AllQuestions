import java.util.*;

// LeetCode 2115
public class FindAllPossibleRecipesFromGivenSupplies {
    public static void main(String[] args) {
        FindAllPossibleRecipesFromGivenSupplies test = new FindAllPossibleRecipesFromGivenSupplies();
        String[] recipes = {"bread", "sandwich", "burger"};
        List<List<String>> ingredients = new ArrayList<>();
        ingredients.add(Arrays.asList("yeast", "flour"));
        ingredients.add(Arrays.asList("bread", "meat"));
        ingredients.add(Arrays.asList("sandwich", "meat", "bread"));
        String[] supplies = {"yeast", "flour", "meat"};
        System.out.println(test.findAllRecipes(recipes, ingredients, supplies));
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> supplySet = new HashSet<>(Arrays.asList(supplies));
        Map<String, List<String>> ingredientToRecipe = new HashMap<>();
        List<String> topologicalOrder = new ArrayList<>();
        Map<String, Integer> incomingEdges = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        for (int i = 0; i < recipes.length; ++i) {
            String recipe = recipes[i];
            List<String> ingredientList = ingredients.get(i);
            int curIncomingEdges = ingredientList.size();
            incomingEdges.put(recipe, curIncomingEdges);
            for (String ingredient : ingredientList) {
                ingredientToRecipe.putIfAbsent(ingredient, new ArrayList<>());
                ingredientToRecipe.get(ingredient).add(recipe);
                if (supplySet.contains(ingredient)) {
                    incomingEdges.put(recipe, --curIncomingEdges);
                }
                if (curIncomingEdges == 0) {
                    queue.offer(recipe);
                }
            }
        }
        while (!queue.isEmpty()) {
            String recipe = queue.poll();
            topologicalOrder.add(recipe);
            List<String> dishesMadeFromRecipe = ingredientToRecipe.getOrDefault(recipe, new ArrayList<>());
            for (String dish : dishesMadeFromRecipe) {
                int curIncomingEdges = incomingEdges.get(dish);
                incomingEdges.put(dish, --curIncomingEdges);
                if (curIncomingEdges == 0) {
                    queue.offer(dish);
                }
            }

        }
        return topologicalOrder;
    }
}
