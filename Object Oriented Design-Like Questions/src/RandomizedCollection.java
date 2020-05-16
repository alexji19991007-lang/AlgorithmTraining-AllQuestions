import java.util.*;

class RandomizedCollection {
    Map<Integer, Set<Integer>> dict;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        dict = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!dict.containsKey(val)) {
            dict.put(val, new LinkedHashSet<>());
        }
        // add the current index to the set
        dict.get(val).add(list.size());
        // add the value to the list
        list.add(val);
        return dict.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!dict.containsKey(val) || dict.get(val).size() == 0) {
            return false;
        }
        // get the index of the value to be removed
        int removeIndex = dict.get(val).iterator().next();
        // remove the index from the set in the dictionary
        dict.get(val).remove(removeIndex);
        // get the value in the end of the list
        int lastVal = list.get(list.size() - 1);
        // put it to the index where the value will be removed
        list.set(removeIndex, lastVal);
        // add the last value's current index to the set in the hashmap
        dict.get(lastVal).add(removeIndex);
        // remove the previous index of the last value in the set
        dict.get(lastVal).remove(list.size() - 1);
        // remove the last value from the list
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/*
  Your RandomizedCollection object will be instantiated and called as such:
  RandomizedCollection obj = new RandomizedCollection();
  boolean param_1 = obj.insert(val);
  boolean param_2 = obj.remove(val);
  int param_3 = obj.getRandom();
 */