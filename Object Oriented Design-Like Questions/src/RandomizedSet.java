import java.util.*;

public class RandomizedSet {
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        dict = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (dict.containsKey(val)) {
            return false;
        }
        dict.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!dict.containsKey(val)) {
            return false;
        }
        // 取出链表中最后一个值
        int lastVal = list.get(list.size() - 1);
        // 把它放到要remove掉的那个值的位置上
        int index = dict.get(val);
        list.set(index, lastVal);
        // 在map中更新最后一个值现在的index
        dict.put(lastVal, index); //Hashmap doesn't allow duplicate keys
        // 把原来链表中的最后一个东西删掉
        list.remove(list.size() - 1);
        // 把链表中要删除值的key-value pair删掉
        dict.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomPos = rand.nextInt(list.size());
        return list.get(randomPos);
    }
}
