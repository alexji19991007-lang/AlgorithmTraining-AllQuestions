import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// LeetCode 2353
public class FoodRatings {
    Map<String, TreeSet<Food>> cuisineToFoods;
    Map<String, Food> nameToFood;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        this.cuisineToFoods = new HashMap<>();
        this.nameToFood = new HashMap<>();
        for (int i = 0; i < foods.length; ++i) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            Food foodObject = new Food(food, cuisine, rating);
            cuisineToFoods.putIfAbsent(cuisine, new TreeSet<>((f1, f2) -> {
                if (f1.rating != f2.rating) {
                    return f2.rating - f1.rating;
                }
                return f1.name.compareTo(f2.name);
            }));
            cuisineToFoods.get(cuisine).add(foodObject);
            nameToFood.put(food, foodObject);
        }
    }

    public void changeRating(String food, int newRating) {
        Food foodObject = nameToFood.get(food);
        TreeSet<Food> pq = cuisineToFoods.get(foodObject.cuisine);
        pq.remove(foodObject);
        foodObject.rating = newRating;
        pq.add(foodObject);
    }

    public String highestRated(String cuisine) {
        if (!cuisineToFoods.containsKey(cuisine)) {
            return null;
        }
        return cuisineToFoods.get(cuisine).first().name;
    }

    static class Food {
        String name;
        String cuisine;
        int rating;

        public Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }
}
